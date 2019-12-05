package coffee.inventory.builder;

import java.util.Collection;

import coffee.inventory.adapter.ItemAdapter;

public class WarehouseItemBuilder extends TransactionBuilder {

    WarehouseItemBuilder make(Collection<ItemAdapter> itemAdapters) {
       itemAdapters.stream()
        .map(helper::getWarehouseItem)
        .filter(item -> !item.isPresent())
        .forEach(System.out::println);

        return this;
    }
}