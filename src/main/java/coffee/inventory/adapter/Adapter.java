package coffee.inventory.adapter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import coffee.inventory.enumeration.TransactionType;
import coffee.inventory.helper.PoolService;

public interface Adapter {

    TransactionType getType();

    public default Collection<Integer> getWarehouseIds() {

        return Collections.emptySet();
    }

    public default Object build(PoolService poolService) {

        return null;
    }

    public default Integer getWarehouse() {

        return null;
    }

    public default Collection<String> getProductCodes() {

        return Collections.emptySet();
    }

    public default Collection<String> getCategories() {

        return Collections.emptySet();
    }

    public default Collection<String> getUnits() {

        return Collections.emptySet();
    }

    public default List<Integer> getItemIds() {

        return Collections.emptyList();
    }
}