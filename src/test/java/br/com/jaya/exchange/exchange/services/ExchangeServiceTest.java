
package br.com.jaya.exchange.exchange.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.jaya.exchange.dto.persist.ExchangePersist;
import br.com.jaya.exchange.dto.requests.ExchangeRequest;
import br.com.jaya.exchange.dto.responses.ExchangeResponse;
import br.com.jaya.exchange.dto.responses.Rate;
import br.com.jaya.exchange.dto.responses.RateResponse;
import br.com.jaya.exchange.entities.Transaction;
import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.enums.Currencies;
import br.com.jaya.exchange.exception.ExchangeBadRequestException;
import br.com.jaya.exchange.services.ExchangeService;
import br.com.jaya.exchange.services.TransactionService;
import br.com.jaya.exchange.services.UserService;

@RunWith(SpringRunner.class)
public class ExchangeServiceTest {

     @InjectMocks
     private ExchangeService exchangeService;

     @Mock
     private RestTemplate restTemplate;

     @Mock
     private UserService userService;

     @Mock
     private TransactionService transactionService;

     private ExchangeRequest exchangeRequest;

     private RateResponse rateResponse;

     private Rate rate;

     private ExchangePersist exchangePersist;

     private User user;

     private ExchangeResponse exchangeResponse;

     private Transaction transaction;

     @Before
     public void before() {

          exchangeRequest = new ExchangeRequest();
          exchangeRequest.setCurrency(Currencies.USD);

          rate = new Rate();
          rate.setBrl(BigDecimal.valueOf(RandomUtils.nextLong()));
          rate.setEur(BigDecimal.valueOf(RandomUtils.nextLong()));
          rate.setJpy(BigDecimal.valueOf(RandomUtils.nextLong()));
          rate.setUsd(BigDecimal.valueOf(RandomUtils.nextLong()));

          rateResponse = new RateResponse();
          rateResponse.setBase(exchangeRequest.getCurrency().name());
          rateResponse.setDate(LocalDate.now());
          rateResponse.setRates(rate);

          exchangePersist = new ExchangePersist();
          exchangePersist.setOriginCurrency(Currencies.JPY);
          exchangePersist.setOriginValue(BigDecimal.valueOf(RandomUtils.nextFloat()));
          exchangePersist.setTargetCurrency(Currencies.BRL);
          exchangePersist.setUserId(RandomUtils.nextLong());

          user = new User();
          user.setId(RandomUtils.nextLong());
          user.setName(RandomStringUtils.random(100));

          exchangeResponse = new ExchangeResponse();
          exchangeResponse.setId(RandomUtils.nextLong());
          exchangeResponse.setOriginCurrency(Currencies.JPY.name());
          exchangeResponse.setOriginValue(BigDecimal.valueOf(RandomUtils.nextFloat()));
          exchangeResponse.setRate(BigDecimal.valueOf(RandomUtils.nextFloat()));
          exchangeResponse.setTargetCurrency(Currencies.BRL.name());
          exchangeResponse.setTargetValue(BigDecimal.valueOf(RandomUtils.nextFloat()));
          exchangeResponse.setTimestamp(ZonedDateTime.now());
          exchangeResponse.setUserId(RandomUtils.nextLong());

          transaction = new Transaction();
          transaction.setId(exchangeResponse.getId());
          transaction.setOriginCurrency(exchangeResponse.getOriginCurrency());
          transaction.setOriginValue(exchangeResponse.getOriginValue());
          transaction.setRate(exchangeResponse.getRate());
          transaction.setTargetCurrency(exchangeResponse.getTargetCurrency());
          transaction.setTargetValue(exchangeResponse.getTargetValue());
          transaction.setTimestamp(exchangeResponse.getTimestamp());
          transaction.setUserId(exchangeResponse.getUserId());
     }

     @Test
     public void testListRate() {

          ResponseEntity<RateResponse> responseMock = new ResponseEntity<RateResponse>(rateResponse, HttpStatus.OK);

          when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseMock);

          RateResponse response = exchangeService.listRate(exchangeRequest);

          assertNotNull(response);
          assertEquals(rateResponse.getBase(), response.getBase());
          assertEquals(rateResponse.getDate(), response.getDate());
          assertEquals(rateResponse.getRates(), response.getRates());
     }

     @Test(expected = ExchangeBadRequestException.class)
     public void testListRateHttpClientErrorException() {

          HttpClientErrorException httpClientErrorException = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
          when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenThrow(httpClientErrorException);

          exchangeService.listRate(exchangeRequest);
     }

     @Test(expected = ExchangeBadRequestException.class)
     public void testListRateHttpServerErrorException() {

          HttpServerErrorException httpServerErrorException = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
          when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenThrow(httpServerErrorException);

          exchangeService.listRate(exchangeRequest);
     }

     @Test
     public void testExchange() {

          ResponseEntity<RateResponse> responseMock = new ResponseEntity<RateResponse>(rateResponse, HttpStatus.OK);

          when(userService.find(anyLong())).thenReturn(user);
          when(transactionService.save(any(Transaction.class))).thenReturn(transaction);
          when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseMock);

          ExchangeResponse response = exchangeService.exchange(exchangePersist);

          assertNotNull(response);
          assertEquals(exchangeResponse.getId(), response.getId());
          assertEquals(exchangeResponse.getOriginCurrency(), response.getOriginCurrency());
          assertEquals(exchangeResponse.getOriginValue(), response.getOriginValue());
          assertEquals(exchangeResponse.getRate(), response.getRate());
          assertEquals(exchangeResponse.getTargetCurrency(), response.getTargetCurrency());
          assertEquals(exchangeResponse.getTargetValue(), response.getTargetValue());
          assertEquals(exchangeResponse.getTimestamp(), response.getTimestamp());
          assertEquals(exchangeResponse.getUserId(), response.getUserId());
     }

     @Test(expected = ExchangeBadRequestException.class)
     public void testExchangeErrorResponse() {

          rateResponse.getRates().setBrl(null);
          rateResponse.getRates().setEur(null);
          rateResponse.getRates().setJpy(null);
          rateResponse.getRates().setUsd(null);
          ResponseEntity<RateResponse> responseMock = new ResponseEntity<RateResponse>(rateResponse, HttpStatus.OK);

          when(userService.find(anyLong())).thenReturn(user);
          when(transactionService.save(any(Transaction.class))).thenReturn(transaction);
          when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseMock);

          exchangeService.exchange(exchangePersist);
     }
}
