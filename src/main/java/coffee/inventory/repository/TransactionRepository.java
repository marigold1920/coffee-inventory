package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    
}