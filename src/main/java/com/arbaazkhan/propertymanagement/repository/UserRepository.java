package com.arbaazkhan.propertymanagement.repository;

import com.arbaazkhan.propertymanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    //Don't need to have @Query statements, cuz we are using jpa to do the heavy lifting. Just make sure you use "findBy" followed by the variables you want to search with that exist in the Object being passed into the CrudRepo
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByEmail(String email);
}
