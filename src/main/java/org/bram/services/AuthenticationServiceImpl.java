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
import org.bram.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.bram.utils.Mapper.*;
import static org.bram.utils.PasswordUtil.verifyPassword;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, SellerRepository sellerRepository, JwtService jwtService) {
       this.userRepository = userRepository;
       this.customerRepository = customerRepository;
       this.sellerRepository = sellerRepository;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        verifyNewEmail(registerRequest.getEmail());
        verifyNewPhone(registerRequest.getPhone());

        var user = mapToUser(registerRequest);
        var role = user.getUserRole();

        switch(role) {
            case CUSTOMER:
                var customer = mapToCustomer(user);
                customerRepository.save(customer); break;

            case SELLER:
                var seller = mapToSeller(user);
                sellerRepository.save(seller); break;
        }

        RegisterResponse response = new RegisterResponse();
        response.setSuccess(true);
        response.setMessage("Registered successfully");
        return response;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);
        boolean isCorrectPassword = verifyPassword(password, foundCustomer.get().getPassword());

        if (isCorrectPassword) {
        String token = jwtService.generateToken(email, UserRole.CUSTOMER);
            return mapToLoginResponse("Login Successful", true, token);
        }

        Optional<Seller> foundSeller = sellerRepository.findByEmail(email);
        if (foundSeller.isPresent() && foundSeller.get().getPassword().equals(password)) {
            String token = jwtService.generateToken(email, UserRole.SELLER);
            return mapToLoginResponse("Login Successful", true, token);
        }

        return mapToLoginResponse("No user found", false, null);
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email already exists");
    }

    private void verifyNewPhone(String phone) {
        if(userRepository.existsByPhone(phone)) throw new DetailsAlreadyInUseException("Phone already exists");
    }
}
