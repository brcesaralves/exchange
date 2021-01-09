
package br.com.jaya.exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jaya.exchange.entities.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

}
