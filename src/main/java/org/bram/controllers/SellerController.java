package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.UpdateSellerProfileRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.*;
import org.bram.services.SellerServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerServicesImpl sellerServices;

    @Autowired
    public SellerController(SellerServicesImpl sellerServices) {
        this.sellerServices = sellerServices;
    }

    @PutMapping("/changeEmail")
    public ResponseEntity<ApiResponse> changeEmail(@RequestBody @Valid ChangeEmailRequest request) {
        try {
            ApiResponse response = sellerServices.changeEmail(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException | UserNotLoggedInException | SameEmailException | IncorrectOldEmailException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false));
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        try {
            ApiResponse response = sellerServices.changePassword(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException | UserNotLoggedInException | SamePasswordException | IncorrectOldPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false));
        }
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody UpdateSellerProfileRequest request) {
        try {
            ApiResponse response = sellerServices.updateProfile(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException | UserNotLoggedInException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false));
        }
    }
 }
