package org.bram.services;

import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }
}
