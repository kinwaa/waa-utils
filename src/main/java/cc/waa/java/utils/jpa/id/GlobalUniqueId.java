/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   GlobalUniqueId.java
 * CreateTime: 2021-05-25 19:02:19
 */
package cc.waa.java.utils.jpa.id;

import java.io.Serializable;

/**
 * 全局唯一id.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 *
 * @param <T> 所封装的id的类型
 */
public interface GlobalUniqueId<T extends Serializable> {

   /**
    * 以实际id类型输出.
    *
    * @return id
    */
   T present();
}
