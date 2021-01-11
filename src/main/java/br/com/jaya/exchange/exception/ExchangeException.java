
package br.com.jaya.exchange.exception;

import lombok.Getter;

public class ExchangeException extends RuntimeException {

     private static final long serialVersionUID = 5981442778490971891L;

     @Getter
     private ExchangeExceptionsMessagesEnum msgEnum;

     public ExchangeException(ExchangeExceptionsMessagesEnum globalRegisterNotFound) {

          super(globalRegisterNotFound.getMessage());
          this.msgEnum = globalRegisterNotFound;
     }

     public ExchangeException(String key, String msg) {

          super(msg);
          this.msgEnum = ExchangeExceptionsMessagesEnum.getEnum(key);
     }

     public static void checkThrow(boolean expression, ExchangeExceptionsMessagesEnum exceptionsMessagesEnum) throws ExchangeException {

          if (expression) {
               exceptionsMessagesEnum.raise();
          }
     }
}
