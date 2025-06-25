package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SellerServicesImplTest {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerServicesImpl sellerService;

    @Autowired
    private AuthenticationService authenticationService;
    private RegisterResponse registerResponse;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private ChangeEmailRequest changeEmailRequest;


    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAll();
        registerResponse = new RegisterResponse();
        registerRequest = new RegisterRequest();
        loginRequest = new LoginRequest();
        changeEmailRequest = new ChangeEmailRequest();
        userRepository.deleteAll();
    }

    @Test
    public void ChangeSellerEmail__changeEmailTest() {
        registerSeller();
        assertEquals("Registered successfully", registerResponse.getMessage());

        loginRequest.setRole("Customer");
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());

        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, List.of(new SimpleGrantedAuthority("SELLER")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        changeEmailRequest.setOldEmail("grace@ayoola.com");
        changeEmailRequest.setNewEmail("grace@gmail.com");
        ChangeEmailResponse response = sellerService.changeEmail(changeEmailRequest);
        assertEquals("Email changed successfully", response.getMessage());
        assertTrue(response.isSuccess());
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