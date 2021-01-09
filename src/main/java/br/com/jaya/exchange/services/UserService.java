
package br.com.jaya.exchange.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

     private final UserRepository userRepository;

     public User find(Long id) {

          Optional<User> user = userRepository.findById(id);

          return user.get();
     }

     public Page<User> list(Pageable pageable) {

          return userRepository.findAll(pageable);
     }

}
