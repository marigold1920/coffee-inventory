package coffee.inventory.service;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.common.ResponseModel;

public interface TransactionService {
    ResponseModel saveTransaction(TransactionAdapter transactionAdapter);
}