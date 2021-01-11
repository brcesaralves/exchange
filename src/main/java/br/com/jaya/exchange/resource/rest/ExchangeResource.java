
package br.com.jaya.exchange.resource.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jaya.exchange.dto.responses.ExchangeResponse;
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

     @GetMapping()
     @ApiOperation(value = "List rates", notes = "This feature allows you to list rates.", response = ExchangeResponse.class, nickname = "listRates")
     public ResponseEntity<ExchangeResponse> list() {

          ExchangeResponse response = exchangeService.exchange();

          return ResponseEntity.ok(response);
     }
}
