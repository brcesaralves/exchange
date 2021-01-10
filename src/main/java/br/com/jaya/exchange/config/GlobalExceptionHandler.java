
package br.com.jaya.exchange.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import br.com.jaya.exchange.config.GlobalExceptionHandler.BindErrorException.ObjectErrorExchange;
import br.com.jaya.exchange.exception.ExchangeBadRequestException;
import br.com.jaya.exchange.exception.ExchangeException;
import br.com.jaya.exchange.exception.ExchangeExceptionsMessagesEnum;
import br.com.jaya.exchange.exception.ExchangeNotFoundException;
import br.com.jaya.exchange.exception.ExchangeServerErrorException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

     @ResponseStatus(HttpStatus.NOT_FOUND)
     @ExceptionHandler(ExchangeNotFoundException.class)
     public @ResponseBody ErroInfo handleExceptionNotFound(HttpServletResponse response, HttpServletRequest request, Exception exception) {

          ErroInfo erroInfo = buildErrorInfo(request, exception);
          return erroInfo;
     }

     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     @ExceptionHandler(ExchangeServerErrorException.class)
     public @ResponseBody ErroInfo handleExceptionServerError(HttpServletResponse response, HttpServletRequest request, Exception exception) {

          ErroInfo erroInfo = buildErrorInfo(request, exception);
          return erroInfo;
     }

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(ExchangeBadRequestException.class)
     public @ResponseBody ErroInfo handleExceptionBadRequest(HttpServletResponse response, HttpServletRequest request, Exception exception) {

          ErroInfo erroInfo = buildErrorInfo(request, exception);
          return erroInfo;
     }

     @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(MethodArgumentNotValidException.class)
     public @ResponseBody BindErrorException validationMethodArgumentNotValidException(HttpServletResponse response, HttpServletRequest request, MethodArgumentNotValidException exception) {

          BindErrorException bindErrorException = new BindErrorException();
          List<ObjectErrorExchange> errors = Lists.newArrayList();
          List<ObjectError> objectsError = exception.getBindingResult().getAllErrors();

          objectsError.forEach(objectError -> {
               FieldError fieldError = (FieldError) objectError;

               String code = StringUtils.startsWith(fieldError.getDefaultMessage(), "{{") ? fieldError.getDefaultMessage() : null;
               String message = Objects.nonNull(code) ? code : fieldError.getDefaultMessage();

               bindErrorException.timestamp = LocalDateTime.now();
               bindErrorException.status = 400;
               bindErrorException.exception = "MethodArgumentNotValidException";

               ObjectErrorExchange error = bindErrorException.new ObjectErrorExchange();
               error.defaultMessage = message;
               error.objectName = fieldError.getObjectName();
               error.field = fieldError.getField();
               error.code = fieldError.getCode();

               errors.add(error);
          });

          bindErrorException.errors = errors;

          return bindErrorException;
     }

     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     @ExceptionHandler(Exception.class)
     public @ResponseBody ErroInfo handleException(HttpServletResponse response, HttpServletRequest request, Exception exception) {

          ExchangeException exchangeException = new ExchangeException(ExchangeExceptionsMessagesEnum.GLOBAL_SERVER_ERROR);

          ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), exchangeException.getMsgEnum().getCodigo(), exchangeException.getClass().getSimpleName(), exchangeException.getMessage(), request.getRequestURI());

          return erroInfo;
     }

     public ErroInfo buildErrorInfo(HttpServletRequest request, Exception exception) {

          ExchangeException exceptionResponse;

          if (exception instanceof ExchangeException) {
               exceptionResponse = (ExchangeException) exception;
          } else {
               exceptionResponse = new ExchangeException(ExchangeExceptionsMessagesEnum.GLOBAL_SERVER_ERROR);
          }

          ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), exceptionResponse.getMsgEnum().getCodigo(), exceptionResponse.getClass().getSimpleName(), exceptionResponse.getMessage(), request.getRequestURI());
          return erroInfo;
     }

     @AllArgsConstructor
     @Getter
     public class ErroInfo {

          @DateTimeFormat(iso = ISO.DATE_TIME)
          public LocalDateTime timestamp;

          public Integer code;

          public String exception;

          public String message;

          public String path;
     }

     public class BindErrorException {

          @Getter
          @DateTimeFormat(iso = ISO.DATE_TIME)
          public LocalDateTime timestamp;

          @Getter
          public Integer status;

          @Getter
          public String exception;

          @Getter
          public List<ObjectErrorExchange> errors;

          public class ObjectErrorExchange {

               public String defaultMessage;

               public String objectName;

               public String field;

               public String code;
          }
     }
}
