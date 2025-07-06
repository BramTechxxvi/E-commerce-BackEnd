package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.models.Product;
import org.bram.data.models.Seller;
import org.bram.data.repository.*;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.junit.jupiter.api.AfterEach;
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

@SpringBootTest()
@ActiveProfiles("test")
@Import(CloudinaryTestConfig.class)
class ProductServiceImplTest {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;

    private AddProductRequest addProductRequest;
    private RegisterRequest registerRequest;
    private RegisterResponse registerResponse;
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;
    private UpdateProductRequest updateRequest;
    private ApiResponse apiResponse;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        sellerRepository.deleteAll();
        userRepository.deleteAll();
        addProductRequest = new AddProductRequest();
        registerRequest = new RegisterRequest();
        registerResponse = new RegisterResponse();
        loginRequest = new LoginRequest();
        loginResponse = new LoginResponse();
        updateRequest = new UpdateProductRequest();
        apiResponse = new ApiResponse();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void sellerCanAddAProduct__addProductTest() {
        registerASellerAndLogin();
        assertTrue(registerResponse.isSuccess());
        assertTrue(loginResponse.isSuccess());

        var authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
        var auth = new UsernamePasswordAuthenticationToken("seyi@adams.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        addAProduct();
        assertEquals("Product added successfully", apiResponse.getMessage());
        assertTrue(apiResponse.isSuccess());
        assertEquals(1, productRepository.count());
    }

    @Test
    public void testIfUnAuthorizedUserCanAddProduct__throwException() {
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("CUSTOMER"));
        var auth = new UsernamePasswordAuthenticationToken("grace@ayoola.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Exception error  = assertThrows(AccessDeniedException.class, this::addAProduct);
        assertFalse(apiResponse.isSuccess());
        assertEquals("You are not allowed to add products", error.getMessage());
    }

    @Test
    public void sellerCanDeleteProduct__removeProductTest() {
        registerASellerAndLogin();
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
        var auth = new UsernamePasswordAuthenticationToken("seyi@adams.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        addAProduct();
        Seller seller = sellerRepository.findByEmail("seyi@adams.com")
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));
        Product savedProduct = productRepository.findByProductNameAndSeller("Headphones", seller)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        apiResponse = productServices.removeProduct(savedProduct.getProductId());
        assertTrue(apiResponse.isSuccess());
        assertEquals("Product removed successfully", apiResponse.getMessage());
        assertEquals(0, productRepository.count());
    }

    @Test
    public void sellerCanUpdateProduct__updateProductTest() {
        registerASellerAndLogin();
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
        var auth = new UsernamePasswordAuthenticationToken("seyi@adams.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        addAProduct();
        Seller seller = sellerRepository.findByEmail("seyi@adams.com")
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));
        Product savedProduct = productRepository.findByProductNameAndSeller("Headphones", seller)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));

        updateRequest.setProductName("Headset");
        updateRequest.setDescription("ANC");
        updateRequest.setPrice(30_000);
        updateRequest.setProductQuantity(50);
        updateRequest.setCategory("Electronics");

        byte[] imageBytes;
        try(var inputStream = getClass().getClassLoader().getResourceAsStream("image.jpg")) {
            if (inputStream == null) throw new NullPointerException("Image not found");
            imageBytes = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Could not read image file");
        }
        MockMultipartFile imageFile = new MockMultipartFile(
                "image", "image.jpg", "image/jpeg", imageBytes);
        updateRequest.setImage(imageFile);
        apiResponse = productServices.updateProduct(savedProduct.getProductId(), updateRequest);
        assertTrue(apiResponse.isSuccess());
        assertEquals("Product updated successfully", apiResponse.getMessage());
    }

    @Test
    public void getAlProductsTest() {
        registerASellerAndLogin();
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("SELLER"));
        var auth = new UsernamePasswordAuthenticationToken("seyi@adams.com", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        addAProduct();
        var allProducts = productServices.getAllProducts();
        boolean containsHeadphone = allProducts.stream()
                .anyMatch(product -> "Headphones".equals(product.getProductName()));
        assertEquals(product)
    }

    private void registerASellerAndLogin() {
        registerRequest.setFirstName("Seyi");
        registerRequest.setLastName("Adams");
        registerRequest.setEmail("seyi@adams.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("081373542529");
        registerRequest.setUserRole("SELLER");
        registerResponse = authenticationService.register(registerRequest);

        loginRequest.setEmail("seyi@adams.com");
        loginRequest.setPassword("password111");
        loginResponse = authenticationService.login(loginRequest);
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
}