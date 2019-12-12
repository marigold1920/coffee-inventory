package coffee.inventory.chain;


import coffee.inventory.adapter.Adapter;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.implement.TransactionServiceImpl;

abstract class AbstractInitiator {
    
    protected Level level;
    protected AbstractInitiator nextInitiation;
    protected PoolService poolService;
    protected Adapter data;
    protected TransactionServiceImpl service;

    AbstractInitiator(PoolService poolService, Adapter adapter, TransactionServiceImpl service) {
        this.poolService = poolService;
        this.data = adapter;
        this.service = service;
    }

    abstract void fetchData();

    AbstractInitiator setNextInitiation(AbstractInitiator nextInitiation) {
        this.nextInitiation = nextInitiation;

        return this;
    }
}