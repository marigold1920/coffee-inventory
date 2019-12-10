package coffee.inventory.adapter;

import java.util.Collection;

import coffee.inventory.builder.TransactionBuilder;
import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.ServiceHelper;
import lombok.Getter;

@Getter
public class TransactionAdapter {

    private int source;
    private int destination;
    private Collection<ItemAdapter> items;
    private TransactionType type;

    public Transaction build(ServiceHelper service) {

        return new TransactionBuilder(service).item().make(items).warehouseItem().make(this).details().make(this).build();
    }
}