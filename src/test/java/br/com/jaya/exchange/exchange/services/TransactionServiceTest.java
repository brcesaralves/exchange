
package br.com.jaya.exchange.exchange.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import br.com.jaya.exchange.entities.Transaction;
import br.com.jaya.exchange.enums.Currencies;
import br.com.jaya.exchange.exception.ExchangeNotFoundException;
import br.com.jaya.exchange.repositories.TransactionRepository;
import br.com.jaya.exchange.services.TransactionService;
import br.com.jaya.exchange.util.Pageable;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

     @InjectMocks
     private TransactionService transactionService;

     @Mock
     private TransactionRepository transactionRepository;

     private Transaction transaction;

     private Optional<Transaction> transactionOptional;

     private List<Transaction> listTransactions;

     private Page<Transaction> transactionPage;

     private Pageable pageable;

     private final Integer PAGE = 0;

     private final Integer LIMIT = 10;

     private final Integer ZERO = 0;

     @Before
     public void before() {

          transaction = new Transaction();
          transaction.setId(RandomUtils.nextLong());
          transaction.setOriginCurrency(Currencies.BRL.name());
          transaction.setOriginValue(BigDecimal.valueOf(RandomUtils.nextFloat()));
          transaction.setRate(BigDecimal.valueOf(RandomUtils.nextFloat()));
          transaction.setTargetCurrency(Currencies.EUR.name());
          transaction.setTargetValue(BigDecimal.valueOf(RandomUtils.nextFloat()));
          transaction.setTimestamp(ZonedDateTime.now());
          transaction.setUserId(RandomUtils.nextLong());

          transactionOptional = Optional.of(transaction);

          listTransactions = Lists.newArrayList();
          listTransactions.add(transaction);

          pageable = Pageable.setPageable(PAGE, LIMIT);

          transactionPage = new PageImpl<Transaction>(listTransactions, pageable, listTransactions.size());
     }

     @SuppressWarnings("unchecked")
     @Test
     public void testListTransactions() {

          when(transactionRepository.findAll(any(Example.class), any(Pageable.class))).thenReturn(transactionPage);

          Page<Transaction> response = transactionService.list(transaction, pageable);

          assertNotNull(response);
          assertTrue(response.getContent().size() > ZERO);
          assertEquals(transaction.getId(), response.getContent().get(ZERO).getId());
          assertEquals(transaction.getOriginCurrency(), response.getContent().get(ZERO).getOriginCurrency());
          assertEquals(transaction.getOriginValue(), response.getContent().get(ZERO).getOriginValue());
          assertEquals(transaction.getRate(), response.getContent().get(ZERO).getRate());
          assertEquals(transaction.getTargetCurrency(), response.getContent().get(ZERO).getTargetCurrency());
          assertEquals(transaction.getTargetValue(), response.getContent().get(ZERO).getTargetValue());
          assertEquals(transaction.getTimestamp(), response.getContent().get(ZERO).getTimestamp());
          assertEquals(transaction.getUserId(), response.getContent().get(ZERO).getUserId());
     }

     @SuppressWarnings("unchecked")
     @Test
     public void testListUsersEmpty() {

          transactionPage = new PageImpl<Transaction>(Lists.newArrayList(), pageable, ZERO);

          when(transactionRepository.findAll(any(Example.class), any(Pageable.class))).thenReturn(transactionPage);

          Page<Transaction> response = transactionService.list(transaction, pageable);

          assertNotNull(response);
          assertEquals(response.getContent().size(), 0);
     }

     @Test
     public void testFindTransaction() {

          when(transactionRepository.findById(anyLong())).thenReturn(transactionOptional);

          Transaction response = transactionService.find(transaction.getId());

          assertNotNull(response);
          assertEquals(transaction.getId(), response.getId());
          assertEquals(transaction.getOriginCurrency(), response.getOriginCurrency());
          assertEquals(transaction.getOriginValue(), response.getOriginValue());
          assertEquals(transaction.getRate(), response.getRate());
          assertEquals(transaction.getTargetCurrency(), response.getTargetCurrency());
          assertEquals(transaction.getTargetValue(), response.getTargetValue());
          assertEquals(transaction.getTimestamp(), response.getTimestamp());
          assertEquals(transaction.getUserId(), response.getUserId());
     }

     @Test(expected = ExchangeNotFoundException.class)
     public void testFindUserNotFound() {

          when(transactionRepository.findById(anyLong())).thenReturn(Optional.empty());
          transactionService.find(transaction.getId());
     }

     @Test
     public void testSave() {

          when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

          Transaction response = new Transaction();
          response.setOriginCurrency(transaction.getOriginCurrency());
          response.setOriginValue(transaction.getOriginValue());
          response.setRate(transaction.getRate());
          response.setTargetCurrency(transaction.getTargetCurrency());
          response.setTargetValue(transaction.getTargetValue());
          response.setTimestamp(transaction.getTimestamp());
          response.setUserId(transaction.getUserId());

          response = transactionService.save(response);

          assertNotNull(response);
          assertEquals(transaction.getId(), response.getId());
          assertEquals(transaction.getOriginCurrency(), response.getOriginCurrency());
          assertEquals(transaction.getOriginValue(), response.getOriginValue());
          assertEquals(transaction.getRate(), response.getRate());
          assertEquals(transaction.getTargetCurrency(), response.getTargetCurrency());
          assertEquals(transaction.getTargetValue(), response.getTargetValue());
          assertEquals(transaction.getTimestamp(), response.getTimestamp());
          assertEquals(transaction.getUserId(), response.getUserId());
     }
}
