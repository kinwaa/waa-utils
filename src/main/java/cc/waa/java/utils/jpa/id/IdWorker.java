/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   IdWorker.java
 * CreateTime: 2021-05-25 19:03:32
 */
package cc.waa.java.utils.jpa.id;

import java.io.Serializable;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 *
 * @param <T> IdWorker产生的id的类型
 */
public interface IdWorker<T extends Serializable> {

   /**
    * 产生下一个新的id.
    *
    * @return id
    */
   GlobalUniqueId<T> nextId();
}
