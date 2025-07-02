package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.UpdateCustomerProfileRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.services.CustomerServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServicesImpl customerServices;

    @Autowired
    public CustomerController(CustomerServicesImpl customerServices) {
        this.customerServices = customerServices;
    }

    @PutMapping("/changeEmail")
    public ResponseEntity<ApiResponse> changeEmail(@RequestBody @Valid ChangeEmailRequest request) {
        ApiResponse response = customerServices.changeEmail(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        ApiResponse response = customerServices.changePassword(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<ApiResponse> updateProfile(@RequestBody UpdateCustomerProfileRequest request) {
        ApiResponse response = customerServices.updateProfile(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
