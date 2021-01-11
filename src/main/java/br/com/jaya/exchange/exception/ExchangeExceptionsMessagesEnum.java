
package br.com.jaya.exchange.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Objects;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ExchangeExceptionsMessagesEnum {

     GLOBAL_SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "global server error", ExchangeServerErrorException.class),

     USER_NOT_FOUND(NOT_FOUND.value(), "user not found", ExchangeNotFoundException.class),

     TRANSACTION_NOT_FOUND(NOT_FOUND.value(), "transaction not found", ExchangeNotFoundException.class),

     ERROR_CONSUMER_EXCHANGE_RATE_API(BAD_REQUEST.value(), "external API call error", ExchangeBadRequestException.class);

     @Getter
     private Integer codigo;

     @Getter
     private String key;

     @Getter
     private Class<? extends ExchangeException> klass;

     ExchangeExceptionsMessagesEnum(int codigo, String key, Class<? extends ExchangeException> klass) {

          this.key = key;
          this.klass = klass;
          this.codigo = codigo;
     }

     public static ExchangeExceptionsMessagesEnum getEnum(String key) {

          for (ExchangeExceptionsMessagesEnum e : ExchangeExceptionsMessagesEnum.values()) {

               if (e.getKey().equals(key)) {
                    return e;
               }
          }

          return null;
     }

     public String getMessage() {

          return this.key;
     }

     public void raise() {

          log.error("Raising error: {}", this);
          raiseException(getMessage());
     }

     private void raiseException(String msg) {

          if (this.badRequest()) {

               throw new ExchangeBadRequestException(key, msg);

          } else if (this.notFound()) {

               throw new ExchangeNotFoundException(key, msg);

          } else if (this.serverError()) {

               throw new ExchangeServerErrorException(key, msg);

          }
     }

     public void raiseLogError(String... textoErro) {

          if (Objects.nonNull(textoErro)) {

               for (String erro : textoErro) {
                    log.error(erro);
               }
          }

          raise();
     }

     private Boolean notFound() {

          return this.codigo == NOT_FOUND.value();
     }

     private Boolean badRequest() {

          return this.codigo == BAD_REQUEST.value();
     }

     private Boolean serverError() {

          return this.codigo == INTERNAL_SERVER_ERROR.value();
     }
}
