package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.models.Product;
import org.bram.data.repository.CartRepository;
import org.bram.data.repository.ProductRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.ProductNotFoundException;
import org.bram.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Collections;

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
    private AddItemToCartRequest addItemRequest;
    private ApiResponse apiResponse;
    private RegisterRequest sellerRegisterRequest;
    private RegisterResponse sellerRegisterResponse;
    private RegisterRequest customerRegisterRequest;
    private RegisterResponse customerRegisterResponse;
    private LoginRequest sellerLoginRequest;
    private LoginResponse sellerLoginResponse;
    private LoginRequest customerLoginRequest;
    private LoginResponse customerLoginResponse;
    private AddProductRequest addProductRequest;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductServicesImpl productServices;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    cartRepository.deleteAll();
    userRepository.deleteAll();
    sellerRepository.deleteAll();
    addItemRequest = new AddItemToCartRequest();
    addProductRequest = new AddProductRequest();
    apiResponse = new ApiResponse();
    sellerRegisterRequest = new RegisterRequest();
    sellerRegisterResponse = new RegisterResponse();
    sellerLoginRequest = new LoginRequest();
    sellerLoginResponse = new LoginResponse();
    customerRegisterRequest = new RegisterRequest();
    customerRegisterResponse = new RegisterResponse();
    customerLoginRequest = new LoginRequest();
    customerLoginResponse = new LoginResponse();
    }

    @Test
    public void addAnItemToCart__addItemToCartTest() {
        registerASellerAndLogin();
        assertEquals("Registered successfully", sellerRegisterResponse.getMessage());
        assertTrue(sellerLoginResponse.isSuccess());
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
        var auth = new UsernamePasswordAuthenticationToken("Seyi@adams.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        var seller = sellerRepository.findByEmail("seyi@ADAMS.COM".toLowerCase())
                        .orElseThrow(()-> new UserNotFoundException("User not found"));
        addAProduct();
        assertEquals("Product added successfully", apiResponse.getMessage());
        Product savedProduct = productRepository.findByProductNameAndSeller("Headphones", seller)
                        .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        registerACustomerAndLogin();
        assertEquals("Registered successfully", customerRegisterResponse.getMessage());
        assertTrue(customerLoginResponse.isSuccess());

        var authority = Collections.singletonList(new SimpleGrantedAuthority("CUSTOMER"));
        var authen = new UsernamePasswordAuthenticationToken("grace@adams.com", null, authority);
        SecurityContextHolder.getContext().setAuthentication(authen);

        addItemRequest.setProductId(savedProduct.getProductId());
        addItemRequest.setQuantity(2);
        apiResponse = cartServices.addItemToCart(addItemRequest);
        assertEquals("Added", apiResponse.getMessage());
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

    private void addAProduct() {
        byte[] imageBytes;
        try(var inputStream = getClass().getClassLoader().getResourceAsStream("image.jpg")) {
            if (inputStream == null) throw new NullPointerException("Image not found");
            imageBytes = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Could not read image file");
        }
        MockMultipartFile imageFile = new MockMultipartFile(
                "image", "image.jpg", "image/jpeg", imageBytes);
        addProductRequest.setProductName("Headphones");
        addProductRequest.setDescription("Immerse yourself in crystal-clear audio with our premium headphones");
        addProductRequest.setPrice(100.00);
        addProductRequest.setProductCategory("Gadgets");
        addProductRequest.setProductQuantity(30);
        addProductRequest.setImage(imageFile);

        apiResponse = productServices.addProduct(addProductRequest);
    }

    private void registerACustomerAndLogin() {
        customerRegisterRequest.setFirstName("Grace");
        customerRegisterRequest.setLastName("Adams");
        customerRegisterRequest.setEmail("grace@adams.com");
        customerRegisterRequest.setPassword("password111");
        customerRegisterRequest.setPhone("08137352352");
        customerRegisterRequest.setUserRole("CUSTOMER");
        customerRegisterResponse = authenticationService.register(customerRegisterRequest);

        customerLoginRequest.setEmail("seyi@adams.com");
        customerLoginRequest.setPassword("password111");
        customerLoginResponse = authenticationService.login(customerLoginRequest);
    }
}