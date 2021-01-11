
package br.com.jaya.exchange.dto.responses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class RateResponse implements Serializable {

     private static final long serialVersionUID = -7224667428067294214L;

     private Rate rates;

     private String base;

     private LocalDate date;

     public BigDecimal rateOf(int currencyCode) {

          switch (currencyCode) {
               case 986:
                    return rates.getBrl();

               case 978:
                    return rates.getEur();

               case 392:
                    return rates.getJpy();

               case 840:
                    return rates.getUsd();

               default:
                    return BigDecimal.ZERO;
          }
     }
}
