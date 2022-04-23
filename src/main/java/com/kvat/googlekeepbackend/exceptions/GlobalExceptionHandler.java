package com.kvat.googlekeepbackend.exceptions;

import com.kvat.googlekeepbackend.dtos.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception){
        String message= exception.getMessage();
        ApiResponseDto apiResponseDto=new ApiResponseDto();
        apiResponseDto.setMessage(message);
        apiResponseDto.setSuccess(false);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        Map<String, String> errorResponse= new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error->{
            String fieldName= ((FieldError)error).getField();
            String message= error.getDefaultMessage();
            errorResponse.put(fieldName,message);
        });
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
