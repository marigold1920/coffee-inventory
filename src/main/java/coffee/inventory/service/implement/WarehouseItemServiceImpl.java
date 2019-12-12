package coffee.inventory.service.implement;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.WarehouseItem;
import coffee.inventory.repository.WarehouseItemRepository;
import coffee.inventory.service.WarehouseItemService;

@Service
public class WarehouseItemServiceImpl implements WarehouseItemService {

    private WarehouseItemRepository warehouseItemRepository;

    public WarehouseItemServiceImpl(WarehouseItemRepository warehouseItemRepository) {
        this.warehouseItemRepository = warehouseItemRepository;
    }

    public Collection<WarehouseItem> findWarehouseItemsById(int warehouseId, Integer... warehouseItemIds) {

        return warehouseItemRepository.findAllByItemId(warehouseId, Arrays.asList(warehouseItemIds));
    }

    public ResponseModel getWarehouseItems(int pageIndex, int warehouseId) {
        ResponseModel respones = new ResponseModel();
        PageRequest page = PageRequest.of(pageIndex, 1000);
        respones.addData(
            warehouseItemRepository.findItemsByWarehouseId(warehouseId, page)
        );
        return respones;
    }

}