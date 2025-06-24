package org.bram.services;

import org.bram.data.models.*;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterResponse;
import org.bram.exceptions.DetailsAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.bram.utils.Mapper.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private SellerRepository sellerRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, SellerRepository sellerRepository) {
       this.userRepository = userRepository;
       this.customerRepository = customerRepository;
       this.sellerRepository = sellerRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        verifyNewEmail(registerRequest.getEmail());
        verifyNewPhone(registerRequest.getPhone());

        var user = mapToUser(registerRequest);

        switch(user.getUserRole()) {
            case CUSTOMER:
                var customer = mapToCustomer(user);

                customerRepository.save(customer);
        }


    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email already exists");
    }

    private void verifyNewPhone(String phone) {
        if(userRepository.existsByPhone(phone)) throw new DetailsAlreadyInUseException("Phone already exists");
    }
}
