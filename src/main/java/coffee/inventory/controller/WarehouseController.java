package coffee.inventory.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.adapter.TransactionAdapter;
import coffee.inventory.common.ResponseModel;

@RestController
@RequestMapping("api/v1.0/warehouses")
public class WarehouseController {

    @PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseModel saveTransaction(@RequestBody TransactionAdapter transactionAdapter) {

        return null;
    }
}