
package br.com.jaya.exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jaya.exchange.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
