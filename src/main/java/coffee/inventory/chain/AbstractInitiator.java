package coffee.inventory.chain;


import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.ServiceHelper;
import coffee.inventory.service.implement.TransactionServiceImpl;

public abstract class AbstractInitiator {
    
    protected Level level;
    protected AbstractInitiator nextInitiation;
    protected ServiceHelper helper;
    protected TransactionAdapter transactionAdapter;
    protected TransactionServiceImpl service;

    abstract void fetchData();

    AbstractInitiator setNextInitiation(AbstractInitiator nextInitiation) {
        this.nextInitiation = nextInitiation;

        return this;
    }

    void init(Level level) {
        if (this.level.getValue() <= level.getValue()) {
            fetchData();
        } else {
            nextInitiation.init(level);
        }
    }

    AbstractInitiator setTransactionAdapter(TransactionAdapter transactionAdapter) {
        this.transactionAdapter = transactionAdapter;

        return this;
    } 

    AbstractInitiator setServiceHelper(ServiceHelper serviceHelper) {
        this.helper = serviceHelper;

        return this;
    }
    
    AbstractInitiator setService(TransactionServiceImpl service) {
        this.service = service;

        return this;
    } 
}