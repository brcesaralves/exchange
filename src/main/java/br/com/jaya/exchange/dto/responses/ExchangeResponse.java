
package br.com.jaya.exchange.dto.responses;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ExchangeResponse implements Serializable {

     private static final long serialVersionUID = -7224667428067294214L;

     private Rate rates;

     private String base;

     private LocalDate date;
}
