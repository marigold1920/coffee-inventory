package coffee.inventory.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coffee.inventory.adapter.Adapter;
import coffee.inventory.chain.PipeLineManager;
import coffee.inventory.common.ResponseModel;
import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionStatus;
import coffee.inventory.helper.PoolService;
import coffee.inventory.repository.TransactionRepository;
import coffee.inventory.service.ServiceHelper;
import coffee.inventory.service.TransactionService;
import coffee.inventory.factory.Creator;
import coffee.inventory.factory.TransactionAlgorithmsCreator;

@Service
public class TransactionServiceImpl implements TransactionService {

        private TransactionRepository transactionRepository;

        @Autowired
        public void setTransactionRepository(TransactionRepository transactionRepository) {
                this.transactionRepository = transactionRepository;
        }

        public ResponseModel saveTransaction(Adapter adapter, ServiceHelper service) {
                PoolService poolService = new PoolService();
                PipeLineManager initiator = new PipeLineManager(adapter, poolService, service);
                Creator creator = new TransactionAlgorithmsCreator();
                creator.initData(adapter.getType(), initiator);
                ResponseModel response = new ResponseModel();
                transactionRepository.saveAndFlush((Transaction) adapter.build(poolService));

                return response.finishRequest();
        }

        @Override
        public ResponseModel finishTransaction(Integer transactionId, TransactionStatus status) {
                ResponseModel response = new ResponseModel();
                transactionRepository.finishTransaction(transactionId, status);

                return response.finishRequest();
        }
}