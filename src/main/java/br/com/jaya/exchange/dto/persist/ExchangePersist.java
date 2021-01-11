
package br.com.jaya.exchange.dto.persist;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.jaya.exchange.enums.Currencies;
import lombok.Data;

@Data
public class ExchangePersist implements Serializable {

     private static final long serialVersionUID = 7551496895229522922L;

     @NotNull
     private Long UserId;

     @NotNull
     private Currencies originCurrency;

     @NotNull
     private BigDecimal originValue;

     @NotNull
     private Currencies targetCurrency;
}
