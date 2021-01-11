
package br.com.jaya.exchange.exception;

public class ExchangeServerErrorException extends ExchangeException {

     private static final long serialVersionUID = 5496113090017695995L;

     public ExchangeServerErrorException(String key, String msg) {

          super(key, msg);
     }
}
