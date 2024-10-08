package com.rickyandmorti.rickyandmorti.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;


/**
 * @ControllerAdvice se utiliza para manejar excepciones de manera global en
 *                   toda la aplicación.
 *                   
 *                   Cualquier excepción que ocurra en cualquier controlador
 *                   dentro de la aplicación será capturada por los métodos
 *                   marcados con @ExceptionHandler.
 */

@ControllerAdvice
public class ExceptionHttpHandler {

  
    // Manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> errorResponse = Map.of(
            "error", "Un error ha ocurrido: " + ex.getMessage(),
            "status", HttpStatus.INTERNAL_SERVER_ERROR.value()
                    
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

 
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = Map.of(
            "error", ex.getMessage(),
            "status", HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

   // Manejo de excepciones  validación del  campos
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("errors", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // Manejo de SQLIntegrityConstraintViolationException 
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();

        if (rootCause instanceof java.sql.SQLIntegrityConstraintViolationException) {
            Map<String, Object> errorResponse = Map.of(
                "error", "El correo electrónico ya está en uso. Por favor, usa otro.",
                "status", HttpStatus.CONFLICT.value()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        // Si la excepción no es del tipo que queremos manejar, lanzamos una excepción genérica
        return handleAllExceptions(ex);
    }








    
}
