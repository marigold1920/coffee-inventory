package coffee.inventory.helper;

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
import coffee.inventory.service.CategoryService;
import coffee.inventory.service.ItemService;
import coffee.inventory.service.UnitService;
import coffee.inventory.service.WarehouseItemService;
import coffee.inventory.service.WarehouseService;

public final class ServiceHelper {
    private static ServiceHelper helper;
    private Map<String, WarehouseItem> warehouseItems;
    private Map<String, Item> items;
    private Map<Integer, Warehouse> warehouses;
    private Map<String, Unit> units;
    private Map<String, Category> categories;

    private ServiceHelper() {
    };

    public static synchronized ServiceHelper getHelper() {
        if (helper == null)
            helper = new ServiceHelper();

        return helper;
    }

    /**
     * 
     * @param service
     * @param warehouseIds
     */
    public void initWarehouses(WarehouseService service, Integer... warehouseIds) {
        warehouses = service.findWarehousesById(warehouseIds).stream()
                .collect(Collectors.toMap(Warehouse::getId, Function.identity()));
    }

    /**
     * 
     * @param service
     * @param itemIds
     */
    public void initItem(ItemService service, Integer... itemIds) {
        items = service.findAllItemsById(itemIds).stream()
                .collect(Collectors.toMap(i -> i.getProduct().getProductCode(), Function.identity()));
    }

    /**
     * 
     * @param service
     * @param warehouseItemIds
     */
    public void initWarehouseItems(WarehouseItemService service, int warehouseId, Integer... warehouseItemIds) {
        warehouseItems = service.findWarehouseItemsById(warehouseId, warehouseItemIds).stream()
                .collect(Collectors.toMap(i -> i.getItem().getProduct().getProductCode(), Function.identity()));
    }

    /**
     * 
     * @param service
     */
    public void initUnit(UnitService service) {
        units = service.findAllUnits().stream().collect(Collectors.toMap(Unit::getName, Function.identity()));
    }

    /**
     * 
     * @param service
     */
    public void initCategory(CategoryService service) {
        categories = service.findAllCategories().stream()
                .collect(Collectors.toMap(Category::getName, Function.identity()));
    }

    /**
     * 
     * @param warehouseId
     * @return
     */
    public Warehouse getWarehouse(Integer warehouseId) {

        return warehouses.get(warehouseId);
    }

    /**
     * 
     * @param warehouseItem
     * @return
     */
    public WarehouseItem getWarehouseItem(ItemAdapter itemAdapter) {
        var key = itemAdapter.getProductCode();
        var item = getItem(itemAdapter);
        if (!warehouseItems.containsKey(key)) {
            var warehouseItem = WarehouseItem.builder().item(item).quantity(itemAdapter.getQuantity()).build();
            // item.addWarehouseItem(warehouseItem);
            warehouseItems.put(item.getProduct().getProductCode(), warehouseItem);
        } else {
            var warehouseItem = warehouseItems.get(key);
            warehouseItem.setQuantity(warehouseItem.getQuantity() + itemAdapter.getQuantity());
        }

        return warehouseItems.get(key);
    }

    /**
     * 
     * @param itemAdapter
     * @return
     */
    public Item getItem(ItemAdapter itemAdapter) {
        var key = itemAdapter.getProductCode();
        if (!items.containsKey(key)) {
            var product = Product.builder().productCode(itemAdapter.getProductCode()).price(itemAdapter.getPrice())
                    .unit(getUnit(itemAdapter.getUnit())).category(getCategory(itemAdapter.getCategory())).build();
            var item = Item.builder().product(product).supplier(getWarehouse(itemAdapter.getSupplier())).build();
            items.put(item.getProduct().getProductCode(), item);
        }

        return items.get(key);
    }

    /**
     * 
     * @param name
     * @return
     */
    public Unit getUnit(String name) {

        return units.get(name);
    }

    /**
     * 
     * @param name
     * @return
     */
    public Category getCategory(String name) {

        return categories.get(name);
    }
}