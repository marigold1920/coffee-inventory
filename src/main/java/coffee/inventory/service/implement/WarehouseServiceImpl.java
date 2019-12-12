package coffee.inventory.service.implement;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Warehouse;
import coffee.inventory.repository.WarehouseRepository;
import coffee.inventory.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;

    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {

        return warehouseRepository.saveAndFlush(warehouse);
    }

    public Collection<Warehouse> findWarehousesById(Integer... warehouseIds) {

        return warehouseRepository.findAllById(Arrays.asList(warehouseIds));
    }

    @Override
    public ResponseModel getAllWarehouses(Integer warehouseId) {
        ResponseModel response = new ResponseModel();
        response.addData(warehouseRepository.findAllWarehouses(warehouseId));

        return response.finishRequest();
    }

    @Override
    public ResponseModel getAllSuppliers() {
        ResponseModel response = new ResponseModel();
        response.addData(warehouseRepository.findAllSuppliers());
        
        return response;
    }
}