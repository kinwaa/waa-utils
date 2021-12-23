/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   RSACipherer.java
 * CreateTime: 2021-10-24 08:58:57
 */
package cc.waa.java.utils.cryptology.rsa;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.Cipher;

import cc.waa.java.utils.cryptology.Cipherer;
import cc.waa.java.utils.cryptology.Signator;
import lombok.RequiredArgsConstructor;

/**
 * RSA算法的密码器（同时实现签名器）.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@RequiredArgsConstructor
public final class RSACipherer implements Cipherer, Signator {

   /** 默认的签名算法. */
   public static final String SIGN_ALGORITHMS = "SHA1WithRSA";



   /** 传入的Key对象（可公可私）. */
   private final Key key;

   @Override
   public byte[] encrypt(final byte[] data) throws GeneralSecurityException {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(ENCRYPT_MODE, this.key);

      return cipher.doFinal(data);
   }

   @Override
   public byte[] decrypt(final byte[] data) throws GeneralSecurityException {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(DECRYPT_MODE, this.key);

      return cipher.doFinal(data);
   }

   @Override
   public byte[] sign(final byte[] data) throws GeneralSecurityException {
      if (!(this.key instanceof PrivateKey)) {
         throw new SignatureException("just PrivateKey can sign");
      }

      Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

      signature.initSign((PrivateKey) this.key);
      signature.update(data);

      return signature.sign();
   }

   @Override
   public boolean verify(final byte[] data, final byte[] sign)
         throws GeneralSecurityException {
      if (!(this.key instanceof PublicKey)) {
         throw new SignatureException("just PublicKey can verify");
      }

      Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

      signature.initVerify((PublicKey) this.key);
      signature.update(data);

      return signature.verify(sign);
   }
}
