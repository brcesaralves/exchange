
package br.com.jaya.exchange.resource.rest;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jaya.exchange.converters.GenericConvert;
import br.com.jaya.exchange.dto.requests.TransactionRequest;
import br.com.jaya.exchange.entities.Transaction;
import br.com.jaya.exchange.services.TransactionService;
import br.com.jaya.exchange.util.Pageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Transaction" })
@RestController
@RequestMapping(path = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionResource {

     private final TransactionService transactionService;

     public TransactionResource(TransactionService transactionService) {

          this.transactionService = transactionService;
     }

     @GetMapping()
     @ApiOperation(value = "List transactions", notes = "This feature allows you to list transactions.", response = Transaction.class, nickname = "listTransactions")
     public Page<Transaction> list(
               @Validated @ModelAttribute(value = "request") TransactionRequest transactionRequest) {

          Transaction transaction = GenericConvert.convertModelMapper(transactionRequest, Transaction.class);
          Pageable pageable = Pageable.setPageable(transactionRequest.getPage(), transactionRequest.getLimit());

          return transactionService.list(transaction, pageable);
     }
}
