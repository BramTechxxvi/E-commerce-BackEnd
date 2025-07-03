package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.repository.AdminRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(CloudinaryTestConfig.class)
class AdminServicesImplTest {

    @Autowired
    private AdminServicesImpl adminServices;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private LoginRequest adminLoginRequest;
    private LoginResponse adminLoginResponse;
    private LoginRequest sellerLoginRequest;
    private LoginResponse sellerLoginResponse;
    private ApiResponse apiResponse;


    @BeforeEach
    void setUp() {
        adminRepository.deleteAll();
        userRepository.deleteAll();
        registerRequest = new RegisterRequest();
        adminLoginRequest = new LoginRequest();
        adminLoginResponse = new LoginResponse();
        sellerLoginRequest = new LoginRequest();
        sellerLoginResponse = new LoginResponse();
        apiResponse = new ApiResponse();
    }

    @Test
    public void adminCanBanAUser__banUserTest() {
        registerSeller();
        registerAdmin();
        adminLoginRequest.setEmail("wisdom@gmail.com");
        adminLoginRequest.setPassword("password111");
        adminLoginResponse = authenticationService.login(adminLoginRequest);
        assertTrue(adminLoginResponse.isSuccess());
        apiResponse = adminServices.banUser()


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

    private void registerAdmin() {
        registerRequest.setFirstName("Wisdom");
        registerRequest.setLastName("Babalola");
        registerRequest.setEmail("wisdom@gmail.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("09037354211");
        registerRequest.setUserRole("ADMIN");

        registerResponse = authenticationService.register(registerRequest);
    }
}