package coffee.inventory.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Stocktaking;
import coffee.inventory.factory.Creator;
import coffee.inventory.factory.TransactionAlgorithmsCreator;
import coffee.inventory.helper.PoolService;
import coffee.inventory.repository.StocktakingRepository;
import coffee.inventory.service.ServiceHelper;
import coffee.inventory.service.StocktakingService;

@Service
public class StocktakingServiceImpl implements StocktakingService {

    private StocktakingRepository stocktakingRepository;

    @Autowired
    public void setStocktakingRepository(StocktakingRepository stocktakingRepository) {
        this.stocktakingRepository = stocktakingRepository;
    }

    @Override
    public ResponseModel saveStocktaking(Adapter adapter, ServiceHelper service) {
        ResponseModel response = new ResponseModel();
        PoolService poolService = new PoolService();
        PipeLineManager initiator = new PipeLineManager(adapter, poolService, service);
        Creator creator = new TransactionAlgorithmsCreator();
        creator.initData(adapter.getType(), initiator);
        // response.addData(
        stocktakingRepository.saveAndFlush((Stocktaking) adapter.build(poolService));
        // );

        return response.finishRequest();
    }
    
}