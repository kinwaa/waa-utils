/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   RSACipherer.java
 * CreateTime: 2021-10-24 08:58:57
 */
package cc.waa.java.utils.cryptology.rsa;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static javax.crypto.Cipher.getInstance;

import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;

import cc.waa.java.utils.cryptology.Cipherer;
import lombok.Setter;

/**
 * RSA算法的密码器.
 *
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class RSACipherer implements Cipherer {

   /** Key. */
   @Setter
   private Key key;

   @Override
   public byte[] encrypt(final byte[] data) throws GeneralSecurityException {
      Cipher cipher = getInstance("RSA");
      cipher.init(ENCRYPT_MODE, this.key);

      return cipher.doFinal(data);
   }

   @Override
   public byte[] decrypt(final byte[] data) throws GeneralSecurityException {
      Cipher cipher = getInstance("RSA");
      cipher.init(DECRYPT_MODE, this.key);

      return cipher.doFinal(data);
   }
}
