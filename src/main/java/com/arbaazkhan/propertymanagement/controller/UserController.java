package com.arbaazkhan.propertymanagement.controller;

import com.arbaazkhan.propertymanagement.dto.UserDTO;
import com.arbaazkhan.propertymanagement.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@Valid allows for error handling that was set up in UserDTO
    @ApiOperation(value = "register", notes = "This method is used for user registration") //Swagger annotation for detail endpoint
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@ApiParam( //this is extra swagger annotations to add more info to our documentation
            name = "userDTO",
            type = "UserDTO",
            value = "User data",
            example = "user information",
            required = true) @Valid @RequestBody UserDTO dto){
        dto = userService.register(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //Elaborate Mapping with path, consumes. and produces. It is good practice to explicitly state what the endpoint consumes and produces
    @PostMapping(path ="/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO dto) {
        dto = userService.login(dto.getEmail(), dto.getPassword());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
