package coffee.inventory.service.implement;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Category;
import coffee.inventory.entity.Item;
import coffee.inventory.entity.Product;
import coffee.inventory.entity.Transaction;
import coffee.inventory.entity.Unit;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.entity.WarehouseItem;
import coffee.inventory.enumeration.TransactionStatus;
import coffee.inventory.helper.PoolService;
import coffee.inventory.repository.CategoryRepository;
import coffee.inventory.repository.ItemRepository;
import coffee.inventory.repository.ProductRepository;
import coffee.inventory.repository.TransactionRepository;
import coffee.inventory.repository.UnitRepository;
import coffee.inventory.repository.WarehouseItemRepository;
import coffee.inventory.repository.WarehouseRepository;
import coffee.inventory.service.TransactionService;
import coffee.inventory.factory.Creator;
import coffee.inventory.factory.TransactionAlgorithmsCreator;

@Service
public class TransactionServiceImpl implements TransactionService {

        private TransactionRepository transactionRepository;
        private UnitRepository unitRepository;
        private CategoryRepository categoryRepository;
        private WarehouseRepository warehouseRepository;
        private ItemRepository itemRepository;
        private WarehouseItemRepository warehouseItemRepository;
        private ProductRepository productRepository;

        @Autowired
        public TransactionServiceImpl(TransactionRepository transactionRepository, UnitRepository unitRepository,
                        CategoryRepository categoryRepository, ItemRepository itemRepository,
                        WarehouseRepository warehouseRepository, WarehouseItemRepository warehouseItemRepository,
                        ProductRepository productRepository) {
                this.transactionRepository = transactionRepository;
                this.unitRepository = unitRepository;
                this.categoryRepository = categoryRepository;
                this.warehouseRepository = warehouseRepository;
                this.itemRepository = itemRepository;
                this.warehouseItemRepository = warehouseItemRepository;
                this.productRepository = productRepository;
        }

        public ResponseModel saveTransaction(Adapter adapter) {
                PoolService poolService = new PoolService();
                PipeLineManager initiator = new PipeLineManager(adapter, poolService, this);
                Creator creator = new TransactionAlgorithmsCreator();
                creator.initData(adapter.getType(), initiator);
                ResponseModel response = new ResponseModel();
                transactionRepository.saveAndFlush((Transaction) adapter.build(poolService));

                return response.finishRequest();
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

        public Collection<WarehouseItem> findAllWarehouseItemsById(Integer warehouseId, List<Integer> itemIds) {
                if (itemIds.isEmpty())
                        return Collections.emptyList();
                // Init all warehouse items to ServiceHelper
                return warehouseItemRepository.findAllByItemId(warehouseId, itemIds);
        }

        public Collection<Warehouse> findAllWarehouseById(Collection<Integer> warehouseIds) {
                // Init all warehouse items to ServiceHelper
                return warehouseRepository.findAllById(warehouseIds);
        }

        @Override
        public ResponseModel finishTransaction(Integer transactionId, TransactionStatus status) {
                ResponseModel response = new ResponseModel();
                transactionRepository.finishTransaction(transactionId, status);

                return response.finishRequest();
        }
}