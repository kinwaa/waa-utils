/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   LogicalDeleteSupport.java
 * CreateTime: 2021-06-09 16:46:57
 */
package cc.waa.java.utils.jpa.entity;

import java.util.Date;

/**
 * 支持逻辑删除的实体类接口.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface LogicalDeleteSupport {

   /**
    * @return 是否已经删除
    */
   boolean isDeleted();

   /**
    * @param deleted
    */
   void setDeleted(boolean deleted);

   /**
    * @return 删除时间
    */
   Date getDeleteTime();

   /**
    * @param deleteTime
    */
   void setDeleteTime(Date deleteTime);
}
