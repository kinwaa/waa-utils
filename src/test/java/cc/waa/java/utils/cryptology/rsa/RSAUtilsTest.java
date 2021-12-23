/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   RSAUtilsTest.java
 * CreateTime: 2021-12-21 15:54:46
 */
package cc.waa.java.utils.cryptology.rsa;

import static cc.waa.java.utils.cryptology.rsa.RSAUtils.extractPrivateKeyAsBase64;
import static cc.waa.java.utils.cryptology.rsa.RSAUtils.extractPublicKeyAsBase64;
import static cc.waa.java.utils.cryptology.rsa.RSAUtils.genKeyPair;
import static org.junit.Assert.assertNotNull;

import java.security.GeneralSecurityException;
import java.security.KeyPair;

import org.junit.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class RSAUtilsTest {

   @Test
   public void testGenKeyPair() throws GeneralSecurityException {
      KeyPair keyPair = genKeyPair();

      assertNotNull(keyPair);

      String privateKey = extractPrivateKeyAsBase64(keyPair);
      String publicKey  = extractPublicKeyAsBase64(keyPair);

      assertNotNull(privateKey);
      assertNotNull(publicKey);
   }
}
