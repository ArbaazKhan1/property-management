package com.arbaazkhan.propertymanagement.controller;

import com.arbaazkhan.propertymanagement.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  This an examlpe class to test out/ see different concepts
 */
@RestController
@RequestMapping("/api/v1/calculator")   //Class level mapping of a url to a controller class
public class CalculatorController {

    @GetMapping("/add") //Method level mapping of a url to a controller function
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2) {
        return num1+num2;
    }

    @GetMapping("/sub/{num1}/{num2}") //Map values of url to java variables bu path variable method
    public Double sub(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2){
        Double result = null;
        if (num1>num2){
            result = num1-num2;
        } else {
            result = num2-num1;
        }
        return  result;
    }

    @PostMapping("/mult")
    public ResponseEntity<Double> mult(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = null;
        result = calculatorDTO.getNum1()* calculatorDTO.getNum2() * calculatorDTO.getNum3() * calculatorDTO.getNum4();

        ResponseEntity<Double> responseEntity = new ResponseEntity<>(result, HttpStatus.CREATED); //With PostMapping you pass created(201)
        return responseEntity;
    }
}
