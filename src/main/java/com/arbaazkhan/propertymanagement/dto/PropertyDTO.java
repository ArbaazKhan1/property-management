package com.arbaazkhan.propertymanagement.dto;

import lombok.Getter;
import lombok.Setter;

//Lombok's annotations for easy create getters/setters for data classes
@Getter
@Setter
public class PropertyDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String address;
}
