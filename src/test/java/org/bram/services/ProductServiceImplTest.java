package org.bram.services;

import org.bram.data.repository.ProductRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    private ProductServices productServices;
    private ProductRepository productRepository;
    private AddProductRequest addProductRequest;
    private ApiResponse apiResponse;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        addProductRequest = new AddProductRequest();
        apiResponse = new ApiResponse();
    }

    @Test
    public void addAProduct__addProductTest() {

    }

}