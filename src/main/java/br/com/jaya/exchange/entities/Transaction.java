
package br.com.jaya.exchange.entities;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Table(name = "TRANSACTIONS")
@Entity
@Data
public class Transaction {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private Long userId;

     private String originCurrency;

     private BigDecimal originValue;

     private String targetCurrency;

     private BigDecimal targetValue;

     private BigDecimal rate;

     @Type(type = "java.time.ZonedDateTime")
     private ZonedDateTime timestamp;
}
