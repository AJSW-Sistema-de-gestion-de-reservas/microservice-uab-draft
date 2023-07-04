package com.example.microserviceuab.accommodation;

import com.example.microserviceuab.common.AccommodationNotFoundException;
import com.example.microserviceuab.common.ExceptionResponseDto;
import com.example.microserviceuab.common.OwnerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AccommodationController.class)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AccommodationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationExceptionHandler.class);

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleOwnerNotFound(OwnerNotFoundException ex) {
        LOGGER.error("handleOwnerNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Owner not found"), HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(AccommodationNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleAccommodationNotFound(AccommodationNotFoundException ex) {
        LOGGER.error("handleAccommodationNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Accommodation not found"), HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

}
