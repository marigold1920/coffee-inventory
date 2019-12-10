package coffee.inventory.chain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.chain.AbstractInitiator;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.implement.TransactionServiceImpl;

public class PipeLineManager {
    private Collection<AbstractInitiator> initiators = new LinkedList<>();

    public PipeLineManager(TransactionAdapter transactionAdapter, PoolService poolService, TransactionServiceImpl service) {
        initiators.addAll(Stream.of(
                    new CategoryInitiator(Level.CATEGORY, poolService, transactionAdapter, service),
                    new UnitInitiator(Level.UNIT, poolService, transactionAdapter, service),
                    new ProductInitiator(Level.PRODUCT, poolService, transactionAdapter, service),
                    new ItemInitiator(Level.ITEM, poolService, transactionAdapter, service),
                    new WarehouseItemInitiator(Level.WAREHOUSEITEM, poolService, transactionAdapter, service),
                    new WarehouseInitiator(Level.WAREHOUSE, poolService, transactionAdapter, service)
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