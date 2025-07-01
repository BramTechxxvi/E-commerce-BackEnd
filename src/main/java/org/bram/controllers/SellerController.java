package org.bram.controllers;

import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @PostMapping("/changeEmail")
    public ResponseEntity<ApiResponse> changeEmail(@RequestBody ChangeEmailRequest request) {
        return null;
    }
}
