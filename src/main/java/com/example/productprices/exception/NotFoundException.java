package com.example.productprices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class NotFoundException extends Exception {
    private HttpStatus httpStatus = null;
    private String message = null;
    
   
    public NotFoundException (HttpStatus httpStatus, String message) {
    	this.httpStatus = httpStatus;
    	this.message = message;
    }
    
}
