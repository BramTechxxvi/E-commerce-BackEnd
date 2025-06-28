package org.bram.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.bram.data.repository.ProductRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.dtos.response.RegisterResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Mock
    private Cloudinary cloudinary;

    private AddProductRequest addProductRequest;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private ApiResponse apiResponse;

    @BeforeEach
    void setUp() throws IOException {
        productRepository.deleteAll();
        sellerRepository.deleteAll();
        addProductRequest = new AddProductRequest();
        registerRequest = new RegisterRequest();
        registerResponse = new RegisterResponse();
        apiResponse = new ApiResponse();

        Uploader uploader = mock(Uploader.class);
        when(cloudinary.uploader()).thenReturn(uploader);

        Map<String,String> uploadResult = new HashMap<>();
        uploadResult.put("secure_url", "http://fake-imageUrl.com");
        when(uploader.upload(any(byte[].class), anyMap())).thenReturn(uploadResult);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void addAProduct__addProductTest() {
        registerASeller();
        assertTrue(registerResponse.isSuccess());

        var authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
        var auth = new UsernamePasswordAuthenticationToken("grace@ayoola.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        addAProduct();
        assertEquals("Product added successfully", apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
    }


//@Test
//public void addAProduct__addProductTest_UnauthorizedUser () {
//    // Set up the security context without the ROLE_SELLER authority
//    var authorities = Collections.emptyList(); // No authorities
//    var auth = new UsernamePasswordAuthenticationToken("grace@ayoola.com", null, authorities);
//    SecurityContextHolder.getContext().setAuthentication(auth);
//    // Attempt to add a product
//    ApiResponse apiResponse = productServices.addProduct(addProductRequest);
//    // Clear the security context after the test
//    SecurityContextHolder.clearContext();
//    assertNotNull(apiResponse);
//    assertFalse(apiResponse.isSuccess());
//    assertEquals("You do not have permission to add products", apiResponse.getMessage(

    private void registerASeller() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542529");
        registerRequest.setUserRole("SELLER");

        registerResponse = authenticationService.register(registerRequest);
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

        apiResponse = productServices.addProduct(addProductRequest);
    }
}