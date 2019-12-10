package coffee.inventory.builder;

import java.util.Collection;
import java.util.Objects;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.helper.PoolService;

public class ItemBuilder extends TransactionBuilder {

    public ItemBuilder(PoolService poolService) {
        super(poolService);
    }

    public ItemBuilder make(Collection<ItemAdapter> items) {
        items.stream()
            .filter(i -> Objects.isNull(i.getId()))
            .forEach(poolService::getItem);

        return this;
    }
}