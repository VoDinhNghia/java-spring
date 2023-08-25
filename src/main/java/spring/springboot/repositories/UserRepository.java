package spring.springboot.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.springboot.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    UserEntity findByCode(String code);

    @Query(value = "SELECT * from users WHERE name LIKE CONCAT('%', :searchKey, '%')", nativeQuery = true)
    List<UserEntity> search(String searchKey);
}
