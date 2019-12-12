package coffee.inventory.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.service.WarehouseItemService;

@RestController
@RequestMapping(value = "api/v1.0/items", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE } )
public class WarehouseItemController {

    private WarehouseItemService warehouseItemService;

    public WarehouseItemController(WarehouseItemService warehouseItemService) {
        this.warehouseItemService = warehouseItemService;
    }

    @GetMapping
    public ResponseModel getWarehouseItems(@RequestParam int pageIndex, int warehouseId) {

        return warehouseItemService.getWarehouseItems(pageIndex, warehouseId);
    }
}