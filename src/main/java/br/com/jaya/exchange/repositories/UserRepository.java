
package br.com.jaya.exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jaya.exchange.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
