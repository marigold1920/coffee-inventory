package coffee.inventory.service;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.enumeration.TransactionStatus;

public interface TransactionService {
    ResponseModel saveTransaction(TransactionAdapter transactionAdapter);

    ResponseModel finishTransaction(Integer transactionId, TransactionStatus status);
}