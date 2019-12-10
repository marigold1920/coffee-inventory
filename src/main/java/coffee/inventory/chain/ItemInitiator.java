package coffee.inventory.chain;

import coffee.inventory.enumeration.Level;

public class ItemInitiator extends AbstractInitiator {

    ItemInitiator(Level level) {
        this.level = level;
    }

    @Override
    void fetchData() {
        helper.initItems(service.findAllItemsById(transactionAdapter));
    }

}