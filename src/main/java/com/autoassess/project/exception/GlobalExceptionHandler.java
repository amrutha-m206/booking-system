package com.autoassess.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //If a controller throws an exception,send it to this class first.
public class GlobalExceptionHandler {

     @ExceptionHandler(RuntimeException.class)
      public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){
         ErrorResponse error=new ErrorResponse();
         error.setMessage(ex.getMessage());
         log.error("Exception occurred", ex);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
