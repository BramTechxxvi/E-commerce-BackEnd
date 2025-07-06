package org.bram.services;

import org.bram.TestConfig.CloudinaryTestConfig;
import org.bram.data.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(CloudinaryTestConfig.class)
class CartServicesImplTest {

    private CartRepository cartRepository;
    private CartServicesImpl cartServices;

    @BeforeEach() {

    }

    @Test
    public void addAnItemToCart__addItemTest() {

    }

}