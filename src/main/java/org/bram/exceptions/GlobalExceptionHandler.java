package org.bram.exceptions;

import org.bram.dtos.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDenied(AccessDeniedException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DetailsAlreadyInUseException.class)
    public ResponseEntity<ApiResponse> handleDetailsAlreadyInUse(DetailsAlreadyInUseException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ApiResponse> handleImageUpload(ImageUploadException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ApiResponse> buildResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ApiResponse(message, false));
    }

}
