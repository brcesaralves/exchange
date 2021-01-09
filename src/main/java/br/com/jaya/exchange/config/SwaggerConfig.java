
package br.com.jaya.exchange.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiExchange() {
        return new Docket(DocumentationType.SWAGGER_2)
        		//.groupName("REST API EXCHANGE V1")
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("br.com.jaya.exchange"))
        		.paths(regex("/.*"))
        		.build()
        		.apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

          return new ApiInfo(
          		"Exchange API Specification", 
          		"Currency conversion application", 
          		"V1", 
          		"www.jaya.tech", 
          		new Contact("Jaya", "www.jaya.tech", "contact@jaya.tech"), 
          		"License of API", 
          		"licenseUrl",
          		Collections.emptyList()
          );
    }

}