package com.example.demo.Services.Impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTOs.UserDTO;
import com.example.demo.Entities.RolesEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.RolRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    private RolRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.rolesRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, readOnly = false)
    public ResponseEntity<String> create(UserDTO userDTO){

        try{

            Optional<RolesEntity> optionalRolesEntity = rolesRepository.findByName(userDTO.getRol().toUpperCase());

            if(!optionalRolesEntity.isPresent()){
                return ResponseEntity.badRequest().body("the role was not found");
            }

            if(userRepository.existsByEmail(userDTO.getEmail())){
                return ResponseEntity.badRequest().body("the given email is already registered");
            }

            if(userRepository.existsByUsername(userDTO.getUsername())){
                return ResponseEntity.badRequest().body("the given username is already registered");
            }

            RolesEntity rolesEntity = optionalRolesEntity.get();

            UserEntity userEntity = new UserEntity();

            userEntity.setEmail(userDTO.getEmail());
            userEntity.setUsername(userDTO.getUsername());
            userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userEntity.setIdRol(rolesEntity);
            userRepository.save(userEntity);

            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
