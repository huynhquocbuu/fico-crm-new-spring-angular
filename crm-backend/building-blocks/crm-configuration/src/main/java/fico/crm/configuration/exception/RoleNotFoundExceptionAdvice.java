package fico.crm.configuration.exception;

import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RoleNotFoundExceptionAdvice {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ResponseWrapper<String>> handleRoleNotFoundException(
            RoleNotFoundException ex) {

        return ResponseHandler.error(
                ex.getMessage(),
                "E-002",
                "Role not found");
    }
}
