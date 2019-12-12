package coffee.inventory.repository;

import coffee.inventory.entity.TransactionDetails;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {

	@Query("SELECT t FROM TransactionDetails t WHERE t.item.id = ?1 AND (t.transaction.fromWarehouse.id = ?2 OR t.transaction.toWarehouse.id = ?2)")
	Collection<TransactionDetails> findAllHistoriesOfItem(Integer itemId, Integer warehouseId);

}
