package com.arbaazkhan.propertymanagement.service.impl;

import com.arbaazkhan.propertymanagement.converter.PropertyConverter;
import com.arbaazkhan.propertymanagement.dto.PropertyDTO;
import com.arbaazkhan.propertymanagement.entity.PropertyEntity;
import com.arbaazkhan.propertymanagement.entity.UserEntity;
import com.arbaazkhan.propertymanagement.exception.BusinessException;
import com.arbaazkhan.propertymanagement.exception.ErrorModel;
import com.arbaazkhan.propertymanagement.repository.PropertyRepository;
import com.arbaazkhan.propertymanagement.repository.UserRepository;
import com.arbaazkhan.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//We will have spring take care of object creation for this class, so it will take care of view/destroying memory for this class. this is done by using Spring Bean singleton
@Service //We are using the service annotation to create our singleton cuz this is the service layer
//THis class is made to implement (IMPL) the property service
//Factory design pattern is when there can be multiple impl class of 1 interface and based upon whose impl class object class gets created that object class gets executed
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        //get user data
       Optional<UserEntity> user = userRepository.findById(propertyDTO.getUserId());
       if (user.isPresent()){

           PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
           pe.setUserEntity(user.get());
           propertyRepository.save(pe); //store the data onto the DB
           propertyDTO = propertyConverter.convertEntitytoDTO(pe); //return saved data as dto

       } else {
           List<ErrorModel> errorModelList = new ArrayList<>();
           ErrorModel errorModel = new ErrorModel();
           errorModel.setCode("USER_ID_NOT_EXIST");
           errorModel.setMessage("User does not exist");
           errorModelList.add(errorModel);

           throw new BusinessException(errorModelList);
       }
       return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setDescription(propertyDTO.getDescription());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntitytoDTO(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setPrice(propertyDTO.getPrice());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntitytoDTO(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        //Optional helps us with doing null checks
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntitytoDTO(pe);
        }
        return dto;
    }

    @Override
    public List<PropertyDTO> getAllUserProperties(Long userId) {
        List<PropertyDTO> propertyList = new ArrayList<>();
        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAllByUserEntityId(userId);

        for(PropertyEntity pe: propertyEntityList){
            propertyList.add(propertyConverter.convertEntitytoDTO(pe));
        }

        return propertyList;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyDTO> propertyList = new ArrayList<>();
        List<PropertyEntity> propertyEntityList = (List<PropertyEntity>) propertyRepository.findAll();

        for(PropertyEntity pe: propertyEntityList){
            propertyList.add(propertyConverter.convertEntitytoDTO(pe));
        }

        return propertyList;
    }
}
