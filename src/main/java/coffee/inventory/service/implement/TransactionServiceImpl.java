package coffee.inventory.service.implement;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Category;
import coffee.inventory.entity.Item;
import coffee.inventory.entity.Product;
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
import coffee.inventory.strategy.Creator;
import coffee.inventory.strategy.TransactionAlgorithmsCreator;

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

        public ResponseModel saveTransaction(TransactionAdapter transactionAdapter) {
                PoolService poolService = new PoolService();
                PipeLineManager initiator = new PipeLineManager(transactionAdapter, poolService, this);
                Creator creator = new TransactionAlgorithmsCreator();
                creator.initData(transactionAdapter.getType(), initiator);
                ResponseModel response = new ResponseModel();
                transactionRepository.saveAndFlush(transactionAdapter.build(poolService));

                return response.finishRequest();
        }

        public Collection<Category> findAllCategoriesByName(TransactionAdapter transactionAdapter) {

                // Init all categories to ServiceHelper
                return categoryRepository.findAllByName(
                                transactionAdapter.getItems().stream().filter(i -> Objects.isNull(i.getId()))
                                                .map(ItemAdapter::getCategory).distinct().collect(Collectors.toList()));
        }

        public Collection<Unit> findAllUnitsByName(TransactionAdapter transactionAdapter) {

                // Init all units to ServiceHelper
                return unitRepository.findAllByName(
                                transactionAdapter.getItems().stream().filter(i -> Objects.isNull(i.getId()))
                                                .map(ItemAdapter::getUnit).distinct().collect(Collectors.toList()));
        }

        public Collection<Product> findAllProductsById(TransactionAdapter transactionAdapter) {

                // Init all products to ServiceHelper
                return productRepository.findAllById(transactionAdapter.getItems().stream()
                                .map(ItemAdapter::getProductCode).distinct().collect(Collectors.toList()));
        }

        public Collection<Item> findAllItemsById(TransactionAdapter transactionAdapter) {

                // Init all items to ServiceHelper
                return itemRepository.findAllById(transactionAdapter.getItems().stream().map(ItemAdapter::getId)
                                .distinct().collect(Collectors.toList()));
        }

        public Collection<WarehouseItem> findAllWarehouseItemsById(TransactionAdapter transactionAdapter) {

                // Init all warehouse items to ServiceHelper
                return warehouseItemRepository.findAllByItemId(transactionAdapter.getDestination(), transactionAdapter
                                .getItems().stream().map(ItemAdapter::getId).distinct().collect(Collectors.toList()));
        }

        public Collection<Warehouse> findAllWarehouseById(TransactionAdapter transactionAdapter) {

                // Load suppliers for RECEIPT transaction to ServiceHelper
                Set<Integer> warehouseIds = transactionAdapter.getItems().stream()
                                .filter(i -> Objects.isNull(i.getId())).map(ItemAdapter::getSupplier).distinct()
                                .collect(Collectors.toSet());
                // Load warehouses to ServiceHelper
                warehouseIds.addAll(Arrays.asList(transactionAdapter.getDestination(), transactionAdapter.getSource()));

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