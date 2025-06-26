package org.bram.services;

import org.bram.data.repository.ProductRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
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
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "hello".getBytes());
    }
//
//        @Test
//        void addProduct_successful() throws Exception {
//            AddProductRequest request = new AddProductRequest();
//            request.setProductName("Test Product");
//            request.setDescription("Test Desc");
//            request.setProductQuantity(5);
//            request.setPrice(100.0);
//            request.setProductCategory("ELECTRONICS");
//            request.setImage(new MockMultipartFile("image", "image.jpg", "image/jpeg", "dummy".getBytes()));
//
//            Map<String, String> uploadResult = new HashMap<>();
//            uploadResult.put("secure_url", "http://image.url");
//
//            Seller seller = new Seller();
//            seller.setEmail("test@example.com");
//
//            when(cloudinary.uploader().upload(any(byte[].class), eq(ObjectUtils.emptyMap()))).thenReturn(uploadResult);
//            when(sellerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(seller));
//            when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);
//
//            ApiResponse response = productService.addProduct(request);
//            assertTrue(response.isSuccess());
//            assertEquals("Product created successfully", response.getMessage());
//        }
//@Test
//void createProduct_success_savesProductAndSeller() throws Exception {
//    // Setup fake image file
//    MockMultipartFile imageFile = new MockMultipartFile(
//            "image", "product.jpg", "image/jpeg", "fake image content".getBytes());
//
//    // Create request
//    CreateProductRequest request = new CreateProductRequest();
//    request.setProductName("Test Product");
//    request.setDescription("Test Description");
//    request.setPrice(100.0f);
//    request.setProductCategory("ELECTRONICS");
//    request.setProductQuantity(5);
//    request.setImage(imageFile);
//
//    // Mock Cloudinary upload
//    Map<String, Object> cloudinaryResult = new HashMap<>();
//    cloudinaryResult.put("secure_url", "https://cloudinary.com/fake-image.jpg");
//    Uploader uploader = mock(Uploader.class);
//    when(cloudinary.uploader()).thenReturn(uploader);
//    when(uploader.upload(any(byte[].class), anyMap())).thenReturn(cloudinaryResult);
//
//    // Mock SecurityContext to simulate logged-in seller
//    SecurityContext securityContext = mock(SecurityContext.class);
//    Authentication authentication = mock(Authentication.class);
//    when(authentication.getName()).thenReturn("seller@example.com");
//    when(securityContext.getAuthentication()).thenReturn(authentication);
//    SecurityContextHolder.setContext(securityContext);
//
//    // Mock seller lookup
//    Seller seller = new Seller();
//    seller.setEmail("seller@example.com");
//    seller.setProducts(new ArrayList<>());
//    when(sellerRepository.findByEmail("seller@example.com")).thenReturn(Optional.of(seller));


}