package coffee.inventory.chain;

import coffee.inventory.enumeration.Level;

public class ProductInitiator extends AbstractInitiator {

    ProductInitiator(Level level) {
        this.level = level;
    }

    @Override
    void fetchData() {
        helper.initProducts(service.findAllProductsById(transactionAdapter));
    }

}