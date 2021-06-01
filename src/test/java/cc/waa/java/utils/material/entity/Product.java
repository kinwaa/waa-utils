/*
 * Project:    Waa Java Utilities Package
 *
 * FileName:   Product.java
 * CreateTime: 2021-06-01 15:59:16
 */
package cc.waa.java.utils.material.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
@Entity
@Table(name = "test_product")
@Getter @Setter
public class Product implements Serializable {

   @Id
   @GeneratedValue(generator = "snow-flake")
   @Column(name = "pk_id")
   private Long id;

   @Column(name = "c_create_time")
   private Date createTime;

   @Column(name = "c_name")
   private String name;
}
