package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.UpdateSellerProfileRequest;
import org.bram.dtos.response.ApiResponse;
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
        ApiResponse response = sellerServices.changeEmail(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        ApiResponse response = sellerServices.changePassword(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody UpdateSellerProfileRequest request) {
        ApiResponse response = sellerServices.updateProfile(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
 }
