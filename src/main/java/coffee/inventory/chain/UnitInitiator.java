package coffee.inventory.chain;

import coffee.inventory.enumeration.Level;

public class UnitInitiator extends AbstractInitiator {

    UnitInitiator(Level level) {
        this.level = level;
    }

    @Override
    void fetchData() {
        helper.initUnits(service.findAllUnitsByName(transactionAdapter));
    }

}