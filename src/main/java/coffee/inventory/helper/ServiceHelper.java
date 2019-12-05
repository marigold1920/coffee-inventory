package coffee.inventory.helper;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.entity.Category;
import coffee.inventory.entity.Unit;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.entity.WarehouseItem;
import coffee.inventory.service.CategoryService;
import coffee.inventory.service.UnitService;
import coffee.inventory.service.WarehouseItemService;
import coffee.inventory.service.WarehouseService;

public final class ServiceHelper {
    private static ServiceHelper helper;
    private Map<Integer, WarehouseItem> warehouseItems;
    private Map<Integer, Warehouse> warehouses;
    private Map<String, Unit> units;
    private Map<String, Category> categories;

    private ServiceHelper() { };

    public static synchronized ServiceHelper getHelper() {
        if (helper == null) helper = new ServiceHelper();

        return helper;
    }

    public void initWarehouses(WarehouseService service, Integer... warehouseIds) {
        warehouses = service.findWarehousesById(warehouseIds)
            .stream()
            .collect(Collectors.toMap(Warehouse::getId, Function.identity()));
    }

    public void initWarehouseItems(WarehouseItemService service,  Integer... warehouseItemIds) {
        warehouseItems = service.findWarehouseItemsById(warehouseItemIds)
            .stream()
            .collect(Collectors.toMap(i -> i.getItem().getId(), Function.identity()));
    }

    public void initUnit(UnitService service) {
        units = service.findAllUnits()
            .stream()
            .collect(Collectors.toMap(Unit::getName, Function.identity()));
    }

    public void initCategory(CategoryService service) {
        categories = service.findAllCategories()
            .stream()
            .collect(Collectors.toMap(Category::getName, Function.identity()));
    }

    public Optional<Warehouse> getWarehouse(Integer warehouseId) {

        return Optional.ofNullable(warehouses.get(warehouseId));
    }

    public Optional<WarehouseItem> getWarehouseItem(ItemAdapter warehouseItem) {
        
        return Optional.ofNullable(warehouseItems.get(warehouseItem.getId()));
    }

    public Optional<Unit> getUnit(String name) {

        return Optional.ofNullable(units.get(name));
    }

    public Optional<Category> getCategory(String name) {

        return Optional.ofNullable(categories.get(name));
    }
}