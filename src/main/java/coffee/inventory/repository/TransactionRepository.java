package coffee.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import coffee.inventory.entity.Transaction;
import coffee.inventory.enumeration.TransactionStatus;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("UPDATE Transaction t SET t.status = ?2 WHERE t.transactionId = ?1")
	Optional<Transaction> finishTransaction(Integer transactionId, TransactionStatus status);
    
}