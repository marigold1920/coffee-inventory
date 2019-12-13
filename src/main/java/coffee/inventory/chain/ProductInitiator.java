package coffee.inventory.chain;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.ServiceHelper;

public class ProductInitiator extends AbstractInitiator {

    ProductInitiator(Level level, PoolService poolService, Adapter adapter, ServiceHelper service) {
        super(poolService, adapter, service);
        this.level = level;
    }

    @Override
    void fetchData() {
        poolService.initProducts(service.findAllProductsById(data.getProductCodes()));
    }

}