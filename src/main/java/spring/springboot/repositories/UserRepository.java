package spring.springboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.springboot.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
}
