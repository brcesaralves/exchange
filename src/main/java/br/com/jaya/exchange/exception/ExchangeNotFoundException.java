
package br.com.jaya.exchange.exception;

public class ExchangeNotFoundException extends ExchangeException {

     private static final long serialVersionUID = 2693472434849305004L;

     public ExchangeNotFoundException(String key, String msg) {

          super(key, msg);
     }
}
