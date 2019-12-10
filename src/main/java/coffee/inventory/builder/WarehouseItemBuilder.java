package coffee.inventory.builder;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.ServiceHelper;

public class WarehouseItemBuilder extends TransactionBuilder {

    public WarehouseItemBuilder(ServiceHelper service) {
        super(service);
    }

    public WarehouseItemBuilder make(TransactionAdapter transactionAdapter) {
        if (transactionAdapter.getType() == TransactionType.RECEIPT) {
            transactionAdapter.getItems().stream().map(helper::getWarehouseItem)
                    .forEach(i -> i.setWarehouse(helper.getWarehouse(transactionAdapter.getDestination())));
        } else {
            transactionAdapter.getItems().stream()
                    .map(ItemAdapter::downQuantity)
                    .map(helper::getWarehouseItem)
                    .forEach(i -> i.setWarehouse(helper.getWarehouse(transactionAdapter.getDestination())));
        }

        return this;
    }
}