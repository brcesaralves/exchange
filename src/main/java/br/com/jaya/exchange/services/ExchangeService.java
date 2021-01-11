
package br.com.jaya.exchange.services;

import static br.com.jaya.exchange.util.Constants.URL_EXCHANGE_RATE_API;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.ZonedDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jaya.exchange.converters.GenericConvert;
import br.com.jaya.exchange.dto.persist.ExchangePersist;
import br.com.jaya.exchange.dto.requests.ExchangeRequest;
import br.com.jaya.exchange.dto.responses.ExchangeResponse;
import br.com.jaya.exchange.dto.responses.RateResponse;
import br.com.jaya.exchange.entities.Transaction;
import br.com.jaya.exchange.exception.ExchangeExceptionsMessagesEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ExchangeService {

     private UserService userService;

     private TransactionService transactionService;

     public RateResponse listRate(ExchangeRequest exchangeRequest) {

          MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
          parameters.add("base", exchangeRequest.getCurrency().name());

          UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(URL_EXCHANGE_RATE_API);
          uriComponentsBuilder.queryParams(parameters);

          HttpHeaders headers = new HttpHeaders();
          headers.set("Accept", "application/json");

          HttpEntity entity = new HttpEntity(headers);

          RestTemplate restTemplate = new RestTemplate();

          HttpEntity<RateResponse> response = null;

          try {

               response = restTemplate.exchange(uriComponentsBuilder.build().encode().toUri(), HttpMethod.GET, entity, RateResponse.class);

          } catch (HttpClientErrorException e) {

               log.error(e.getMessage());
               ExchangeExceptionsMessagesEnum.ERROR_CONSUMER_EXCHANGE_RATE_API.raiseLogError(e.getResponseBodyAsString());

          } catch (HttpServerErrorException e) {

               log.error(e.getMessage());
               ExchangeExceptionsMessagesEnum.ERROR_CONSUMER_EXCHANGE_RATE_API.raiseLogError(e.getResponseBodyAsString());

          } catch (Exception e) {

               log.error(e.getMessage());
               ExchangeExceptionsMessagesEnum.ERROR_CONSUMER_EXCHANGE_RATE_API.raiseLogError(e.getMessage());
          }

          return response.getBody();
     }

     @Transactional
     public ExchangeResponse exchange(ExchangePersist exchangePersist) {

          userService.find(exchangePersist.getUserId());

          ExchangeRequest exchangeRequest = new ExchangeRequest();
          exchangeRequest.setCurrency(exchangePersist.getOriginCurrency());

          RateResponse rateResponse = listRate(exchangeRequest);

          BigDecimal rate = rateResponse.rateOf(exchangePersist.getTargetCurrency().getCode());

          BigDecimal targetValue = calculateExchange(exchangePersist.getOriginValue(), rate);

          Transaction transaction = new Transaction();
          transaction.setOriginCurrency(exchangePersist.getOriginCurrency().name());
          transaction.setOriginValue(exchangePersist.getOriginValue());
          transaction.setRate(BigDecimal.ONE.divide(rate, MathContext.DECIMAL128));
          transaction.setTargetCurrency(exchangePersist.getTargetCurrency().name());
          transaction.setTargetValue(targetValue);
          transaction.setTimestamp(ZonedDateTime.now());
          transaction.setUserId(exchangePersist.getUserId());

          transaction = transactionService.save(transaction);

          ExchangeResponse response = GenericConvert.convertModelMapper(transaction, ExchangeResponse.class);

          return response;
     }

     private BigDecimal calculateExchange(BigDecimal originValue, BigDecimal rate) {

          return originValue = originValue.multiply(rate);
     }
}
