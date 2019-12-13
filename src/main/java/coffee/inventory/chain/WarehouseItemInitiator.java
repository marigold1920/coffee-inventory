package coffee.inventory.chain;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.enumeration.Level;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.ServiceHelper;

public class WarehouseItemInitiator extends AbstractInitiator {

    WarehouseItemInitiator(Level level, PoolService poolService, Adapter adapter, ServiceHelper service) {
        super(poolService, adapter, service);
        this.level = level;
    }

    @Override
    void fetchData() {
        if (data.getType() == TransactionType.STOCKTAKING) {
            poolService.initWarehouseItems(service.findAllWarehouseItemsById(data.getItemIds()), data.getType());
        } else {
            poolService.initWarehouseItems(
                    service.findAllWarehouseItemsByItemId(data.getWarehouse(), data.getItemIds()), data.getType());
        }
    }

}