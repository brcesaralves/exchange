
package br.com.jaya.exchange.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.jaya.exchange.dto.responses.ExchangeResponse;
import br.com.jaya.exchange.exception.ExchangeExceptionsMessagesEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ExchangeService {

//     @Value("${url.exchange.rate.api}")
//     private String url;

     public ExchangeResponse exchange() {

          RestTemplate restTemplate = new RestTemplate();

          ExchangeResponse response = null;

          try {

               response = restTemplate.getForObject("https://api.exchangeratesapi.io/latest?base=USD", ExchangeResponse.class);

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

          return response;
     }
}
