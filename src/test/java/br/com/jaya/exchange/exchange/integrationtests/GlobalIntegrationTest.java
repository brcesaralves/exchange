
package br.com.jaya.exchange.exchange.integrationtests;

import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class GlobalIntegrationTest {

     @LocalServerPort
     private int serverPort;

     @ClassRule
     public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

     @Rule
     public final SpringMethodRule springMethodRule = new SpringMethodRule();

     @Before
     public abstract void setup();

     @After
     public abstract void finish();

     @PostConstruct
     public void init() {

          configureRestAssured();
     }

     private void configureRestAssured() {

          RestAssured.port = serverPort;
          RestAssured.basePath = "/api";
     }
}
