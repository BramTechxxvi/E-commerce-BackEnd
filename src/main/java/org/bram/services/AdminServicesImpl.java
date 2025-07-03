package org.bram.services;

import org.bram.configuration.TokenBlacklist;
import org.bram.data.models.User;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicesImpl implements AdminServices{

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final TokenBlacklist tokenBlacklist;

    @Autowired
    public AdminServicesImpl(UserRepository userRepository, CustomerRepository customerRepository, SellerRepository sellerRepository, TokenBlacklist tokenBlacklist) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    public ApiResponse banUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        user.setBanned(true);
        user.setLoggedIn(false);
        userRepository.save(user);

        switch (user.getUserRole()) {
            case CUSTOMER: customerRepository.findById(id).ifPresent(customer-> {
                customer.setLoggedIn(false);
                customer.setBanned(true);
                customerRepository.save(customer);
            });
            case SELLER: sellerRepository.findById(id).ifPresent(seller -> {
                seller.setLoggedIn(false);
                seller.setBanned(true);
                sellerRepository.save(seller);
            });
        } return new ApiResponse("User banned successfully", true);
    }

    @Override
    public ApiResponse unBanUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        user.setBanned(false);
        userRepository.save(user);

        switch (user.getUserRole()) {
            case CUSTOMER: customerRepository.findById(id).ifPresent(customer -> {
                customer.setBanned(false);
                customerRepository.save(customer);
            });
            case SELLER: sellerRepository.findById(id).ifPresent(seller -> {
                seller.setBanned(false);
                sellerRepository.save(seller);
            });
        } return new ApiResponse("User unbanned succesfully", true);
    }
}
