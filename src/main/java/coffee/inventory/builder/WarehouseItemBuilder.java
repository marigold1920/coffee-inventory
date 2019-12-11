package coffee.inventory.builder;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.helper.PoolService;

public class WarehouseItemBuilder extends TransactionBuilder {

    public WarehouseItemBuilder(PoolService poolService) {
        super(poolService);
    }

    public WarehouseItemBuilder make(TransactionAdapter transactionAdapter) {
        transactionAdapter.getItems().stream().map(i -> poolService.getWarehouseItem(i, transactionAdapter.getType()))
                .forEach(i -> i.setWarehouse(poolService.getWarehouse(transactionAdapter.getDestination())));
                    
        return this;
    }
}