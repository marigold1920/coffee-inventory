package coffee.inventory.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import coffee.inventory.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.name in :categoryNames")
    Collection<Category> findAllByName(@Param("categoryNames") Iterable<String> categoryNames);
}