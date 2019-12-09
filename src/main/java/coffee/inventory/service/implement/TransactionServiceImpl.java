package coffee.inventory.service.implement;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.adapter.ItemAdapter;
import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.enumeration.ResponseStatus;
import coffee.inventory.helper.ServiceHelper;
import coffee.inventory.repository.CategoryRepository;
import coffee.inventory.repository.ItemRepository;
import coffee.inventory.repository.ProductRepository;
import coffee.inventory.repository.TransactionRepository;
import coffee.inventory.repository.UnitRepository;
import coffee.inventory.repository.WarehouseItemRepository;
import coffee.inventory.repository.WarehouseRepository;
import coffee.inventory.service.TransactionService;

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
                ServiceHelper serviceHelper = ServiceHelper.getService();
                init(transactionAdapter, serviceHelper);
                ResponseModel response = new ResponseModel();
                response.addStatus(ResponseStatus.SUCCESS);
                transactionRepository.saveAndFlush(transactionAdapter.build());

                return response.finishRequest();
        }

        /**
         * 
         * @param transactionAdapter
         * @param serviceHelper
         */
        private void init(TransactionAdapter transactionAdapter, ServiceHelper serviceHelper) {
                Collection<ItemAdapter> items = transactionAdapter.getItems();
                Supplier<Stream<ItemAdapter>> inititation = () -> items.parallelStream().filter(i -> Objects.isNull(i.getId()));
                Set<Integer> warehouseIds = inititation.get().map(ItemAdapter::getSupplier).distinct().collect(Collectors.toSet());
                warehouseIds.addAll(Arrays.asList(transactionAdapter.getDestination(), transactionAdapter.getSource()));

                serviceHelper.initUnits(unitRepository.findAllByName(
                                inititation.get().map(ItemAdapter::getUnit).collect(Collectors.toList())))
                                .initCategories(categoryRepository.findAllByName(inititation.get()
                                                .map(ItemAdapter::getCategory).distinct().collect(Collectors.toList())))
                                .initProducts(productRepository.findAllById(inititation.get()
                                                .map(ItemAdapter::getProductCode).distinct().collect(Collectors.toList())))
                                .initItems(itemRepository.findAllById(
                                                items.stream().map(ItemAdapter::getId).collect(Collectors.toList())))
                                .initWarehouseItems(warehouseItemRepository.findAllByItemId(
                                                transactionAdapter.getDestination(),
                                                items.stream().map(ItemAdapter::getId).collect(Collectors.toList())))
                                .initWarehouses(warehouseRepository.findAllById(warehouseIds));
        }
}