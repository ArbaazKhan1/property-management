package com.arbaazkhan.propertymanagement.service.impl;

import com.arbaazkhan.propertymanagement.converter.UserConverter;
import com.arbaazkhan.propertymanagement.dto.UserDTO;
import com.arbaazkhan.propertymanagement.entity.UserEntity;
import com.arbaazkhan.propertymanagement.exception.BusinessException;
import com.arbaazkhan.propertymanagement.exception.ErrorModel;
import com.arbaazkhan.propertymanagement.repository.UserRepository;
import com.arbaazkhan.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDTO register(UserDTO userDTO) {
        //Check if user already exists
        Optional<UserEntity> entity = userRepository.findByEmail(userDTO.getEmail());
        if (entity.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessage("The Email Which You Are Trying To Register Already Exists!");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        UserEntity ue = userConverter.convertDTOtoEntity(userDTO);
        ue = userRepository.save(ue);
        userDTO = userConverter.convertEntitytoDTO(ue);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO dto = null;
        Optional<UserEntity> entity = userRepository.findByEmailAndPassword(email, password);

        if (entity.isPresent()){
            dto = userConverter.convertEntitytoDTO(entity.get());
        } else {    //Exception handling for bad login
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        return dto;
    }
}
