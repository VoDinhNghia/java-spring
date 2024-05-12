package spring.springboot.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.springboot.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID>{

    @Query(value = "SELECT * from categories WHERE name LIKE CONCAT('%', :searchKey, '%')", nativeQuery = true)
    List<CategoryEntity> search(String searchKey);
}
