package com.example.microserviceuab.user;

import com.example.microserviceuab.common.ClientNotFoundException;
import com.example.microserviceuab.common.ExceptionResponseDto;
import com.example.microserviceuab.common.UsernameAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ClientController.class)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientExceptionHandler.class);

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        LOGGER.error("handleUsernameAlreadyExists", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Username already exists"), HttpStatusCode.valueOf(HttpStatus.CONFLICT.value())
        );
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleClientNotFound(ClientNotFoundException ex) {
        LOGGER.error("handleClientNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Client not found"), HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value())
        );
    }

}
