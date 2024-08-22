package com.rickyandmorti.rickyandmorti.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Manejo de excepciones específicas (puedes agregar más métodos según tus necesidades)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = Map.of(
            "error", ex.getMessage(),
            "status", HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    









    
}
