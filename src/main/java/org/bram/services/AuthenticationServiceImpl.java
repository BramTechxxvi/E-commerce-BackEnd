package org.bram.services;

import org.bram.data.models.*;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.LoginRequest;
import org.bram.dtos.request.RegisterRequest;
import org.bram.dtos.response.LoginResponse;
import org.bram.dtos.response.RegisterResponse;
import org.bram.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        verifyNewEmail(registerRequest.getEmail().trim().toLowerCase());
        verifyNewPhone(registerRequest.getPhone().trim());

        UserRole userRole;
        try {
            userRole = UserRole.valueOf(registerRequest.getUserRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("Invalid role specified" + registerRequest.getUserRole());
        }

        User user = mapToUser(registerRequest);
        userRepository.save(user);

        switch(userRole) {
            case CUSTOMER:
                Customer customer = mapToCustomer(user);
                customerRepository.save(customer); break;

            case SELLER:
                Seller seller = mapToSeller(user);
                sellerRepository.save(seller); break;
        }

        RegisterResponse response = new RegisterResponse();
        response.setSuccess(true);
        response.setMessage("Registered successfully");
        return response;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail().trim().toLowerCase();
        String password = loginRequest.getPassword().trim();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        var userRole = user.getUserRole();
        boolean isCorrectPassword = verifyPassword(password, user.getPassword());
        if (!isCorrectPassword) throw new IncorrectPasswordException("Incorrect password");

        String token = jwtService.generateToken(email, userRole);
        user.setLoggedIn(true);
        userRepository.save(user);
//        switch (userRole) {
//            case CUSTOMER:
//
//                }
//
//        }
        String fullName = user.getFirstName() +" " + user.getLastName();

        LoginResponse response = new LoginResponse();
        response.setSuccess(true);
        response.setToken(token);
        response.setMessage("Welcome back " + fullName);

        return response;
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email already exists");
    }

    private void verifyNewPhone(String phone) {
        if(userRepository.existsByPhone(phone)) throw new DetailsAlreadyInUseException("Phone already exists");
    }
}
