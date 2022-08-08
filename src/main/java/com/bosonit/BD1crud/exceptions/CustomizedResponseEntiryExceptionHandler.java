package com.bosonit.BD1crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntiryExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IdNoEncontrada.class)
    public final ResponseEntity<CustomError> IdNoEncontradaException(IdNoEncontrada ex, WebRequest request) {
        CustomError customError = new CustomError(
           new Date(),
           ex.getMessage(),
            HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<CustomError> UnprocesableExceptionEx(UnprocesableException ex, WebRequest request) {
        CustomError customError = new CustomError(
                new Date(),
                ex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value()
        );
        return new ResponseEntity<>(customError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
