package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerServicesImplTest {

    private SellerRepository sellerRepository;
    private SellerServices sellerServices;

    @B
    void setUp() {
        sellerRepository.deleteAll();
    }

}