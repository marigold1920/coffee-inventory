package coffee.inventory.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.entity.Category;
import coffee.inventory.entity.Item;
import coffee.inventory.entity.Product;
import coffee.inventory.entity.Unit;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.entity.WarehouseItem;
import coffee.inventory.enumeration.TransactionType;

public final class PoolService {
    private Map<String, WarehouseItem> warehouseItems;
    private Map<String, Product> products;
    private Map<String, Item> items;
    private Map<Integer, Warehouse> warehouses;
    private Map<String, Unit> units;
    private Map<String, Category> categories;

    public PoolService initWarehouses(Collection<Warehouse> initiation) {
        warehouses = initiation.stream().collect(Collectors.toMap(Warehouse::getId, Function.identity()));

        return this;
    }

    public PoolService initProducts(Collection<Product> initiation) {
        products = initiation.stream().collect(Collectors.toMap(Product::getProductCode, Function.identity()));

        return this;
    }

    public PoolService initItems(Collection<Item> initiation) {
        items = initiation.stream()
                .collect(Collectors.toMap(i -> i.getProduct().getProductCode(), Function.identity()));
        return this;
    }

    public PoolService initWarehouseItems(Collection<WarehouseItem> initiation) {
        warehouseItems = initiation.stream()
                .collect(Collectors.toMap(i -> i.getItem().getProduct().getProductCode(), Function.identity()));

        return this;
    }

    public PoolService initUnits(Collection<Unit> initiation) {
        units = initiation.stream().collect(Collectors.toMap(Unit::getName, Function.identity()));

        return this;
    }

    public PoolService initCategories(Collection<Category> initiation) {
        categories = initiation.stream().collect(Collectors.toMap(Category::getName, Function.identity()));

        return this;
    }

    public Warehouse getWarehouse(Integer warehouseId) {

        return warehouses.get(warehouseId);
    }

    public WarehouseItem getWarehouseItem(ItemAdapter itemAdapter, TransactionType type) {
        String key = itemAdapter.getProductCode();
        int quantity = type == TransactionType.DELIVERY ? 0 : itemAdapter.getQuantity();
        
        if (!warehouseItems.containsKey(key)) {
            Item item = getItem(itemAdapter);
            //Create new  WarehouseItem
            WarehouseItem warehouseItem = WarehouseItem.builder().item(item).quantity(quantity).build();
            item.addWarehouseItem(warehouseItem);
            //Init WarehouseItem to ServiceHelper
            warehouseItems.put(item.getProduct().getProductCode(), warehouseItem);
        } else {
            //Update quantity 
            WarehouseItem warehouseItem = warehouseItems.get(key);
            warehouseItem.setQuantity(warehouseItem.getQuantity() + quantity);
        }

        return warehouseItems.get(key);
    }

    public Item getItem(ItemAdapter itemAdapter) {
        String key = itemAdapter.getProductCode();

        if (!items.containsKey(key)) {
            // Create new item
            Item item = Item.builder().product(getProduct(itemAdapter)).supplier(getWarehouse(itemAdapter.getSupplier()))
                    .warehouseItems(new HashSet<>()).build();
            // Init item to ServiceHelper
            items.put(item.getProduct().getProductCode(), item);
        }

        return items.get(key);
    }

    public Product getProduct(ItemAdapter itemAdapter) {
        String key = itemAdapter.getProductCode();

        if (!products.containsKey(key)) {
            //Create new product
            Product product = Product.builder().name(itemAdapter.getName()).productCode(key)
                    .price(itemAdapter.getPrice()).unit(getUnit(itemAdapter.getUnit()))
                    .category(getCategory(itemAdapter.getCategory())).build();
            //Init product to ServiceHelper
            products.put(key, product);
        }

        return products.get(key);
    }

    public Unit getUnit(String name) {

        return units.get(name);
    }

    public Category getCategory(String name) {

        return categories.get(name);
    }


}