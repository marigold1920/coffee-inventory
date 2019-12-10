package coffee.inventory.chain;

import coffee.inventory.enumeration.Level;

public class WarehouseItemInitiator extends AbstractInitiator {

    WarehouseItemInitiator(Level level) {
        this.level = level;
    }

    @Override
    void fetchData() {
        helper.initWarehouseItems(service.findAllWarehouseItemsById(transactionAdapter));
    }

}