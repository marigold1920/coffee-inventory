package coffee.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coffee.inventory.entity.Warehouse;
import coffee.inventory.repository.WarehouseRepository;

@Service
@Transactional
public class WarehouseService {

    private WarehouseRepository warehouseRepository;

    /**
     * @param warehouseRepository the warehouseRepository to set
     */
    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {

        return warehouseRepository.saveAndFlush(warehouse);
    }
}