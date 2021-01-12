
package br.com.jaya.exchange.exchange.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.jaya.exchange.ExchangeApplication;
import br.com.jaya.exchange.dto.requests.ExchangeRequest;
import br.com.jaya.exchange.dto.responses.Rate;
import br.com.jaya.exchange.dto.responses.RateResponse;
import br.com.jaya.exchange.enums.Currencies;
import br.com.jaya.exchange.services.ExchangeService;

@RunWith(SpringRunner.class)
public class ExchangeServiceTest {

//     @InjectMocks
//     private ExchangeService exchangeService;
//
//     @Mock
//     private RestTemplate restTemplate;
//
//     private ExchangeRequest exchangeRequest;
//
//     private RateResponse rateResponse;
//
//     private Rate rate;
//
//     @Before
//     public void before() {
//
//          exchangeRequest = new ExchangeRequest();
//          exchangeRequest.setCurrency(Currencies.USD);
//
//          rate = new Rate();
//          rate.setBrl(BigDecimal.valueOf(RandomUtils.nextLong()));
//          rate.setEur(BigDecimal.valueOf(RandomUtils.nextLong()));
//          rate.setJpy(BigDecimal.valueOf(RandomUtils.nextLong()));
//          rate.setUsd(BigDecimal.valueOf(RandomUtils.nextLong()));
//
//          rateResponse = new RateResponse();
//          rateResponse.setBase(exchangeRequest.getCurrency().name());
//          rateResponse.setDate(LocalDate.now());
//          rateResponse.setRates(rate);
//     }
//
//     @Test
//     public void testExchange() {
//
//          ResponseEntity<RateResponse> responseMock = new ResponseEntity<RateResponse>(rateResponse, HttpStatus.OK);
//
//          when(restTemplate.exchange(any(URI.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseMock);
//
//          RateResponse response = exchangeService.listRate(exchangeRequest);
//
//          assertNotNull(response);
//          assertEquals(rateResponse.getBase(), response.getBase());
//          assertEquals(rateResponse.getDate(), response.getDate());
//          assertEquals(rateResponse.getRates(), response.getRates());
//     }
}
