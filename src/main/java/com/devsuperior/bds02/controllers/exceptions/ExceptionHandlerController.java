package com.devsuperior.bds02.controllers.exceptions;

import com.devsuperior.bds02.services.exceptions.ResourceNotFoundServiceException;
import com.devsuperior.bds02.services.exceptions.ViolationIntegrityServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundServiceException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundServiceException e, HttpServletRequest request) {

        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setError("Resource Not Found");
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(ViolationIntegrityServiceException.class)
    public ResponseEntity<StandardError> violationIntegrityException(ViolationIntegrityServiceException e, HttpServletRequest request) {

        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(HttpStatus.BAD_REQUEST.value());
        standardError.setError("integrity constraint violation");
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
