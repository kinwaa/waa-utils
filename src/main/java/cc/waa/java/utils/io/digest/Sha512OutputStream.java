/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   Sha512OutputStream.java
 * CreateTime: 2021-05-19 21:39:11
 */
package cc.waa.java.utils.io.digest;

import static org.apache.commons.codec.binary.Hex.encodeHexString;
import static org.apache.commons.codec.digest.DigestUtils.getSha512Digest;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public final class Sha512OutputStream extends OutputStream {

   /** Sha512散列算法的编码器. */
   private final MessageDigest digest = getSha512Digest();

   @Override
   public void write(final int b) throws IOException {
      this.digest.update((byte) b);
   }

   @Override
   public void write(final byte[] b, final int off, final int len)
         throws IOException {
      this.digest.update(b, off, len);
   }

   @Override
   public void write(final byte[] b) throws IOException {
      this.digest.update(b);
   }

   @Override
   public String toString() {
      return encodeHexString(this.digest.digest());
   }
}
