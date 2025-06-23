package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerServicesImplTest {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SellerServices sellerServices;

    @BeforeEach
    void setUp() {
        sellerRepository.deleteAll();
    }

    @Test
    public void

}