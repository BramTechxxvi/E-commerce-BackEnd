package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.repository.CartRepository;
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
class CartServicesImplTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartServicesImpl cartServices;
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    private AddItemRequest addItemRequest;
    private ApiResponse apiResponse;
    private RegisterRequest sellerRegisterRequest;
    private RegisterResponse sellerRegisterResponse;
    private RegisterRequest customerRegisterRequest;
    private LoginRequest sellerLoginRequest;
    private LoginResponse sellerLoginResponse;
    private LoginRequest customerLoginRequest;

    @BeforeEach
    void setUp() {
    cartRepository.deleteAll();
    addItemRequest = new AddItemRequest();
    apiResponse = new ApiResponse();
    sellerRegisterRequest = new RegisterRequest();
    sellerRegisterResponse = new RegisterResponse();
    sellerLoginRequest = new LoginRequest();
    sellerLoginResponse = new LoginResponse();

    }

    @Test
    public void addAnItemToCart__addItemTest() {
        registerASellerAndLogin();
        assertEquals("Registered successfully", sellerRegisterResponse.getMessage());
        assertTrue(sellerLoginResponse.isSuccess());
    }

    private void registerASellerAndLogin() {
        sellerRegisterRequest.setFirstName("Seyi");
        sellerRegisterRequest.setLastName("Adams");
        sellerRegisterRequest.setEmail("seyi@adams.com");
        sellerRegisterRequest.setPassword("password111");
        sellerRegisterRequest.setPhone("081373542529");
        sellerRegisterRequest.setUserRole("SELLER");
        sellerRegisterResponse = authenticationService.register(sellerRegisterRequest);

        sellerLoginRequest.setEmail("seyi@adams.com");
        sellerLoginRequest.setPassword("password111");
        sellerLoginResponse = authenticationService.login(sellerLoginRequest);
    }
}