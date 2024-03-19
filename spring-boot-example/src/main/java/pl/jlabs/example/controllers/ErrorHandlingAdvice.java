package pl.jlabs.example.controllers;

import pl.jlabs.example.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandlingAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, ?> handle(NotFoundException exception) {
        return Map.of(
                "message", exception.getMessage(),
                "id", exception.getId()
        );
    }
}
