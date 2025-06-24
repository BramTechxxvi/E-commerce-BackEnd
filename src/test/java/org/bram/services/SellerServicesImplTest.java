package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SellerServicesImplTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerServicesImpl sellerService;


    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAll();
    }

    @Test
    public void changeEmail_success() {
        ChangeEmailRequest request = new ChangeEmailRequest();
        request.setOldEmail("old@email.com");
        request.setNewEmail("new@email.com");
        ChangeEmailResponse response = sellerService.changeEmail(request);
        System.out.println(response);
    }



}