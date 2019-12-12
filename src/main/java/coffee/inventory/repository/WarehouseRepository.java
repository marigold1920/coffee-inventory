package coffee.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import coffee.inventory.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

	@Query("SELECT w FROM Warehouse w WHERE w.id != ?1 AND type = 'Warehouse'")
	Collection<Warehouse> findAllWarehouses(Integer warehouseId);

	@Query("SELECT w FROM Warehouse w WHERE type = 'Supplier'")
	Collection<Warehouse> findAllSuppliers();
}