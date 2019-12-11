package coffee.inventory.builder;

import java.util.Objects;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.helper.PoolService;

public class ItemBuilder extends TransactionBuilder {

    public ItemBuilder(PoolService poolService) {
        super(poolService);
    }

    public ItemBuilder make(TransactionAdapter transactionAdapter) {
        transactionAdapter.getItems().stream().filter(i -> Objects.isNull(i.getId()))
                .map(i -> i.setSupplier(transactionAdapter.getSource())).forEach(poolService::getItem);

        return this;
    }
}