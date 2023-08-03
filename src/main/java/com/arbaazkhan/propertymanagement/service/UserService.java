package com.arbaazkhan.propertymanagement.service;

import com.arbaazkhan.propertymanagement.dto.UserDTO;


public interface UserService {
    UserDTO register(UserDTO userDTO);
    UserDTO login(String email, String password);
}
