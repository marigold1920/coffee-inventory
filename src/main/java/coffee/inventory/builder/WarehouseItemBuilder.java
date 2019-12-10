package coffee.inventory.builder;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.PoolService;

public class WarehouseItemBuilder extends TransactionBuilder {

    public WarehouseItemBuilder(PoolService poolService) {
        super(poolService);
    }

    public WarehouseItemBuilder make(TransactionAdapter transactionAdapter) {
        if (transactionAdapter.getType() == TransactionType.RECEIPT) {
            transactionAdapter.getItems().stream().map(poolService::getWarehouseItem)
                    .forEach(i -> i.setWarehouse(poolService.getWarehouse(transactionAdapter.getDestination())));
        } else {
            transactionAdapter.getItems().stream()
                    .map(ItemAdapter::downQuantity)
                    .map(poolService::getWarehouseItem)
                    .forEach(i -> i.setWarehouse(poolService.getWarehouse(transactionAdapter.getDestination())));
        }

        return this;
    }
}