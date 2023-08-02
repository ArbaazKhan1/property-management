package com.arbaazkhan.propertymanagement.service.impl;

import com.arbaazkhan.propertymanagement.converter.UserConverter;
import com.arbaazkhan.propertymanagement.dto.UserDTO;
import com.arbaazkhan.propertymanagement.entity.UserEntity;
import com.arbaazkhan.propertymanagement.repository.UserRepository;
import com.arbaazkhan.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity ue = userConverter.convertDTOtoEntity(userDTO);
        ue = userRepository.save(ue);
        userDTO = userConverter.convertEntitytoDTO(ue);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
