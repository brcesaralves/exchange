
package br.com.jaya.exchange.exchange.integrationtests.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.exchange.integrationtests.repository.UserIntegrationTestRepository;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class UserTestDataFactory {

     @Autowired
     private UserIntegrationTestRepository userIntegrationTestRepository;

     public User findUser() {

          return userIntegrationTestRepository.findUser();
     }
}
