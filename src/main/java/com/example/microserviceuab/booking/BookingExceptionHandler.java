package com.example.microserviceuab.booking;

import com.example.microserviceuab.common.ClientNotFoundException;
import com.example.microserviceuab.common.ExceptionResponseDto;
import com.example.microserviceuab.common.NoAvailabilityException;
import com.example.microserviceuab.common.RoomNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookingController.class)
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingExceptionHandler.class);

    @ExceptionHandler(NoAvailabilityException.class)
    public ResponseEntity<ExceptionResponseDto> handleNoAvailability(NoAvailabilityException ex) {
        LOGGER.error("handleNoAvailability", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("No availability between the requested dates"),
                HttpStatusCode.valueOf(HttpStatus.CONFLICT.value())
        );
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleClientNotFound(ClientNotFoundException ex) {
        LOGGER.error("handleClientNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Client not found"),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleRoomNotFound(RoomNotFoundException ex) {
        LOGGER.error("handleRoomNotFound", ex);
        return new ResponseEntity<>(
                new ExceptionResponseDto("Room not found"),
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

}
