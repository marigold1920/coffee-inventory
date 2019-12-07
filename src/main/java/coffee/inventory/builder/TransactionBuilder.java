package coffee.inventory.builder;

import coffee.inventory.entity.Transaction;
import coffee.inventory.helper.ServiceHelper;

public class TransactionBuilder {
    protected Transaction transaction;
    protected ServiceHelper helper = ServiceHelper.getHelper();

    public TransactionBuilder() {
        transaction = Transaction.builder().build();
    }

    /**
     * 
     * @return
     */
    public ItemBuilder item() {

        return new ItemBuilder();
    }

    /**
     * 
     * @return
     */
    public WarehouseItemBuilder warehouseItem() {

        return new WarehouseItemBuilder();
    }

    /**
     * 
     * @param itemAdapters
     * @return
     */
    public TransactionDetailsBuilder details() {

        return new TransactionDetailsBuilder();
    }

    public Transaction build() {

        return transaction;
    }
}