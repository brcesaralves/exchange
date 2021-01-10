
package br.com.jaya.exchange.dto.responses;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Rate implements Serializable {

     private static final long serialVersionUID = 8173038749196904715L;

     @JsonProperty("USD")
     private BigDecimal usd;

     @JsonProperty("BRL")
     private BigDecimal brl;

     @JsonProperty("EUR")
     private BigDecimal eur;

     @JsonProperty("JPY")
     private BigDecimal jpy;
}
