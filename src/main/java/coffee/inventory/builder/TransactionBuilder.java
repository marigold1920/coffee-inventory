package coffee.inventory.builder;

import java.time.LocalDate;

import coffee.inventory.entity.Transaction;
import coffee.inventory.helper.PoolService;

public class TransactionBuilder {
    protected Transaction transaction;
    protected PoolService poolService;

    public TransactionBuilder(PoolService service) {
        transaction = Transaction.builder().dateCreate(LocalDate.now()).build();
        this.poolService = service;
    }

    public ItemBuilder item() {

        return new ItemBuilder(poolService);
    }

    public WarehouseItemBuilder warehouseItem() {

        return new WarehouseItemBuilder(poolService);
    }

    public TransactionDetailsBuilder details() {

        return new TransactionDetailsBuilder(poolService);
    }

    public Transaction build() {

        return transaction;
    }
}