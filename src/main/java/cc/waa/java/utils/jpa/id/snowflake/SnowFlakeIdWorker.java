/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   SnowFlakeIdWorker.java
 * CreateTime: 2021-05-25 19:07:54
 */
package cc.waa.java.utils.jpa.id.snowflake;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.join;

import cc.waa.java.utils.jpa.id.GlobalUniqueId;
import cc.waa.java.utils.jpa.id.IdWorker;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用SnowFlake算法生成分布式id（64位，首位固定为0，目的要为正数）.
 *
 * <p>id由时间戳（n位）、机器id（63-n-m位）、序号（m位）组成</p>
 * <p>注意：应用启动时必须初始化唯一的实例：<br>new SnowFlakeIdWorker(...);</p>
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Slf4j
public class SnowFlakeIdWorker implements IdWorker<Long> {

   /** 整个id的总位数（包含首位的0）. */
   private static final long ID_BITS = 64;

   /** 默认的基准时间戳. */
   private static final long DEFAULT_BASE_TIMESTAMP = 1621872000000L;

   /** 默认的时间戳占位数. */
   private static final int DEFAULT_BITS_OF_TIMESTAMP = 47;

   /** 默认的序号占位数. */
   private static final int DEFAULT_BITS_OF_SEQUENCE = 6;

   /** 默认的workerId. */
   private static final long DEFAULT_WORKER_ID = 0;



   private final class SnowFlakeId implements GlobalUniqueId<Long> {

      /** id的long值. */
      private final long idValue;

      private SnowFlakeId(final long timestamp, final long sequence) {
         SnowFlakeIdWorker idWorker = SnowFlakeIdWorker.this;
         long value = timestamp;

         value = value << idWorker.workerIdBits;
         value = value | idWorker.mWorkerId;

         value = value << idWorker.cSequenceBits;
         value = value | sequence;

         this.idValue = value;
      }

      @Override
      public Long present() {
         return this.idValue;
      }

      @Override
      public String toString() {
         return String.valueOf(this.idValue);
      }
   }


   /*
    * 运行前已固定的设置
    */

   /**
    * 基准时间戳.
    *
    * <p>为了能容纳尽量多的id，该功能实现时确定一个基准时间戳，
    * id中使用的时间戳都以基准时间戳开始计算（按毫秒累加）</p>
    */
   private final long cBaseTimestamp;

   /** sequence所占的位数. */
   private final long cSequenceBits;

   /** workerId所占的位数. */
   private final long workerIdBits;

   /** timestampBits对应时间戳的最大值. */
   private final long maxTimestamp;

   /** sequenceBits对应sequence的最大值（最小值为0）. */
   private final long maxSequence;


   /*
    * 运行时的变量
    */

   /** 时间戳（声明：保存已减掉基准时间戳的值）. */
   private long mLastTimestamp = -1L;

   /** 配置机器Id. */
   private final long mWorkerId;

   /** 序号. */
   private long mSequence = 0L;


   /**
    * 默认构造函数.
    */
   public SnowFlakeIdWorker() {
      this(DEFAULT_WORKER_ID);
   }

   /**
    * @param workerId
    */
   public SnowFlakeIdWorker(final long workerId) {
      this(DEFAULT_BASE_TIMESTAMP, DEFAULT_BITS_OF_TIMESTAMP,
           DEFAULT_BITS_OF_SEQUENCE, workerId);
   }

   /**
    * @param baseTimestamp
    * @param timestampBits
    * @param sequenceBits
    * @param workerId
    */
   public SnowFlakeIdWorker(final long baseTimestamp,
                            final int timestampBits,
                            final int sequenceBits,
                            final long workerId) {
      super();

      this.cBaseTimestamp = baseTimestamp;

      this.cSequenceBits = sequenceBits;
      this.workerIdBits = ID_BITS - timestampBits - sequenceBits - 1;

      this.maxTimestamp = (-1L ^ -1L << timestampBits);
      this.maxSequence  = (-1L ^ -1L << sequenceBits);

      if (workerId > (-1L ^ -1L << this.workerIdBits) || workerId < 0) {
         throw new IllegalArgumentException("workerId越界");
      }

      this.mWorkerId = workerId;
   }

   /**
    * 获取当前时间戳（直接减掉基准时间戳）.
    *
    * @return 时间戳
    */
   private long timestampOffsetGen() {
      return currentTimeMillis() - this.cBaseTimestamp;
   }

   /**
    * 获取下一个时间戳（sequence溢出的情况下需要处理）.
    *
    * @param lastTimestamp 上一个时间戳
    * @return 下一个时间戳
    */
   private long tilNextTimestamp(final long lastTimestamp) {
      long timestamp = timestampOffsetGen();

      while (timestamp <= lastTimestamp) {
         timestamp = timestampOffsetGen();
      }

      return timestamp;
   }

   @Override
   public final synchronized GlobalUniqueId<Long> nextId() {
      long timestamp = timestampOffsetGen();

      if (timestamp > this.maxTimestamp) { // id资源已耗尽
         throw new RuntimeException("id所需的位数已经耗尽，无法再生成id");
      }

      if (this.mLastTimestamp == timestamp) {
         this.mSequence = (this.mSequence + 1) & this.maxSequence;

         if (this.mSequence == 0) { // sequence已经超出允许范围
            timestamp = tilNextTimestamp(this.mLastTimestamp);

            log.warn(join("同一timestamp的sequence已经用完，需要等到下一个",
                          "timestamp，这样比较耗时，如果频繁出现这种状况，",
                          "建议调整bits的分配"));
         }
      } else {
         this.mSequence = 0;
      }

      if (timestamp < this.mLastTimestamp) {
         throw new RuntimeException("时间戳异常，不可能获取到比之前还小的时间戳");
      }

      this.mLastTimestamp = timestamp;

      return new SnowFlakeId(timestamp, this.mSequence);
   }
}
