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
        return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectOldPasswordException.class)
    public ResponseEntity<ApiResponse> handleWrongOldPassword(IncorrectOldPasswordException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectOldEmailException.class)
    public ResponseEntity<ApiResponse> handleIncorrectOldEmail(IncorrectOldEmailException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiResponse> handleIncorrectPassword(IncorrectPasswordException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProductCategory.class)
    public ResponseEntity<ApiResponse> handleInvalidProductCategory(InvalidProductCategory e) {
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ApiResponse> handleInvalidRole(InvalidRoleException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse> handleInvalidToken(InvalidTokenException e) {
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<ApiResponse> buildResponse(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ApiResponse(message, false));
    }

}
