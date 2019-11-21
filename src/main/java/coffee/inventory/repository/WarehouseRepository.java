package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}