package coffee.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import coffee.inventory.entity.WarehouseItem;

public interface WarehouseItemRepository extends JpaRepository<WarehouseItem, Integer> {

    @Query("SELECT i FROM WarehouseItem i WHERE i.warehouse.id = :warehouseId AND i.item.id in :itemIds")
    Collection<WarehouseItem> findAllByItemId(@Param("warehouseId")Integer warehouseId, @Param("itemIds") Iterable<Integer> itemIds);
}