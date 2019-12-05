package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.WarehouseItem;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer> {
    
}