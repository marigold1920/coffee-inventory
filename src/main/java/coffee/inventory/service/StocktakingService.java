package coffee.inventory.service;

import coffee.inventory.adapter.StocktakingAdapter;
import coffee.inventory.common.ResponseModel;

public interface StocktakingService {

	ResponseModel saveStocktaking(StocktakingAdapter stocktakingAdapter);
    
}