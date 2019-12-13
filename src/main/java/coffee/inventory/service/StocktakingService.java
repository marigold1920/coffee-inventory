package coffee.inventory.service;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.common.ResponseModel;

public interface StocktakingService {

	ResponseModel saveStocktaking(Adapter stocktakingAdapter, ServiceHelper service);
    
}