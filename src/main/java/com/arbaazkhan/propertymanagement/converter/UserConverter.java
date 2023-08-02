package com.arbaazkhan.propertymanagement.converter;

import com.arbaazkhan.propertymanagement.dto.UserDTO;
import com.arbaazkhan.propertymanagement.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setEmail(userDTO.getEmail());
        entity.setName(userDTO.getName());
        entity.setPhone(userDTO.getPhone());
        entity.setPassword(userDTO.getPassword());
        return entity;
    }

    public UserDTO convertEntitytoDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
