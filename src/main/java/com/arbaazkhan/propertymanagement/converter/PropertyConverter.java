package com.arbaazkhan.propertymanagement.converter;

import com.arbaazkhan.propertymanagement.dto.PropertyDTO;
import com.arbaazkhan.propertymanagement.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component  //will make this class a singleton, and we can inject(autowire) it into our service layer
public class PropertyConverter {

    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO) {
        PropertyEntity pe = new PropertyEntity();

        pe.setTitle(propertyDTO.getTitle());
        pe.setAddress(propertyDTO.getAddress());
        pe.setPrice(propertyDTO.getPrice());
        pe.setDescription(propertyDTO.getDescription());

        return pe;
    }

    public PropertyDTO convertEntitytoDTO(PropertyEntity propertyEntity) {
        PropertyDTO dto = new PropertyDTO();

        dto.setId(propertyEntity.getId());
        dto.setTitle(propertyEntity.getTitle());
        dto.setAddress(propertyEntity.getAddress());
        dto.setDescription(propertyEntity.getDescription());
        dto.setPrice(propertyEntity.getPrice());
        dto.setUserId(propertyEntity.getUserEntity().getId());

        return dto;
    }
}
