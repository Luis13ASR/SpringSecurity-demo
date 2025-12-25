package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
