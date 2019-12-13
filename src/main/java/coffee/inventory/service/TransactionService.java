package coffee.inventory.service;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.enumeration.TransactionStatus;

public interface TransactionService {
    ResponseModel saveTransaction(Adapter transactionAdapter, ServiceHelper service);

    ResponseModel finishTransaction(Integer transactionId, TransactionStatus status);
}