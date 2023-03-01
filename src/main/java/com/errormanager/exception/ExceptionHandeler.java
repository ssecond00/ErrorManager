package com.errormanager.exception;

import com.errormanager.dto.geterror.GetErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ExceptionHandeler extends ResponseEntityExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandeler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GetErrorResponse> exceptionHandle() {
        GetErrorResponse resp = GetErrorResponse.builder()
                .errorCode("500")
                .errorMessage("Ocurrio un error en la comunicacion con la base de datos.")
                .build();
        LOGGER.error("Se genera una exception por error en la comunicacion con la base de datos.");
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GetErrorResponse resp = GetErrorResponse.builder()
                .errorCode("400")
                .errorMessage("Bad Request. Revise los campos obligatorios en el swagger y vuelva a intertarlo.")
                .build();
        LOGGER.error("Bad Request. Revise los campos obligatorios en el swagger y vuelva a intertarlo.");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
