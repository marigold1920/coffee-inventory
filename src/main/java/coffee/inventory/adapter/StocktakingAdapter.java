package coffee.inventory.adapter;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import coffee.inventory.builder.StocktakingBuilder;
import coffee.inventory.entity.Stocktaking;
import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.PoolService;

@Getter
public class StocktakingAdapter implements Serializable, Adapter {

    private static final long serialVersionUID = 1L;

    private int warehouseId;
    private ArrayList<StocktakingDetailsAdapter> listStock;
    private TransactionType type;

    @Override
    public Stocktaking build(PoolService poolService) {

        return new StocktakingBuilder(poolService, warehouseId).makeDetails(listStock).build();
    }

    @Override
    public List<Integer> getItemIds() {
        return listStock.stream().map(StocktakingDetailsAdapter::getWarehouseItemId).collect(Collectors.toList());
    }

    @Override
    public Collection<Integer> getWarehouseIds() {
        return Arrays.asList(warehouseId);
    }

}
