package com.arbaazkhan.propertymanagement.repository;

import com.arbaazkhan.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

//CrudRepo takes 2 inputs, the first is the object you want to pass into the repo and the second is the datatype of the primary key of said object
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
