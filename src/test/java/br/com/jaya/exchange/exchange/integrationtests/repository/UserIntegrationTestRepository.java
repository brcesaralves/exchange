
package br.com.jaya.exchange.exchange.integrationtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jaya.exchange.entities.User;

@Repository
public interface UserIntegrationTestRepository extends JpaRepository<User, Long> {

     @Query(value = "SELECT * FROM USERS limit 1", nativeQuery = true)
     User findUser();
}
