/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   SnowFlakeIdGeneratorTest.java
 * CreateTime: 2021-06-01 14:57:03
 */
package cc.waa.java.utils.jpa.id.hibernate;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cc.waa.java.utils.material.entity.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SnowFlakeIdGeneratorTest {

   @Autowired
   private DataSource dataSource;

   @Before
   public void setUp() throws Exception {
      try (Connection con = this.dataSource.getConnection()) {
         try (Statement stm = con.createStatement()) {
            stm.executeUpdate("drop table if exists test_order");
            stm.executeUpdate("create table test_order (pk_id numeric not null, c_create_time timestamp not null, primary key (pk_id))");
         }
      }

      log.info("测试数据已经准备好");
   }

   @Autowired
   private SessionFactory sessionFactory;

   @Test
   public void testSnowFlakeIdGeneratorInject() {
      Long orderId = null;

      try (Session session = this.sessionFactory.openSession()) {
         Order order = new Order();
         order.setCreateTime(new Date());

         session.getTransaction().begin();
         orderId = (Long) session.save(order);
         session.getTransaction().commit();

         assertNotNull(orderId);
      }

      try (Session session = this.sessionFactory.openSession()) {
         Order order = session.find(Order.class, orderId);

         assertNotNull(order);
      }
   }
}
