package coffee.inventory.builder;

import coffee.inventory.adapter.TransactionAdapter;

public class WarehouseItemBuilder extends TransactionBuilder {

    public WarehouseItemBuilder make(TransactionAdapter transactionAdapter) {
        transactionAdapter.getItems().stream().map(helper::getWarehouseItem)
                .forEach(i -> i.setWarehouse(helper.getWarehouse(transactionAdapter.getDestination())));

        return this;
    }
}