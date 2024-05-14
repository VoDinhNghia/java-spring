package spring.springboot.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.springboot.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query(value = "SELECT * from products WHERE name LIKE CONCAT(%, :searchKey,%)", nativeQuery = true)
    List<ProductEntity> search(String searchKey);

}