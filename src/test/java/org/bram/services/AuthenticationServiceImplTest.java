package org.bram.services;

import org.bram.Main;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.LogoutResponse;
import org.bram.dtos.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
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
        registerResponse = new RegisterResponse();
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
        registerCustomer();
        loginRequest.setEmail("John@doe.com");
        loginRequest.setPassword("password111");
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertNotNull(loginResponse.getToken());
        assertEquals("Welcome back John Doe", loginResponse.getMessage());
    }

    @Test
    public void loginSeller__loginTest() {
        registerSeller();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertNotNull(loginResponse.getToken());
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());
        assertTrue(loginResponse.isSuccess());
    }

    @Test
    public void logoutSeller__logoutTest() {
        registerSeller();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertTrue(loginResponse.isSuccess());
        String token = loginResponse.getToken();
        LogoutResponse logoutResponse = authenticationService.logout(token);
        assertTrue(logoutResponse.isSuccess());
        assertEquals("We hope to see you soon...", logoutResponse.getMessage());
    }

    private void registerCustomer() {
        registerRequest.setFirstName("John");
        registerRequest.setLastName("Doe");
        registerRequest.setEmail("john@doe.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542523");
        registerRequest.setUserRole("CUSTOMER");

        registerResponse = authenticationService.register(registerRequest);
    }

    private void registerSeller() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542529");
        registerRequest.setUserRole("SELLER");

        registerResponse = authenticationService.register(registerRequest);
    }


}