/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.social.handler;

import com.social.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author DinhChuong
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleTest(Exception exception){
        ErrorResponse e = new ErrorResponse();
        // set some field
        return new ResponseEntity<>(e, HttpStatus.INSUFFICIENT_STORAGE);
    }
    
}
