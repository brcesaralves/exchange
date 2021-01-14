
package br.com.jaya.exchange.exchange.integrationtests.resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.com.jaya.exchange.dto.persist.UserPersist;
import br.com.jaya.exchange.dto.update.UserUpdate;
import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.exchange.integrationtests.GlobalIntegrationTest;
import br.com.jaya.exchange.exchange.integrationtests.factories.UserTestDataFactory;
import io.restassured.RestAssured;

public class UserResourceIntegrationTest extends GlobalIntegrationTest {

     @Autowired
     private UserTestDataFactory userTestDataFactory;

     private User user;

     private static final Long ID = 999999999L;

     @Override
     public void setup() {

          user = userTestDataFactory.findUser();
     }

     @Override
     public void finish() {}

     @Test
     public void testSave() {

          UserPersist userPersist = new UserPersist();
          userPersist.setName(RandomStringUtils.random(100));

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(userPersist).when().post("/users").then()
               .statusCode(HttpStatus.CREATED.value())
               .and()
               .body("id", Matchers.notNullValue())
               .body("name", Matchers.equalTo(userPersist.getName()));
     }

     @Test
     public void testSaveValidError() {

          UserPersist userPersist = new UserPersist();
          userPersist.setName(RandomStringUtils.random(101));

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(userPersist).when().post("/users").then()
               .statusCode(HttpStatus.BAD_REQUEST.value());
     }

     @Test
     public void testFind() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).pathParam("id", user.getId())
               .when().get("/users/{id}").then()
               .statusCode(HttpStatus.OK.value())
               .and()
               .body("id", Matchers.equalTo(user.getId().intValue()))
               .body("name", Matchers.equalTo(user.getName()));
     }

     @Test
     public void testFindNotFound() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).pathParam("id", ID)
               .when().get("/users/{id}").then()
               .statusCode(HttpStatus.NOT_FOUND.value());
     }

     @Test
     public void testList() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).when().get("/users").then()
               .statusCode(HttpStatus.OK.value())
               .and()
               .body("numberOfElements", Matchers.not(0));
     }

     @Test
     public void testUpdate() {

          UserUpdate userUpdate = new UserUpdate();
          userUpdate.setName(RandomStringUtils.random(10));

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).pathParam("id", user.getId()).body(userUpdate).when().put("/users/{id}").then()
               .statusCode(HttpStatus.OK.value())
               .and()
               .body("id", Matchers.equalTo(user.getId().intValue()))
               .body("name", Matchers.equalTo(userUpdate.getName()));
     }

     @Test
     public void testUpdateNotFound() {

          UserUpdate userUpdate = new UserUpdate();
          userUpdate.setName(RandomStringUtils.random(10));

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).pathParam("id", ID).body(userUpdate).when().put("/users/{id}").then()
               .statusCode(HttpStatus.NOT_FOUND.value());
     }

     @Test
     public void testDelete() {

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).pathParam("id", user.getId()).when().delete("/users/{id}").then()
               .statusCode(HttpStatus.NO_CONTENT.value());
     }

     @Test
     public void testDeleteNotFound() {

          UserUpdate userUpdate = new UserUpdate();
          userUpdate.setName(RandomStringUtils.random(10));

          RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).pathParam("id", ID).when().delete("/users/{id}").then()
               .statusCode(HttpStatus.NOT_FOUND.value());
     }
}
