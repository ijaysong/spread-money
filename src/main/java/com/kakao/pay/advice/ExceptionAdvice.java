package com.kakao.pay.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> excpetionHandler(HttpServletRequest request
                                   , HttpServletResponse response
                                   , Object handler
                                   , Exception exception) {
        
    	
    	ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return entity;
    }
    
}