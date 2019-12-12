package coffee.inventory.service.implement;

import org.springframework.stereotype.Service;

import coffee.inventory.adapter.StocktakingAdapter;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.service.StocktakingService;

@Service
public class StocktakingServiceImpl implements StocktakingService {

    @Override
    public ResponseModel saveStocktaking(StocktakingAdapter stocktakingAdapter) {
        
        return null;
    }
    
}