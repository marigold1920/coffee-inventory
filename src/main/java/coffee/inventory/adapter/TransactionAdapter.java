package coffee.inventory.adapter;

import java.util.Collection;

import coffee.inventory.builder.TransactionBuilder;
import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionType;
import lombok.Getter;

@Getter
public class TransactionAdapter {
    private int source;
    private int destination;
    private Collection<ItemAdapter> items;
    private TransactionType type;

    public static Transaction build() {

        return new TransactionBuilder().build();
    }
}