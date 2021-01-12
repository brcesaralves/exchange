
package br.com.jaya.exchange.services;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.exception.ExchangeException;
import br.com.jaya.exchange.exception.ExchangeExceptionsMessagesEnum;
import br.com.jaya.exchange.repositories.UserRepository;
import br.com.jaya.exchange.util.Pageable;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

     private final UserRepository userRepository;

     public User find(Long id) {

          Optional<User> user = userRepository.findById(id);
          ExchangeException.checkThrow(!user.isPresent(), ExchangeExceptionsMessagesEnum.USER_NOT_FOUND);

          return user.get();
     }

     public Page<User> list(User user, Pageable pageable) {

          ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
          Example<User> filter = Example.of(user, matcher);

          return userRepository.findAll(filter, pageable);
     }

     public User save(User user) {

          return userRepository.save(user);
     }

     public User update(User user) {

          find(user.getId());
          user = userRepository.save(user);

          return user;
     }

     public void delete(Long id) {

          find(id);
          userRepository.deleteById(id);
     }
}
