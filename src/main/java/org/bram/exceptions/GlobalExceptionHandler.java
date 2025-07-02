package org.bram.exceptions;

import org.bram.dtos.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleProductNotFound(AccessDeniedException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



    private ResponseEntity<ApiResponse> buildResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ApiResponse(message, false));
    }

    // === HELPER ===
//    private ResponseEntity<ApiResponse> buildResponse(String message, HttpStatus status) {
//        return ResponseEntity.status(status).body(new ApiResponse(message, false));
//    }
}
