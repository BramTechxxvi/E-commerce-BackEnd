package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.UserNotFoundException;
import org.bram.services.SellerServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerServicesImpl sellerServices;

    @Autowired
    public SellerController(SellerServicesImpl sellerServices) {
        this.sellerServices = sellerServices;
    }

    @PostMapping("/changeEmail")
    public ResponseEntity<ApiResponse> changeEmail(@RequestBody @Valid ChangeEmailRequest request) {
        try {
            ApiResponse response = sellerServices.changeEmail(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false));
        }
    }
}
