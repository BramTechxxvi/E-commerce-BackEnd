package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.repository.CartRepository;
import org.bram.dtos.request.AddItemRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.ApiResponse;
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
    private AddItemRequest addItemRequest;
    private ApiResponse apiResponse;
    private RegisterRequest sellerRegisterRequest;
    private RegisterRequest customerRegisterRequest;

    @BeforeEach
    void setUp() {
    cartRepository.deleteAll();
    addItemRequest = new AddItemRequest();
    apiResponse = new ApiResponse();

    }

    @Test
    public void addAnItemToCart__addItemTest() {

    }

    private

}