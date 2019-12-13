package coffee.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coffee.inventory.adapter.StocktakingAdapter;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.service.ServiceHelper;
import coffee.inventory.service.StocktakingService;

@RestController
@RequestMapping(value = "api/v1.0/stocktakings", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
public class StockTakingController {

    private StocktakingService stocktakingService;
    private ServiceHelper serviceHelper;

    @Autowired
    public void setServiceHelper(ServiceHelper serviceHelper) {
        this.serviceHelper = serviceHelper;
    }

    @Autowired
    public void setStocktakingService(StocktakingService stocktakingService) {
        this.stocktakingService = stocktakingService;
    }

    @PostMapping
    public ResponseModel saveStocktaking(@RequestBody StocktakingAdapter stocktakingAdapter) {
        
        return stocktakingService.saveStocktaking(stocktakingAdapter, serviceHelper);
    }
}