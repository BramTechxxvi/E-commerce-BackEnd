package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.bram.dtos.response.RegisterResponse;
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

    @Autowired
    private AuthenticationService authenticationService;
    private RegisterResponse registerResponse;
    private RegisterRequest registerRequest;

    private ChangeEmailResponse changeEmailResponse;


    @BeforeEach
    public void setUp() {
        sellerRepository.deleteAll();
    }

    @Test
    public void ChangeSellerEmail__changeEmailTest() {
        registerSeller();
        ChangeEmailRequest request = new ChangeEmailRequest();
        request.setOldEmail("old@email.com");
        request.setNewEmail("new@email.com");
        ChangeEmailResponse response = sellerService.changeEmail(request);
        System.out.println(response);
    }

    private void registerSeller() {
        registerRequest.setFirstName("Grace");
        registerRequest.setLastName("Ayoola");
        registerRequest.setEmail("grace@ayoola.com");
        registerRequest.setPassword("password111");
        registerRequest.setPhone("090373542529");
        registerRequest.setUserRole("SELLER");
        registerRequest.setHouseNumber("321");
        registerRequest.setStreet("Bornu Way");
        registerRequest.setCity("Lagos");
        registerRequest.setState("Lagos State");
        registerRequest.setCountry("Nigeria");
        registerResponse = authenticationService.register(registerRequest);
    }



}