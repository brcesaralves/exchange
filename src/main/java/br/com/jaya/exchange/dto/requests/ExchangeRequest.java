
package br.com.jaya.exchange.dto.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.jaya.exchange.enums.Currencies;
import lombok.Data;

@Data
public class ExchangeRequest implements Serializable {

     private static final long serialVersionUID = -5040604137460667518L;

     @NotNull
     private Currencies Currency;

}
