
package br.com.jaya.exchange.resource.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(value = "/api/v1/exchange", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExchangeResource {

}
