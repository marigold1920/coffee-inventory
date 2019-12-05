package coffee.inventory.builder;

import java.util.HashSet;

import coffee.inventory.entity.Transaction;
import coffee.inventory.helper.ServiceHelper;

public class TransactionBuilder {
    protected Transaction transaction;
    protected ServiceHelper helper = ServiceHelper.getHelper();

    public TransactionBuilder() {
        transaction = Transaction.builder().transactionDetails(new HashSet<>()).build();
    }

    public Transaction build() {

        return transaction;
    }
}