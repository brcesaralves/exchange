
package br.com.jaya.exchange.dto.requests;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class ExchangeRequest implements Serializable {

     private static final long serialVersionUID = -5040604137460667518L;

     @Value("${url.exchange.rate.api}")
     private String url;
}
