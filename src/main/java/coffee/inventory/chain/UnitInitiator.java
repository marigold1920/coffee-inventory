package coffee.inventory.chain;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.implement.TransactionServiceImpl;

public class UnitInitiator extends AbstractInitiator {

    UnitInitiator(Level level, PoolService poolService, Adapter adapter, TransactionServiceImpl service) {
        super(poolService, adapter, service);
        this.level = level;
    }

    @Override
    void fetchData() {
        poolService.initUnits(service.findAllUnitsByName(data.getUnits()));
    }

}