package com.social.handler;

import com.social.dto.response.ErrorResponse;
import com.social.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author DinhChuong
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<NotFoundException> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value = {
//        IllegalArgumentException.class,
//        IllegalStateException.class
//    })
//    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//        String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, ex,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
    
//    @Override
//    protected void handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        System.out.println("MethodArgumentNotValidException");
////        return ResponseEntity.status(status)
////                .body("Validation errors occurred: " + ex.getBindingResult().getAllErrors());
//    }
    
    
}
