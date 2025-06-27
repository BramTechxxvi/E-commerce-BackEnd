package org.bram.services;

import com.cloudinary.Cloudinary;
import org.bram.data.repository.ProductRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    private Cloudinary cloudinary;
    private AddProductRequest addProductRequest;
    private ApiResponse apiResponse;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        sellerRepository.deleteAll();
        addProductRequest = new AddProductRequest();
        apiResponse = new ApiResponse();
    }

    @Test
    public void addAProduct__addProductTest() {


        Map<?,?> uploadResult = new HashMap<>();
        uploadResult.put("secure_url", "http://image.url");
        when(uploader().upload)
        apiResponse = productServices.addProduct(addProductRequest);
        assertNotNull(apiResponse);
        assertTrue(apiResponse.isSuccess());
        assertEquals("Product added successfully", apiResponse.getMessage());
  }

    private void addAProduct() {
        MockMultipartFile imageFile = new MockMultipartFile(
        "image", "image.jpg", "images/jpeg", "imageContent".getBytes());
        addProductRequest.setProductName("Headphones");
        addProductRequest.setDescription("Immerse yourself in crystal-clear audio with our premium headphones");
        addProductRequest.setPrice(100.00);
        addProductRequest.setProductCategory("Gadgets");
        addProductRequest.setProductQuantity(30);
        addProductRequest.setImage(imageFile);
    }

}