package org.bram.services;

import io.jsonwebtoken.lang.Classes;
import org.bram.Main;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Main.class})
public class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private LoginRequest loginRequest;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @BeforeEach
    public void setUp(){
        registerRequest = new RegisterRequest();
        customerRepository.deleteAll();
        loginRequest = new LoginRequest();
        userRepository.deleteAll();
        sellerRepository.deleteAll();
    }

    @Test
    public void registerACustomer__registerTest() {
        registerCustomer();
        assertNotNull(registerResponse);
        assertTrue(registerResponse.isSuccess());
        assertEquals(1, customerRepository.count());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void registerASeller__registerTest() {
        registerSeller();
        assertNotNull(registerResponse);
        assertTrue(registerResponse.isSuccess());
        assertEquals(1, sellerRepository.count());
        assertEquals(1, userRepository.count());
    }

    @Test
    public void loginCustomer__loginTest() {
        registerACustomer__registerTest();
        loginRequest.setEmail("John@doe.com");
        loginRequest.setPassword("password111");
        loginRequest.setRole("CUSTOMER");
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertEquals("Welcome back John Doe", loginResponse.getMessage());
    }

    @Test
    public void loginSeller__loginTest() {
        registerASeller__registerTest();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginRequest.setRole("SELLER");
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());
        assertTrue(loginResponse.isSuccess());
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

    private void registerSeller() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542529");
        registerRequest.setUserRole("SELLER");
        registerRequest.setHouseNumber("321");
        registerRequest.setStreet("Bornu Way");
        registerRequest.setCity("Lagos");
        registerRequest.setState("Lagos State");
        registerRequest.setCountry("Nigeria");
        registerResponse = authenticationService.register(registerRequest);
    }


}