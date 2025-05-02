package dev.Zerpyhis.VibeEvents.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(EventsNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEventNotFound(EventsNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


}
