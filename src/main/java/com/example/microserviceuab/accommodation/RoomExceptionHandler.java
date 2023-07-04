package com.example.microserviceuab.accommodation;

import com.example.microserviceuab.common.AccommodationNotFoundException;
import com.example.microserviceuab.common.ExceptionResponseDto;
import com.example.microserviceuab.common.RoomAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RoomController.class)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomExceptionHandler.class);

    @ExceptionHandler(AccommodationNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleAccommodationNotFound(AccommodationNotFoundException ex) {
        LOGGER.error("handleAccommodationNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Accommodation not found"),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDto> handleRoomAlreadyExists(RoomAlreadyExistsException ex) {
        LOGGER.error("handleRoomAlreadyExists", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("A room with the same name already exists"),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

}
