
package br.com.jaya.exchange.exception;

public class ExchangeBadRequestException extends ExchangeException {

     private static final long serialVersionUID = 2714427595010977258L;

     public ExchangeBadRequestException(String key, String msg) {

          super(key, msg);
     }

}
