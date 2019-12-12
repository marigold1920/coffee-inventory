package coffee.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.service.WarehouseService;

@RestController
@RequestMapping(value = "api/v1.0", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
public class WarehouseController {

    private WarehouseService warehouseService;

    @Autowired
    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping(value = "warehouses")
    public ResponseModel getAllWarehouses(@RequestParam Integer warehouseId) {
        
        return warehouseService.getAllWarehouses(warehouseId);
    }

    @GetMapping("suppliers")
    public ResponseModel getAllSuppliers() {
        
        return warehouseService.getAllSuppliers();
    }
}