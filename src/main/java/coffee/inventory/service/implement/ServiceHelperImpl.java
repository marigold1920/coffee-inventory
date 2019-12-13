package coffee.inventory.service.implement;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.entity.Category;
import coffee.inventory.entity.Item;
import coffee.inventory.entity.Product;
import coffee.inventory.entity.Unit;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.entity.WarehouseItem;
import coffee.inventory.repository.CategoryRepository;
import coffee.inventory.repository.ItemRepository;
import coffee.inventory.repository.ProductRepository;
import coffee.inventory.repository.UnitRepository;
import coffee.inventory.repository.WarehouseItemRepository;
import coffee.inventory.repository.WarehouseRepository;
import coffee.inventory.service.ServiceHelper;

@Service
public class ServiceHelperImpl implements ServiceHelper {

    private UnitRepository unitRepository;
    private CategoryRepository categoryRepository;
    private WarehouseRepository warehouseRepository;
    private ItemRepository itemRepository;
    private WarehouseItemRepository warehouseItemRepository;
    private ProductRepository productRepository;

    @Autowired
    public ServiceHelperImpl(UnitRepository unitRepository, CategoryRepository categoryRepository, ItemRepository itemRepository,
                    WarehouseRepository warehouseRepository, WarehouseItemRepository warehouseItemRepository,
                    ProductRepository productRepository) {
            this.unitRepository = unitRepository;
            this.categoryRepository = categoryRepository;
            this.warehouseRepository = warehouseRepository;
            this.itemRepository = itemRepository;
            this.warehouseItemRepository = warehouseItemRepository;
            this.productRepository = productRepository;
    }

    public Collection<Category> findAllCategoriesByName(Collection<String> categories) {
                // Init all categories to ServiceHelper
                return categoryRepository.findAllByName(categories);
        }

        public Collection<Unit> findAllUnitsByName(Collection<String> units) {
                // Init all units to ServiceHelper
                return unitRepository.findAllByName(units);
        }

        public Collection<Product> findAllProductsById(Collection<String> productCodes) {
                // Init all products to ServiceHelper
                return productRepository.findAllById(productCodes);
        }

        public Collection<Item> findAllItemsById(Collection<Integer> itemIds) {
                // Init all items to ServiceHelper
                return itemRepository.findAllById(itemIds);
        }

        public Collection<WarehouseItem> findAllWarehouseItemsByItemId(Integer warehouseId,
                        Collection<Integer> itemIds) {
                if (itemIds.isEmpty())
                        return Collections.emptyList();
                // Init all warehouse items to ServiceHelper
                return warehouseItemRepository.findAllByItemId(warehouseId, itemIds);
        }

        public Collection<WarehouseItem> findAllWarehouseItemsById(Collection<Integer> warehouseItemIds) {
        
                return warehouseItemRepository.findAllById(warehouseItemIds);
        }

public Collection<Warehouse> findAllWarehouseById(Collection<Integer> warehouseIds) {
        // Init all warehouse items to ServiceHelper
        return warehouseRepository.findAllById(warehouseIds);
}
}