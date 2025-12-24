package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.RolesEntity;

@Repository
public interface RolRepository extends JpaRepository<RolesEntity, Long>{
    Optional<RolesEntity> findByName(String name); 
}
