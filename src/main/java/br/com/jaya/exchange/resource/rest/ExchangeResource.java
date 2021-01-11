
package br.com.jaya.exchange.resource.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jaya.exchange.dto.persist.ExchangePersist;
import br.com.jaya.exchange.dto.requests.ExchangeRequest;
import br.com.jaya.exchange.dto.responses.ExchangeResponse;
import br.com.jaya.exchange.dto.responses.RateResponse;
import br.com.jaya.exchange.services.ExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Exchange" })
@RestController
@RequestMapping(value = "/api/v1/exchange", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExchangeResource {

     private final ExchangeService exchangeService;

     public ExchangeResource(ExchangeService exchangeService) {

          this.exchangeService = exchangeService;
     }

     @GetMapping("/rates")
     @ApiOperation(value = "List rates", notes = "This feature allows you to list rates.", response = RateResponse.class, nickname = "listRates")
     public ResponseEntity<RateResponse> list(
               @Validated @ModelAttribute(value = "request") ExchangeRequest exchangeRequest) {

          RateResponse response = exchangeService.listRate(exchangeRequest);

          return ResponseEntity.ok(response);
     }

     @PostMapping()
     @ApiOperation(value = "Exchange", notes = "This feature allows you to convert between currencies.", response = ExchangeResponse.class, nickname = "exchange")
     public ResponseEntity<ExchangeResponse> exchange(
               @Validated @RequestBody ExchangePersist exchangePersist) {

          ExchangeResponse response  = exchangeService.exchange(exchangePersist);

          return ResponseEntity.ok(response);
     }
}
