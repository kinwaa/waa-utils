/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   Cipherer.java
 * CreateTime: 2021-10-24 00:40:17
 */
package cc.waa.java.utils.cryptology;

import java.security.GeneralSecurityException;

/**
 * 通用密码器接口.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public interface Cipherer {

   /**
    * 加密.
    *
    * @param data
    * @return 密文
    * @throws GeneralSecurityException
    */
   byte[] encrypt(byte[] data) throws GeneralSecurityException;

   /**
    * 解密.
    *
    * @param data
    * @return 明文
    * @throws GeneralSecurityException
    */
   byte[] decrypt(byte[] data) throws GeneralSecurityException;
}
