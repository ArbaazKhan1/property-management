package com.arbaazkhan.propertymanagement.controller;

import com.arbaazkhan.propertymanagement.dto.UserDTO;
import com.arbaazkhan.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto){
        dto = userService.register(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO dto) {
        dto = userService.login(dto.getEmail(), dto.getPassword());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
