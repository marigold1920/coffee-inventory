package coffee.inventory.chain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.chain.AbstractInitiator;
import coffee.inventory.enumeration.Level;
import coffee.inventory.helper.PoolService;
import coffee.inventory.service.implement.TransactionServiceImpl;

public class PipeLineManager {
    private Collection<AbstractInitiator> initiators = new LinkedList<>();

    public PipeLineManager(Adapter adapter, PoolService poolService, TransactionServiceImpl service) {
        initiators.addAll(Stream.of(
                    new CategoryInitiator(Level.CATEGORY, poolService, adapter, service),
                    new UnitInitiator(Level.UNIT, poolService, adapter, service),
                    new ProductInitiator(Level.PRODUCT, poolService, adapter, service),
                    new ItemInitiator(Level.ITEM, poolService, adapter, service),
                    new WarehouseItemInitiator(Level.WAREHOUSEITEM, poolService, adapter, service),
                    new WarehouseInitiator(Level.WAREHOUSE, poolService, adapter, service)
                )
                .sorted((h1, h2) -> Integer.compare(h1.level.getValue(), h2.level.getValue()))
                .collect(Collectors.toList()));
    }

    public void doPipeLine(Level level) {
        initiators.stream()
                .filter(i -> i.level.getValue() <= level.getValue())
                .forEach(AbstractInitiator::fetchData);
    }
}