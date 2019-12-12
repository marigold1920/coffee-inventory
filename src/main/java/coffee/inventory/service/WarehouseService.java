package coffee.inventory.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Warehouse;

@Service
@Transactional
public interface WarehouseService {
    Warehouse saveWarehouse(Warehouse warehouse);    
    Collection<Warehouse> findWarehousesById(Integer... warehouseIds);
	ResponseModel getAllWarehouses(Integer warehouseId);
	ResponseModel getAllSuppliers();
}