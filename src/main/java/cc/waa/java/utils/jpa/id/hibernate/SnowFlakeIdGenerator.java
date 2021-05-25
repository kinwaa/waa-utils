/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   SnowFlakeIdGenerator.java
 * CreateTime: 2021-05-25 21:50:26
 */
package cc.waa.java.utils.jpa.id.hibernate;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import cc.waa.java.utils.jpa.id.snowflake.SnowFlakeIdWorker;
import lombok.extern.slf4j.Slf4j;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Slf4j
public final class SnowFlakeIdGenerator
   implements IdentifierGenerator, Configurable {

   /** idWorker. */
   private final SnowFlakeIdWorker idWorker = new SnowFlakeIdWorker();

   @Override
   public void configure(final Type type, final Properties params,
                         final ServiceRegistry serviceRegistry)
         throws MappingException {
      log.info("配置SnowFlakeIdGenerator");
      // TOOD 须按实际情况配置具体的idWorker
   }

   @Override
   public Serializable generate(final SharedSessionContractImplementor s,
                                final Object object)
         throws HibernateException {
      return this.idWorker.nextId().present();
   }
}
