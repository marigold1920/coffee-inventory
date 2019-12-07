package coffee.inventory.builder;

import java.util.Collection;

import coffee.inventory.adapter.ItemAdapter;

public class WarehouseItemBuilder extends TransactionBuilder {

    public WarehouseItemBuilder make(Collection<ItemAdapter> itemAdapters) {
        itemAdapters.forEach(helper::getWarehouseItem);

        return this;
    }
}