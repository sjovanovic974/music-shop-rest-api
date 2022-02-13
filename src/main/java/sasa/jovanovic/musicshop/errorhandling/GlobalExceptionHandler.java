package sasa.jovanovic.musicshop.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(formatDateTime((LocalDateTime.now())));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(formatDateTime((LocalDateTime.now())));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTime.format(formatter);
    }
}
