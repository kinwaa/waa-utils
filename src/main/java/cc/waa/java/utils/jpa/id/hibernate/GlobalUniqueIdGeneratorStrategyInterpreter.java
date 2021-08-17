/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   GlobalUniqueIdGeneratorStrategyInterpreter.java
 * CreateTime: 2021-05-25 23:49:07
 */
package cc.waa.java.utils.jpa.id.hibernate;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import org.hibernate.boot.model.IdGeneratorStrategyInterpreter;
import org.hibernate.boot.model.IdentifierGeneratorDefinition.Builder;

import cc.waa.java.utils.NotImplementedException;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class GlobalUniqueIdGeneratorStrategyInterpreter
   implements IdGeneratorStrategyInterpreter {

   @Override
   public String determineGeneratorName(final GenerationType generationType,
                              final GeneratorNameDeterminationContext context) {

      if (generationType == AUTO) {
         String generatorName = context.getGeneratedValueGeneratorName();

         switch (generatorName) {
         case "snow-flake":
            return SnowFlakeIdGenerator.class.getName();
         default:
         }
      }

      return null;
   }

   @Override
   public void interpretTableGenerator(final TableGenerator annotation,
                                       final Builder definitionBuilder) {
      throw new NotImplementedException();
   }

   @Override
   public void interpretSequenceGenerator(final SequenceGenerator annotation,
                                          final Builder definitionBuilder) {
      throw new NotImplementedException();
   }
}
