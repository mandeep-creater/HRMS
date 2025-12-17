package com.hrms.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionHandler {

    public static void throwUserExists(String email) {
        throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "User already exists with email: " + email
        );
    }

    public static <T> RuntimeException notFound(Class<T> clazz, Object id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                clazz.getSimpleName() + " not found with ID: " + id
        );
    }

    public static ResponseStatusException companyAlreadyExists(String email) {
        return new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Company already exists with email: " + email
        );
    }

    public static ResponseStatusException companyNotFoundWithEmail(String email) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Company not found with email: " + email
        );
    }
}
