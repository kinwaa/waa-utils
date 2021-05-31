/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   GlobalUniqueIdGeneratorStrategyInterpreter.java
 * CreateTime: 2021-05-31 22:09:03
 */
package cc.waa.java.utils.jpa.id.hibernate;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.spi.MetadataBuilderFactory;
import org.hibernate.boot.spi.MetadataBuilderImplementor;

/**
 * 为实现把自定义的IdGenerator注入到SessionFactory（或者EntityManagerFactory）中.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class IdGeneratorMetadataBuilderFactory
   implements MetadataBuilderFactory {

   @Override
   public MetadataBuilderImplementor getMetadataBuilder(
                              final MetadataSources metadatasources,
                              final MetadataBuilderImplementor defaultBuilder) {
      GlobalUniqueIdGeneratorStrategyInterpreter interpreter;

      interpreter = new GlobalUniqueIdGeneratorStrategyInterpreter();
      defaultBuilder.applyIdGenerationTypeInterpreter(interpreter);

      return defaultBuilder;
   }
}
