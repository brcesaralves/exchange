
package br.com.jaya.exchange.dto.responses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionResponse implements Serializable {

     private static final long serialVersionUID = 2951238838696823508L;

     @JsonProperty("transactionId")
     private Long id;

     private Long userId;

     private String originCurrency;

     private BigDecimal originValue;

     private String targetCurrency;

     private BigDecimal targetValue;

     private BigDecimal rate;

     private ZonedDateTime timestamp;
}
