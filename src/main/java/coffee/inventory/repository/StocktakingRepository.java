package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.Stocktaking;

public interface StocktakingRepository extends JpaRepository<Stocktaking, Integer> {

}
