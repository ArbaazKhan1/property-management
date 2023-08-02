package com.arbaazkhan.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//Just a example class, NOT need for project
@Data
public class CalculatorDTO {
    private Double num1;
    private Double num2;
    private Double num3;
    @JsonProperty("num41")  //Allows for client side to input variable name that is different from num4
    private Double num4;
}
