package coffee.inventory.chain;

import coffee.inventory.enumeration.Level;

public class CategoryInitiator extends AbstractInitiator {

    CategoryInitiator(Level level) {
        this.level = level;
    }
 
    @Override
    void fetchData() {
        helper.initCategories(service.findAllCategoriesByName(transactionAdapter));
    }

}