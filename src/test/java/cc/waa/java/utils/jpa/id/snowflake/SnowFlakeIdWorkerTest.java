/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   SnowFlakeIdWorkerTest.java
 * CreateTime: 2021-05-25 20:49:03
 */
package cc.waa.java.utils.jpa.id.snowflake;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class SnowFlakeIdWorkerTest {

   private SnowFlakeIdWorker idWorker;

   @Before
   public void setUp() throws Exception {
      this.idWorker = new SnowFlakeIdWorker();
   }

   /**
    * Test method for {@link cc.waa.java.utils.jpa.id.snowflake.SnowFlakeIdWorker#nextId()}.
    */
   @Test
   public void testNextId() {
      Long a = this.idWorker.nextId().present(),
           b = this.idWorker.nextId().present();

      assertNotNull(a);
      assertNotNull(b);
      assertNotEquals(a, b);
   }
}
