/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   GeneralResponse.java
 * CreateTime: 2021-09-30 00:15:17
 */
package cc.waa.java.utils.api;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Builder
@Getter @Setter
public class GeneralResponse implements Serializable {

   /** serialVersionUID. */
   private static final long serialVersionUID = -3729809127646152432L;



   /** 返回信息. */
   private String message;
}
