package coffee.inventory.builder;

import java.util.Collection;
import java.util.stream.Collectors;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.entity.TransactionDetail;

public class TransactionDetailsBuilder extends TransactionBuilder {

    public TransactionDetailsBuilder make(Collection<ItemAdapter> itemAdapters) {
        transaction.setTransactionDetails(itemAdapters.stream()
                .map(i -> TransactionDetail.builder().item(helper.getItem(i)).quantity(i.getQuantity()).build())
                .collect(Collectors.toSet()));

        return this;
    }
}