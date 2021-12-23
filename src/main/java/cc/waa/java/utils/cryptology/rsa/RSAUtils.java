/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   RSAUtils.java
 * CreateTime: 2021-12-21 15:43:09
 */
package cc.waa.java.utils.cryptology.rsa;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA算法的相关工具.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class RSAUtils {

   /** 默认的key长度. */
   public static final int DEFAULT_KEYSIZE = 1024;

   /**
    * 生成一个全新的KeyPair.
    *
    * @return 密钥对
    * @throws GeneralSecurityException
    */
   public static KeyPair genKeyPair() throws GeneralSecurityException {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(DEFAULT_KEYSIZE, new SecureRandom());

      return generator.genKeyPair();
   }

   /**
    * 从KeyPair提取出私钥并转换成base64字符串.
    *
    * @param keyPair
    * @return base64的私钥字符串
    */
   public static String extractPrivateKeyAsBase64(final KeyPair keyPair) {
      return encodeBase64String(keyPair.getPrivate().getEncoded());
   }

   /**
    * 从KeyPair提取出公钥并转换成base64字符串.
    *
    * @param keyPair
    * @return base64的公钥字符串
    */
   public static String extractPublicKeyAsBase64(final KeyPair keyPair) {
      return encodeBase64String(keyPair.getPublic().getEncoded());
   }

   /**
    * 用base64字符串生成私钥.
    *
    * @param base64
    * @return 私钥
    * @throws GeneralSecurityException
    */
   public static PrivateKey genPrivateKey(final String base64)
         throws GeneralSecurityException {
      KeyFactory factory = KeyFactory.getInstance("RSA");
      byte[] rawKey = decodeBase64(base64);

      return factory.generatePrivate(new PKCS8EncodedKeySpec(rawKey));
   }

   /**
    * 用base64字符串生成公钥.
    *
    * @param base64
    * @return 公钥
    * @throws GeneralSecurityException
    */
   public static PublicKey genPublicKey(final String base64)
         throws GeneralSecurityException {
      KeyFactory factory = KeyFactory.getInstance("RSA");
      byte[] rawKey = decodeBase64(base64);

      return factory.generatePublic(new X509EncodedKeySpec(rawKey));
   }



   /** 隐藏的默认构造函数. */
   private RSAUtils() {
   }
}
