
package br.com.jaya.exchange.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EXCHANGE")
@Data
public class Exchange {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private Long userId;

     private String originCurrency;

     private BigDecimal originValue;

     private String targetCurrency;

     private BigDecimal targetValue;

     private BigDecimal tax;

     @Column(columnDefinition = "TIMESTAMP")
     private LocalDateTime localDateTime;

}
