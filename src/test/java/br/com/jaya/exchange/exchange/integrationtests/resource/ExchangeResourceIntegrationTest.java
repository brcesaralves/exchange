
package br.com.jaya.exchange.exchange.integrationtests.resource;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.jaya.exchange.dto.persist.ExchangePersist;
import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.enums.Currencies;
import br.com.jaya.exchange.exchange.integrationtests.GlobalIntegrationTest;
import br.com.jaya.exchange.exchange.integrationtests.factories.UserTestDataFactory;
import io.restassured.RestAssured;

public class ExchangeResourceIntegrationTest extends GlobalIntegrationTest {

     @Autowired
     private UserTestDataFactory userTestDataFactory;

     private User user;

     private ExchangePersist exchangePersist;

     private static final Long ID = 999999999L;

     @Override
     public void setup() {

          user = userTestDataFactory.findUser();

          exchangePersist = new ExchangePersist();
          exchangePersist.setOriginCurrency(Currencies.BRL);
          exchangePersist.setOriginValue(BigDecimal.valueOf(RandomUtils.nextLong(1, 9999)));
          exchangePersist.setTargetCurrency(Currencies.USD);
          exchangePersist.setUserId(user.getId());
     }

     @Override
     public void finish() {}

     @Test
     public void testListRates() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).queryParam("currency", "USD").when().get("/exchange/rates").then()
               .statusCode(HttpStatus.OK.value());
     }

     @Test
     public void testExchange() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(exchangePersist).when().post("/exchange").then()
               .statusCode(HttpStatus.OK.value())
               .and()
               .body("originCurrency", Matchers.equalTo(exchangePersist.getOriginCurrency().name()))
               .body("originValue", Matchers.notNullValue())
               .body("rate", Matchers.notNullValue())
               .body("targetCurrency", Matchers.equalTo(exchangePersist.getTargetCurrency().name()))
               .body("targetValue", Matchers.notNullValue())
               .body("transactionId", Matchers.notNullValue())
               .body("userId", Matchers.equalTo(user.getId().intValue()));
     }

     @Test
     public void testExchangeUserNotFound() {

          exchangePersist.setUserId(ID);

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(exchangePersist).when().post("/exchange").then()
               .statusCode(HttpStatus.NOT_FOUND.value());
     }
}
