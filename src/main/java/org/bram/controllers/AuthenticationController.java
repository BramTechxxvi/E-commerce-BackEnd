package org.bram.controllers;

import jakarta.validation.Valid;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.LogoutResponse;
import org.bram.dtos.response.RegisterResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
import org.bram.exceptions.IncorrectPasswordException;
import org.bram.exceptions.InvalidRoleException;
import org.bram.exceptions.InvalidTokenException;
import org.bram.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        try {
            RegisterResponse response = authenticationService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DetailsAlreadyInUseException e) {
            RegisterResponse response = new RegisterResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            LoginResponse response = authenticationService.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IncorrectPasswordException e) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestHeader("Authorization") String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Authorization header is missing or invalid");
        }
        String token = authHeader.substring(7);
        LogoutResponse response = authenticationService.logout(token);
        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

}
