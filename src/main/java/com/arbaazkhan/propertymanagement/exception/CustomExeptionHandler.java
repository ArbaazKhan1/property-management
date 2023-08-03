package com.arbaazkhan.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

//Spring knows this is the class to look for when you have a particular exception is thrown
@ControllerAdvice
public class CustomExeptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //Let's spring know that this is the function it should take when BusinessException is thrown
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException exp) {
        for (ErrorModel model : exp.getErrors()) {
            log.debug("BusinessException is thrown - level-debug: {} - {}", model.getCode(), model.getMessage());
            log.info("BusinessException is thrown - level-info: {} - {}", model.getCode(), model.getMessage());
            log.warn("BusinessException is thrown - level-warn: {} - {}", model.getCode(), model.getMessage());
            log.error("BusinessException is thrown - level-error: {} - {}", model.getCode(), model.getMessage());
        }

        return new ResponseEntity<>(exp.getErrors(), HttpStatus.BAD_REQUEST);
    }

    //Class is thrown by springboot framework
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();
        List<ErrorModel> errorModelList = new ArrayList<>();

        for(FieldError fe : fieldErrorList) {
            log.debug("Inside Field Validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            log.info("Inside Field Validation: {} - {}", fe.getField(), fe.getDefaultMessage());
            ErrorModel model = new ErrorModel();
            model.setCode(fe.getCode());
            model.setMessage(fe.getDefaultMessage());
            errorModelList.add(model);
        }

        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }
}
