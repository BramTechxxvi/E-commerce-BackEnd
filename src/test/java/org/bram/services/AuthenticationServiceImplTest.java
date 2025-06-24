package org.bram.services;

import org.bram.data.repository.CustomerRepository;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
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
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private LoginResponse loginResponse;
    private LoginRequest loginRequest;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){
        registerRequest = new RegisterRequest();
        registerResponse = new RegisterResponse();
        customerRepository.deleteAll();
        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();

    }


    @Test
    public void registerACustomer__registerTest() {
        registerCustomer();
        assertNotNull(registerResponse);
        assertTrue(registerResponse.isSuccess());
        assertEquals(1, customerRepository.count());
    }

    @Test
    public void loginCustomer__loginTest() {
        registerACustomer__registerTest();
        loginRequest.setEmail("John@doe.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);
        assertEquals("We're happy to have you back", loginResponse.getMessage);

    }

    private void registerCustomer() {
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john@doe.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542523");
        registerRequest.setUserRole("CUSTOMER");
        registerRequest.setHouseNumber("125");
        registerRequest.setStreet("Bornu Way");
        registerRequest.setCity("Lagos");
        registerRequest.setState("Lagos State");
        registerRequest.setCountry("Nigeria");
        registerResponse = authenticationService.register(registerRequest);
    }


}