package coffee.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.service.TransactionDetailsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "api/v1.0/histories", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
public class TransactionDetailsController {

    private TransactionDetailsService transactionDetailsService;

    @Autowired
    public void setTransactionDetailsService(TransactionDetailsService transactionDetailsService) {
        this.transactionDetailsService = transactionDetailsService;
    }
    
    @GetMapping
    public ResponseModel getTransactionHistoriesOfItem(@RequestParam Integer itemId, Integer warehouseId) {

        return transactionDetailsService.findAllHistoriesOfItem(itemId, warehouseId);
    }
    
}