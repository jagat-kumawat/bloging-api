package com.blog.exception;


import com.blog.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class gllobleexception {
    @ExceptionHandler(runexception.class)
    public ResponseEntity<ApiResponse>  resourcenotfoundexceptionhendler( runexception ex){
        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);

        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String ,String>> exceptionvalidation(MethodArgumentNotValidException ex){
        Map<String,String > he = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{

            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            he.put(field,defaultMessage);
        });
        return new  ResponseEntity<Map<String,String>>(he,HttpStatus.BAD_REQUEST);
}

}
