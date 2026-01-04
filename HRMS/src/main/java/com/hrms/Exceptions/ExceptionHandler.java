package com.hrms.Exceptions;

import com.hrms.ResponseDTO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                "CompanyController already exists with email: " + email
        );
    }

    public static ResponseStatusException companyNotFoundWithEmail(String email) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "CompanyController not found with email: " + email
        );
    }

    public static ResponseStatusException handleBadRequest() {

        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "CompanyId is mandatory when Super Admin creates an employee "
        );
    }



    public static ResponseStatusException companyNotFoundWithId(Long companyId) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "CompanyController not found with email: " + companyId
        );
    }

    public static Object empNotFoundWithEmail(String email) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Employee not found with email: " + email
        );
    }


    public static class DuplicateDepartmentException extends RuntimeException {
        public DuplicateDepartmentException(String message) {
            super(message);
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateDepartmentException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicate(
            DuplicateDepartmentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(409,false,null, ex.getMessage()));
    }
}
