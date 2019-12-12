package coffee.inventory.adapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import coffee.inventory.builder.TransactionBuilder;
import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.PoolService;
import lombok.Getter;

@Getter
public class TransactionAdapter implements Adapter {

    private int source;
    private int destination;
    private Collection<ItemAdapter> items;
    private TransactionType type;

    @Override
    public Transaction build(PoolService service) {
        return new TransactionBuilder(service).item().make(this).warehouseItem().make(this).details().make(this).build();
    }

    @Override
    public Integer getWarehouseId() {
        return destination;
    }

    @Override
    public List<Integer> getItemIds() {
        return items.stream().filter(i -> Objects.nonNull(i.getId())).map(ItemAdapter::getId).distinct().collect(Collectors.toList());
    }
    
    @Override
    public Collection<Integer> getWarehouseIds() {
        return Arrays.asList(source, destination);
    }

    @Override
    public Collection<String> getUnits() {
        return items.stream().filter(i -> !Objects.nonNull(i.getId())).map(ItemAdapter::getUnit).distinct().collect(Collectors.toList());
    }
    
    @Override
    public Collection<String> getCategories() {
        return items.stream().filter(i -> !Objects.nonNull(i.getId())).map(ItemAdapter::getCategory).distinct().collect(Collectors.toList());
    }

    @Override
    public Collection<String> getProductCodes() {
        return items.stream().filter(i -> Objects.nonNull(i.getId())).map(ItemAdapter::getProductCode).distinct().collect(Collectors.toList());
    }
}