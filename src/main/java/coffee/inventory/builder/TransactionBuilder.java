package coffee.inventory.builder;

import java.time.LocalDate;

import coffee.inventory.entity.Transaction;
import coffee.inventory.helper.ServiceHelper;

public class TransactionBuilder {
    protected Transaction transaction;
    protected ServiceHelper helper;

    public TransactionBuilder(ServiceHelper service) {
        transaction = Transaction.builder().dateCreate(LocalDate.now()).build();
        this.helper = service;
    }

    public ItemBuilder item() {

        return new ItemBuilder(helper);
    }

    public WarehouseItemBuilder warehouseItem() {

        return new WarehouseItemBuilder(helper);
    }

    public TransactionDetailsBuilder details() {

        return new TransactionDetailsBuilder(helper);
    }

    public Transaction build() {

        return transaction;
    }
}