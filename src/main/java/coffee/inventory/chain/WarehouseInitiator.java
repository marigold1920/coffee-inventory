package coffee.inventory.chain;

import coffee.inventory.enumeration.Level;

public class WarehouseInitiator extends AbstractInitiator {

    WarehouseInitiator(Level level) {
        this.level = level;
    }

    @Override
    void fetchData() {
        helper.initWarehouses(service.findAllWarehouseById(transactionAdapter));
    }

}