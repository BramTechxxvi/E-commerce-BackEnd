package org.bram.services;

import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private SellerServicesImpl sellerServices;

    @BeforeEach
    public void cleanDb(){

    }


    @Test
    public void register_success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john@doe.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542523");

        RegisterResponse registerResponse = authenticationService.register(registerRequest);
        assertNotNull(registerResponse);
        assertTrue(registerResponse.isSuccess());
    }

}