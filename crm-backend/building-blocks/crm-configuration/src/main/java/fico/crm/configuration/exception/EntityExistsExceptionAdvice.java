package fico.crm.configuration.exception;

import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class EntityExistsExceptionAdvice {
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ResponseWrapper<String>> handleRoleNotFoundException(
            EntityExistsException ex) {

        return ResponseHandler.error(
                ex.getMessage(),
                "E-003",
                "Data is existing");
    }
}
