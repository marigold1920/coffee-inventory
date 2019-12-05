package coffee.inventory.service.implement;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;

import coffee.inventory.entity.WarehouseItem;
import coffee.inventory.repository.WarehouseItemRepository;
import coffee.inventory.service.WarehouseItemService;

@Service
public class WarehouseItemServiceImpl implements WarehouseItemService {

    private WarehouseItemRepository warehouseItemRepository;

    public WarehouseItemServiceImpl(WarehouseItemRepository warehouseItemRepository) {
        this.warehouseItemRepository = warehouseItemRepository;
    }

    public Collection<WarehouseItem> findWarehouseItemsById(Integer... warehouseItemIds) {

        return warehouseItemRepository.findAllById(Arrays.asList(warehouseItemIds));
    }

}