
package br.com.jaya.exchange.exchange.integrationtests.resource;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.jaya.exchange.exchange.integrationtests.GlobalIntegrationTest;
import io.restassured.RestAssured;

public class TransactionResourceIntegrationTest extends GlobalIntegrationTest {

     @Override
     public void setup() {}

     @Override
     public void finish() {}

     @Test
     public void testList() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).when().get("/transactions").then()
               .statusCode(HttpStatus.OK.value())
               .and()
               .body("numberOfElements", Matchers.not(0));
     }
}
