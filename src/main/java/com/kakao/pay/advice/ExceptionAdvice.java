package com.kakao.pay.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
 
	/**
	 * 예외 처리 핸들러
	 * 
	 * @param request 리퀘스트
	 * @param response 리스폰스
	 * @param handler 핸들러
	 * @param exception 예외
	 * @return
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> excpetionHandler(HttpServletRequest request
                                   , HttpServletResponse response
                                   , Object handler
                                   , Exception exception) {
        
    	
    	ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return entity;
    }
    
}