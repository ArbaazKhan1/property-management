package com.arbaazkhan.propertymanagement.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor(force = true)
public class BusinessException extends RuntimeException{

    private final List<ErrorModel> errors;

    public BusinessException(List<ErrorModel> errors){
        this.errors=errors;
    }
}
