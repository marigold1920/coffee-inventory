package coffee.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import coffee.inventory.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    
    @Query("SELECT u FROM Unit u WHERE u.name in :unitNames")
    Collection<Unit> findAllByName(@Param("unitNames") Iterable<String> unitNames);
}