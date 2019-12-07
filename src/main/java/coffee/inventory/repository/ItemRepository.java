package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}