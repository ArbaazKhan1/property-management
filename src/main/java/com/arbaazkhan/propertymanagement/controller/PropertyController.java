package com.arbaazkhan.propertymanagement.controller;

import com.arbaazkhan.propertymanagement.dto.PropertyDTO;
import com.arbaazkhan.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Required annotation. Spring feature that allows for this java class to be able to interact with the web-browser
@RestController
//allows for our controller to access/receive requests from a particular mapping
//Its good practice to have requestMapping to add specificity to the url, also v1 is foot to maintain version control
@RequestMapping("/api/v1")
public class PropertyController {

    //How to access specific environment properties from code, the : allows for us to avoid null exceptions for whatever enviro that does not have these variables
    //THis will work for any class that is annotated that creates a bean
    @Value("${pms.dummy:}")
    private String dummy;
    @Value("${spring.datasource.url:}")
    private String dbURL;

    //Dependency injection, use autowired to create an object of the impl class(since we asked it to be a singleton (i.e @Service)) and the that object will be pointed by the PropertyService interface
    @Autowired  //autowired allows for the program to know to save the PropertyDTO data onto an instance of property service and then send that propertyService instance to whomever needs it(i.e. PropertyServiceImpl with its @service)
    private PropertyService propertyService;

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {
        propertyDTO = propertyService.saveProperty(propertyDTO);
//        System.out.println(propertyDTO);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        //wil print out what dummy is saved as in the environment(application.properties) the code is currently running in
//        System.out.println(dummy);
//        System.out.println(dbURL);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllUserProperties(@PathVariable("userId") Long userId) {
        List<PropertyDTO> propertyList = propertyService.getAllUserProperties(userId);
        return new ResponseEntity<>(propertyList, HttpStatus.OK);
    }

    //Put mapping can be considered a full update to the PropertyDTO
    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO dto, @PathVariable Long propertyId) {
        dto = propertyService.updateProperty(dto,propertyId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //Patch mapping is used for partial update on the PropertyDTO
    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO dto, @PathVariable Long propertyId) {
        dto = propertyService.updatePropertyDescription(dto,propertyId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO dto, @PathVariable Long propertyId) {
        dto = propertyService.updatePropertyPrice(dto,propertyId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Object> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
