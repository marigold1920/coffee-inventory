package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionStatus;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Transaction t SET t.status = ?2 WHERE t.id = ?1")
	Integer finishTransaction(Integer transactionId, TransactionStatus status);
    
}