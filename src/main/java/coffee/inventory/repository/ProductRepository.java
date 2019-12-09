package coffee.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coffee.inventory.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}