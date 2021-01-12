
package br.com.jaya.exchange.exchange.integrationtests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import br.com.jaya.exchange.dto.responses.RateResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeResourceIntegrationTest {

     @LocalServerPort
     private int port;

     @Test
     public void testList() {

          RateResponse rateResponse = given().contentType("application/json").port(port).when().get("/api/v1/exchange/rates").then().statusCode(200).extract().as(RateResponse.class);

          assertNotNull(rateResponse);
          //assertEquals(rateResponse);
     }
}
