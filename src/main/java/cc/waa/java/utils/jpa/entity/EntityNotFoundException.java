/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   EntityNotFoundException.java
 * CreateTime: 2021-09-30 16:30:48
 */
package cc.waa.java.utils.jpa.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RequiredArgsConstructor
public class EntityNotFoundException extends Exception {

   /** serialVersionUID. */
   private static final long serialVersionUID = 2305197282649255965L;



   /** 实体类型. */
   @Getter
   private final Class<?> entityType;

   /** 实体的key. */
   @Getter
   private final Long key;
}
