package coffee.inventory.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coffee.inventory.common.ResponseModel;
import coffee.inventory.repository.TransactionDetailsRepository;
import coffee.inventory.service.TransactionDetailsService;

@Service
@Transactional
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

    private TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    public void setTransactionDetailsRepository(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    @Override
    public ResponseModel findAllHistoriesOfItem(Integer itemId, Integer warehouseId) {
        ResponseModel response = new ResponseModel();
        response.addData(transactionDetailsRepository.findAllHistoriesOfItem(itemId, warehouseId));

        return response.finishRequest();
    }

}