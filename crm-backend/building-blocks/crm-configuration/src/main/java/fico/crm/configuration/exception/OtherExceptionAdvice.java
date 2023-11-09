package fico.crm.configuration.exception;

import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class OtherExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<String>> handleRoleNotFoundException(
            Exception ex) {

        return ResponseHandler.error(
                ex.getMessage(),
                "E-999",
                "Internal Service Exception");
    }
}
