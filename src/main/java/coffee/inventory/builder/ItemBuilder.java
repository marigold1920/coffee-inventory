package coffee.inventory.builder;

import java.util.Collection;
import java.util.Objects;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.helper.ServiceHelper;

public class ItemBuilder extends TransactionBuilder {

    public ItemBuilder(ServiceHelper service) {
        super(service);
    }

    public ItemBuilder make(Collection<ItemAdapter> items) {
        items.stream()
            .filter(i -> Objects.isNull(i.getId()))
            .forEach(helper::getItem);

        return this;
    }
}