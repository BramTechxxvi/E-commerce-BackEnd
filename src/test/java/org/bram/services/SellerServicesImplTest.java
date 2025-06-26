package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.bram.dtos.response.ChangePasswordResponse;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterResponse;
import org.bram.exceptions.IncorrectOldEmailException;
import org.bram.exceptions.SameEmailException;
import org.bram.exceptions.SamePasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


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
    private LoginResponse loginResponse;
    private ChangePasswordRequest changePasswordRequest;
    private ChangePasswordResponse changePasswordResponse;


    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAll();
        registerResponse = new RegisterResponse();
        registerRequest = new RegisterRequest();
        loginRequest = new LoginRequest();
        changeEmailRequest = new ChangeEmailRequest();
        userRepository.deleteAll();
        loginResponse = new LoginResponse();
        changePasswordResponse = new ChangePasswordResponse();
        changePasswordRequest = new ChangePasswordRequest();
    }

    @Test
    public void ChangeSellerEmail__changeEmailTest() {
        registerSeller();
        assertEquals("Registered successfully", registerResponse.getMessage());
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());

        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        changeEmailRequest.setOldEmail("grace@ayoola.com");
        changeEmailRequest.setNewEmail("grace@gmail.com");
        ChangeEmailResponse response = sellerService.changeEmail(changeEmailRequest);
        assertEquals("Email changed successfully", response.getMessage());
        assertTrue(response.isSuccess());
    }

    @Test
    public void changeSellerEmailWithSameOldEmail__throwsException() {
        registerSeller();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);
        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        changeEmailRequest.setOldEmail("grace@ayoola.com");
        changeEmailRequest.setNewEmail("grace@ayoola.com");
        Exception error = assertThrows(SameEmailException.class,()-> sellerService.changeEmail(changeEmailRequest));
        assertEquals("New email cannot be same as old email", error.getMessage());
    }

    @Test
    public void changeSellerEmailWithWrongOldEmail__throwsException() {
        registerSeller();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);
        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        changeEmailRequest.setOldEmail("grace@gmail.com");
        changeEmailRequest.setNewEmail("grace@ayoola.com");
        Exception error = assertThrows(IncorrectOldEmailException.class,()-> sellerService.changeEmail(changeEmailRequest));
        assertEquals("Old email not correct", error.getMessage());
    }

    @Test
    public void changeSellerPassword__changePassword() {
        registerSeller();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());

        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        changePasswordRequest.setOldPassword("password111");
        changePasswordRequest.setNewPassword("grace111");
        changePasswordResponse = sellerService.changePassword(changePasswordRequest);
        assertEquals("Password changed successfully", changePasswordResponse.getMessage());
        assertTrue(changePasswordResponse.isSuccess());
    }

    @Test
    public void changeSellerPasswordWithSameOldPassword__throwsException() {
        registerSeller();
        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);

        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        changePasswordRequest.setOldPassword("password111");
        changePasswordRequest.setNewPassword("password111");
        Exception error = assertThrows(SamePasswordException.class, ()-> sellerService.changePassword(changePasswordRequest));
        assertEquals("New password cannot be the same as old password", error.getMessage());
    }

//    @Test
//    public void changeSellerPasswordWithWrongOldPassword__throwsException() {
//
//    }
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