package com.arbaazkhan.propertymanagement.repository;

import com.arbaazkhan.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CrudRepo takes 2 inputs, the first is the object you want to pass into the repo and the second is the datatype of the primary key of said object
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
    //An alternate way to write query statements
    //@Query("SELECT p FROM PropertyEntity p WHERE p.userEntity.id = :userId AND p.title = :title")
    //List<PropertyEntity> findAllByUserEntityId(@Param("userId") Long userId, @Param("title") Long title);
    List<PropertyEntity> findAllByUserEntityId(Long userId);
}
