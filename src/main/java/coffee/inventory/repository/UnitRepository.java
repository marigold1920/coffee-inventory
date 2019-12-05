package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    
}