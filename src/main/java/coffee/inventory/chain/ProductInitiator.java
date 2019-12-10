package coffee.inventory.chain;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.implement.TransactionServiceImpl;

public class ProductInitiator extends AbstractInitiator {

    ProductInitiator(Level level, PoolService poolService, TransactionAdapter transactionAdapter,
            TransactionServiceImpl service) {
        super(poolService, transactionAdapter, service);
        this.level = level;
    }

    @Override
    void fetchData() {
        poolService.initProducts(service.findAllProductsById(transactionAdapter));
    }

}