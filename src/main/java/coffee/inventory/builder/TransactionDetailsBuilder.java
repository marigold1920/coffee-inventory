package coffee.inventory.builder;

import java.util.stream.Collectors;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.entity.TransactionDetail;
import coffee.inventory.helper.ServiceHelper;

public class TransactionDetailsBuilder extends TransactionBuilder {

    public TransactionDetailsBuilder(ServiceHelper service) {
        super(service);
    }

    public TransactionDetailsBuilder make(TransactionAdapter transactionAdapter) {
        transaction.setTransactionDetails(transactionAdapter.getItems().parallelStream()
                .map(i -> TransactionDetail.builder().transaction(transaction).item(helper.getItem(i)).quantity(i.getQuantity()).build())
                .collect(Collectors.toSet()));
        transaction.setFromWareHouse(helper.getWarehouse(transactionAdapter.getSource()));
        transaction.setToWareHouse(helper.getWarehouse(transactionAdapter.getDestination()));
        transaction.setType(transactionAdapter.getType());
        return this;
    }
}