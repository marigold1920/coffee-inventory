package coffee.inventory.chain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.chain.AbstractInitiator;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.ServiceHelper;
import coffee.inventory.service.implement.TransactionServiceImpl;

public class PipeLineManager {
    private Collection<AbstractInitiator> initiators = new LinkedList<>();

    public PipeLineManager(TransactionAdapter transactionAdapter, ServiceHelper serviceHelper, TransactionServiceImpl service) {
        initiators.addAll(Stream.of(
                    new CategoryInitiator(Level.CATEGORY).setTransactionAdapter(transactionAdapter).setServiceHelper(serviceHelper).setService(service),
                    new UnitInitiator(Level.UNIT).setTransactionAdapter(transactionAdapter).setServiceHelper(serviceHelper).setService(service),
                    new ProductInitiator(Level.PRODUCT).setTransactionAdapter(transactionAdapter).setServiceHelper(serviceHelper).setService(service),
                    new ItemInitiator(Level.ITEM).setTransactionAdapter(transactionAdapter).setServiceHelper(serviceHelper).setService(service),
                    new WarehouseItemInitiator(Level.WAREHOUSEITEM).setTransactionAdapter(transactionAdapter).setServiceHelper(serviceHelper).setService(service),
                    new WarehouseInitiator(Level.WAREHOUSE).setTransactionAdapter(transactionAdapter).setServiceHelper(serviceHelper).setService(service)
                )
                .sorted((h1, h2) -> Integer.compare(h1.level.getValue(), h1.level.getValue()))
                .collect(Collectors.toList()));
    }

    public void doPipeLine(Level level) {
        initiators.stream()
                .filter(i -> i.level.getValue() <= level.getValue())
                .forEach(AbstractInitiator::fetchData);
    }
}