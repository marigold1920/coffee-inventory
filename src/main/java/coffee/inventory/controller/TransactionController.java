package coffee.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.enumeration.TransactionStatus;
import coffee.inventory.service.TransactionService;
import coffee.inventory.service.implement.TransactionServiceImpl;

@RestController
@RequestMapping(value = "api/v1.0/transactions", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseModel saveTransaction(@RequestBody TransactionAdapter transactionAdapter) {
        ResponseModel response = transactionService.saveTransaction(transactionAdapter);

        return response;
    }

    @PutMapping(value = "/{transactionId}")
    public ResponseModel finishTransaction(@PathVariable("transactionId") Integer transactionId, @RequestParam TransactionStatus status) {

        return transactionService.finishTransaction(transactionId, status);
    }
}