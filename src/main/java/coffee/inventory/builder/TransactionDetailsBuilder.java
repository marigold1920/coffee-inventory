package coffee.inventory.builder;

import java.util.stream.Collectors;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.entity.TransactionDetail;
import coffee.inventory.helper.PoolService;

public class TransactionDetailsBuilder extends TransactionBuilder {

    public TransactionDetailsBuilder(PoolService poolService) {
        super(poolService);
    }

    public TransactionDetailsBuilder make(TransactionAdapter transactionAdapter) {
        transaction.setTransactionDetails(transactionAdapter.getItems().parallelStream()
                .map(i -> TransactionDetail.builder().transaction(transaction).item(poolService.getItem(i)).quantity(i.getQuantity()).build())
                .collect(Collectors.toSet()));
        transaction.setFromWareHouse(poolService.getWarehouse(transactionAdapter.getSource()));
        transaction.setToWareHouse(poolService.getWarehouse(transactionAdapter.getDestination()));
        transaction.setType(transactionAdapter.getType());
        return this;
    }
}