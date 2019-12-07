package coffee.inventory.builder;

import java.util.Collection;
import java.util.Objects;

import coffee.inventory.adapter.ItemAdapter;

public class ItemBuilder extends TransactionBuilder {

    /**
     * 
     * @param items
     * @return
     */
    public ItemBuilder make(Collection<ItemAdapter> items) {
        items.stream()
            .filter(i -> Objects.isNull(i.getId()))
            .forEach(helper::getItem);

        return this;
    }
}