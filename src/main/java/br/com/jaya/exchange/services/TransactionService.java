
package br.com.jaya.exchange.services;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jaya.exchange.entities.Transaction;
import br.com.jaya.exchange.exception.ExchangeException;
import br.com.jaya.exchange.exception.ExchangeExceptionsMessagesEnum;
import br.com.jaya.exchange.repositories.TransactionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {

     private final TransactionRepository transactionRepository;

     public Transaction find(Long id) {

          Optional<Transaction> transaction = transactionRepository.findById(id);
          ExchangeException.checkThrow(!transaction.isPresent(), ExchangeExceptionsMessagesEnum.TRANSACTION_NOT_FOUND);

          return transaction.get();
     }

     public Page<Transaction> list(Transaction transaction, Pageable pageable) {

          Example<Transaction> filter = Example.of(transaction);

          return transactionRepository.findAll(filter, pageable);
     }

     public Transaction save(Transaction transaction) {

          return transactionRepository.save(transaction);
     }
}
