package com.arbaazkhan.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

//Spring knows this is the class to look for when you have a particular exception is thrown
@ControllerAdvice
public class CustomExeptionHandler {

    //Let's spring know that this is the function it should take when BusinessException is thrown
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException exp) {
        System.out.println("BusinessException is thrown!!");
        return new ResponseEntity<>(exp.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
