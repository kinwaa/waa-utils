/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   GlobalUniqueIdGeneratorStrategyProvider.java
 * CreateTime: 2021-05-26 00:49:29
 */
package cc.waa.java.utils.jpa.id.hibernate;

import static java.util.Collections.singletonMap;

import java.util.Map;

import org.hibernate.jpa.spi.IdentifierGeneratorStrategyProvider;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class GlobalUniqueIdGeneratorStrategyProvider
   implements IdentifierGeneratorStrategyProvider {

   @Override
   public Map<String, Class<?>> getStrategies() {
      return singletonMap("snow-flake", SnowFlakeIdGenerator.class);
   }
}
