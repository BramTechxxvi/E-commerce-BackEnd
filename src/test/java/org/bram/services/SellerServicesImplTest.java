package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.IncorrectOldEmailException;
import org.bram.exceptions.IncorrectOldPasswordException;
import org.bram.exceptions.SameEmailException;
import org.bram.exceptions.SamePasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(CloudinaryTestConfig.class)
public class SellerServicesImplTest {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private ChangeEmailRequest changeEmailRequest;
    private ChangePasswordRequest changePasswordRequest;
    private ApiResponse apiResponse;
    private UpdateSellerProfileRequest updateRequest;
    @Autowired
    private SellerServicesImpl sellerServices;


    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAll();
        registerRequest = new RegisterRequest();
        registerResponse = new RegisterResponse();
        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();
        changeEmailRequest = new ChangeEmailRequest();
        userRepository.deleteAll();
        apiResponse = new ApiResponse();
        changePasswordRequest = new ChangePasswordRequest();
        apiResponse = new ApiResponse();
        updateRequest = new UpdateSellerProfileRequest();
    }

    @Test
    public void ChangeSellerEmail__changeEmailTest() {
        registerSellerAndLogin();
        assertEquals("Registered successfully", registerResponse.getMessage());
        assertEquals("Welcome back Grace Ayoola", loginResponse.getMessage());
        changeEmailRequest.setOldEmail("grace@ayoola.com");
        changeEmailRequest.setNewEmail("grace@gmail.com");
        apiResponse = sellerServices.changeEmail(changeEmailRequest);
        assertEquals("Email changed successfully", apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
    }

    @Test
    public void changeSellerEmailWithSameOldEmail__throwsException() {
        registerSellerAndLogin();
        changeEmailRequest.setOldEmail("grace@ayoola.com");
        changeEmailRequest.setNewEmail("grace@ayoola.com");
        Exception error = assertThrows(SameEmailException.class,()-> sellerServices.changeEmail(changeEmailRequest));
        assertEquals("New email cannot be same as old email", error.getMessage());
    }

    @Test
    public void changeSellerEmailWithWrongOldEmail__throwsException() {
        registerSellerAndLogin();
        changeEmailRequest.setOldEmail("grace@gmail.com");
        changeEmailRequest.setNewEmail("grace@ayoola.com");
        Exception error = assertThrows(IncorrectOldEmailException.class,()-> sellerServices.changeEmail(changeEmailRequest));
        assertEquals("Old email not correct", error.getMessage());
    }

    @Test
    public void changeSellerPassword__changePassword() {
        registerSellerAndLogin();
        changePasswordRequest.setOldPassword("password111");
        changePasswordRequest.setNewPassword("grace111");
        apiResponse = sellerServices.changePassword(changePasswordRequest);
        assertEquals("Password changed successfully", apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
    }

    @Test
    public void changeSellerPasswordWithSameOldPassword__throwsException() {
        registerSellerAndLogin();
        changePasswordRequest.setOldPassword("password111");
        changePasswordRequest.setNewPassword("password111");
        Exception error = assertThrows(SamePasswordException.class, ()-> sellerServices.changePassword(changePasswordRequest));
        assertEquals("New password cannot be the same as old password", error.getMessage());
    }

    @Test
    public void changeSellerPasswordWithWrongOldPassword__throwsException() {
        registerSellerAndLogin();
        changePasswordRequest.setOldPassword("pass111");
        changePasswordRequest.setNewPassword("grace111");
        Exception error = assertThrows(IncorrectOldPasswordException.class, ()-> sellerServices.changePassword(changePasswordRequest));
        assertEquals("Old password not correct", error.getMessage());
    }

    @Test
    public void updateSellerInformation__updateSellerProfileTest() {
        registerSellerAndLogin();
        updateRequest.setStoreName("Grace Kiddies Store");
        updateRequest.setStoreDescription("We sell all kinds of children clothes, shoes, toys and comic books");
        apiResponse = sellerServices.updateProfile(updateRequest);
        assertEquals("Profile updated successfully", apiResponse.getMessage());
    }

    @Test
    public void updateSellerAddress__updateProfileTest() {
        registerSellerAndLogin();
        updateRequest.setHouseNumber("12");
        updateRequest.setStreet("Main Street");
        updateRequest.setCity("Lagos city");
        updateRequest.setState("Lagos state");
        updateRequest.setCountry("Nigeria");
        apiResponse = sellerServices.updateProfile(updateRequest);
        assertEquals("Profile updated successfully", apiResponse.getMessage());
    }

    private void registerSellerAndLogin() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542529");
        registerRequest.setUserRole("SELLER");
        registerResponse = authenticationService.register(registerRequest);

        loginRequest.setEmail("grace@ayoola.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);

        var auth = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }



}