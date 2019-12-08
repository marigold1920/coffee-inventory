package coffee.inventory.builder;

import java.time.LocalDate;

import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.ServiceHelper;

public class TransactionBuilder {
    protected Transaction transaction;
    protected ServiceHelper helper = ServiceHelper.getService();

    public TransactionBuilder() {
        transaction = Transaction.builder().dateCreate(LocalDate.now()).status("RECEIPTED").type(TransactionType.RECEIPT).build();
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