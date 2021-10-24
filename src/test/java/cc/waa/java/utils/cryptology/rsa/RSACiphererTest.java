/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   RSACiphererTest.java
 * CreateTime: 2021-10-24 21:41:03
 */
package cc.waa.java.utils.cryptology.rsa;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.security.KeyFactory.getInstance;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.junit.Before;
import org.junit.Test;

public class RSACiphererTest {

   private RSACipherer master, client;

   @Before
   public void setUp() throws GeneralSecurityException {
      String pri = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIU3nKC8GErlBx7Rd/gLnspMI1805oPBrsini+FgHWNbaK/xBEdNdjOu14lln70Blfjljs7IFRR/oS7Y8xI5Ibqva0xmJ4ZmdosYQuyNca5vZX6ONHB5D7UUtSoHCwcTeEyM3wOOMHgVFufLIBOyQCtylLURrZs70HUn1sMNmOJ/AgMBAAECgYAUkBT5Ko+FX/zNYP/npcOI7l+3Xm8Gf2r/Rf1teGyQ3Zc3MTi72NUZ/S7/4lPZ1NjVZnm4qWG3nEvA83PxqCfwdAPmjBcSLfQK4UhPc/y8Ip1/mLhYbHKidVxMFP6nptpDUmajrpM3f57CMJ1UJNac1EE2MWmqiaGdfuys+pGQYQJBAL1fnBMnxizNfK8Ba9rbTgHCJa5W+sHD+8Dr6Twp1zFSu3VRoXhsxvHfrj5VzSmLbhNmeQuXIRliGN/xW8RRzp0CQQC0FibCNIiGbzw7n5/EMij5FfrhfHTi2MJWJVUBRRSdkYUHgkv8sDRBHxZW2VbGyfR3oCca17fmZnyo0MF/XnzLAkBWuXtjdlqWWACasL7w+m/t9DiHb8Pkitk3T5J/f/XR276Hiru0x7QQPsywNWEAfp8JeWtDuJFq63bSz9ijvoctAkBkZVUGP7M8/xZdRwgSoMQm2RIcUv322VC+JeHV62Uq0s2O+hzSqoj3JRWXJWMzP7OCXU5vsINddxVYJ8k38L0xAkBx6Vmy/Ai5jjI5kUDetnacFZHGBAosLuYd+Fh092HSRMlDYb49gD7zLhSXTQ2c+0AgyUf3WnLEQXUji72Ai9ZH";
      String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFN5ygvBhK5Qce0Xf4C57KTCNfNOaDwa7Ip4vhYB1jW2iv8QRHTXYzrteJZZ+9AZX45Y7OyBUUf6Eu2PMSOSG6r2tMZieGZnaLGELsjXGub2V+jjRweQ+1FLUqBwsHE3hMjN8DjjB4FRbnyyATskArcpS1Ea2bO9B1J9bDDZjifwIDAQAB";

      KeyFactory factory = getInstance("RSA");

      PrivateKey privateKey = factory.generatePrivate(new PKCS8EncodedKeySpec(decodeBase64(pri)));
      PublicKey publicKey = factory.generatePublic(new X509EncodedKeySpec(decodeBase64(pub)));

      this.master = new RSACipherer();
      this.client = new RSACipherer();

      this.master.setKey(privateKey);
      this.client.setKey(publicKey);
   }

   @Test
   public void testCipherer() {
      String data = "csdn已为您找到关于java代码大全相关内容。";
      byte[] raw;

      try {
         raw = this.client.encrypt(data.getBytes(UTF_8)); // 加密
         raw = this.master.decrypt(raw); // 解密

         assertEquals(data, new String(raw, UTF_8));
      } catch (GeneralSecurityException e) {
         fail("加/解密失败");
      }
   }

   @Test
   public void testDecryptFromBase64() {
      String data = "U4ZT5EbTsasc5zxcsy/Zu2m50xjmRdvMPWeH0S/9NifGGta7bZl15bRaEixiFR3pUCayTdFeR1s3pgzwTx4o2gTJf5ylKs0FlXyQWDjGLKYN+yPbeiiqzL2wf7t7kez+wGPK9b8QWYHpcsachaU5tG0+gbDjs0+zVlrsOgge+kk=";
      byte[] raw;

      try {
         raw = this.master.decryptFromBase64(data);

         assertEquals("123456", new String(raw, UTF_8));
      } catch (GeneralSecurityException e) {
         fail("加/解密失败");
      }
   }
}
