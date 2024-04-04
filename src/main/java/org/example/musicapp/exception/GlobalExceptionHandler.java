package org.example.musicapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public String handleSQLInternalViolation(SQLIntegrityConstraintViolationException exception){
//        return exception.getMessage();
//    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidArgumentException(MethodArgumentNotValidException exception){
//        Map<String, String> errors = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach( fieldError -> errors.put(fieldError.getField(),
//                fieldError.getDefaultMessage()));
//        return errors;
//    }
////    @ResponseStatus(HttpStatus.FORBIDDEN)
////    @ExceptionHandler(UserPrincipalNotFoundException.class)
////    public String handleIllegalArgumentException(IllegalArgumentException exception){
////        return exception.getMessage();
//   // }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(UserPrincipalNotFoundException.class)
//    public String handleUserNotFundException(UserPrincipalNotFoundException exception){
//        return exception.getMessage();
//    }
//    package com.Davidson.MusicApp.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
//    @RestControllerAdvice
//    public class GlobalExceptionHandler {

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public String handleSQLIntegrityViolation(SQLIntegrityConstraintViolationException exception){
            return exception.getMessage();
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handleMethodArgumentInvalidException(MethodArgumentNotValidException exception){
            Map<String, String> errorMap = new HashMap<>();
            exception.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
//        BindingResult bindingResult = exception.getBindingResult();
//        List<FieldError> errors = bindingResult.getFieldErrors();
//        for( FieldError error: errors ){
//            errorMap.put(error.getField(), error.getDefaultMessage());
//        }
            return errorMap;
        }

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(NoSuchElementException.class)
        public String handleNoSuchElementException(NoSuchElementException exception){
            return exception.getMessage();
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public String handleArgumentTypeMismatch(MethodArgumentTypeMismatchException exception){
            return exception.getMessage();
        }
    }

