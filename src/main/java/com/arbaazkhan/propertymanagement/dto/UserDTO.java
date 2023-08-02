package com.arbaazkhan.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//THese 2 will allow it so that when we return the dto back to the client it will not return null values, This is helpful cuz we don't want to return password so when we are receiving it from thr repo we just don't set it in the dto thus it being nul and us not wanting it there
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
