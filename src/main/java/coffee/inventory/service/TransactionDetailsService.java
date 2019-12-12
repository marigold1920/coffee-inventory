package coffee.inventory.service;

import coffee.inventory.common.ResponseModel;

public interface TransactionDetailsService {

	ResponseModel findAllHistoriesOfItem(Integer itemId, Integer warehouseId);

}