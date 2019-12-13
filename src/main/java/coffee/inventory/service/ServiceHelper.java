package coffee.inventory.service;

import java.util.Collection;

import coffee.inventory.entity.Category;
import coffee.inventory.entity.Item;
import coffee.inventory.entity.Product;
import coffee.inventory.entity.Unit;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.entity.WarehouseItem;

public interface ServiceHelper {

    Collection<Category> findAllCategoriesByName(Collection<String> categories);

    Collection<Unit> findAllUnitsByName(Collection<String> units);

    Collection<Product> findAllProductsById(Collection<String> productCodes);

    Collection<Item> findAllItemsById(Collection<Integer> itemIds);

    Collection<WarehouseItem> findAllWarehouseItemsByItemId(Integer warehouseId, Collection<Integer> itemIds);

    Collection<WarehouseItem> findAllWarehouseItemsById(Collection<Integer> itemIds);

    Collection<Warehouse> findAllWarehouseById(Collection<Integer> warehouseIds);
}