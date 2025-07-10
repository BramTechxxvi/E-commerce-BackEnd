package org.bram.services;

import org.bram.configuration.TokenBlacklist;
import org.bram.data.models.*;
import org.bram.data.repository.*;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
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
    private final TokenBlacklist tokenBlacklist;
    private final AdminRepository adminRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, SellerRepository sellerRepository, JwtService jwtService, TokenBlacklist tokenBlacklist, AdminRepository adminRepository) {
       this.userRepository = userRepository;
       this.customerRepository = customerRepository;
       this.sellerRepository = sellerRepository;
       this.jwtService = jwtService;
        this.tokenBlacklist = tokenBlacklist;
        this.adminRepository = adminRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        verifyNewEmail(registerRequest.getEmail().trim().toLowerCase());
        verifyNewPhone(registerRequest.getPhone().trim());

        User user = mapToUser(registerRequest);
        userRepository.save(user);

        switch(user.getUserRole()) {
            case CUSTOMER:
                Customer customer = mapToCustomer(user);
                customerRepository.save(customer); break;

            case SELLER:
                Seller seller = mapToSeller(user);
                sellerRepository.save(seller); break;

            case ADMIN:
                Admin admin = mapToAdmin(user);
                adminRepository.save(admin); break;
        }

        return new RegisterResponse("Registered successfully", true);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail().trim().toLowerCase();
        String password = loginRequest.getPassword().trim();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        if (user.isBanned()) throw new AccessDeniedException("Your account has been banned");
        var userRole = user.getUserRole();
        boolean isCorrectPassword = verifyPassword(password, user.getPassword());
        if (!isCorrectPassword) throw new IncorrectPasswordException("Incorrect password");

        String token = jwtService.generateToken(email, userRole);
        user.setLoggedIn(true);
        userRepository.save(user);

        switch (userRole) {
            case CUSTOMER:
                Customer customer= customerRepository.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("Customer not found"));
                customer.setLoggedIn(true);
                customerRepository.save(customer); break;

            case SELLER:
                Seller seller = sellerRepository.findByEmail(email)
                        .orElseThrow(()-> new UserNotFoundException("Seller not found"));
                seller.setLoggedIn(true);
                sellerRepository.save(seller); break;

            case ADMIN: adminRepository.findByEmail(email).ifPresent(admin -> {
                admin.setLoggedIn(true);
                adminRepository.save(admin);
            });
        }

        String fullName = user.getFirstName() +" " + user.getLastName();

        return new LoginResponse("Welcome back " + fullName, token, true);
    }

    @Override
    public LogoutResponse logout(String token) {
        String email = jwtService.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setLoggedIn(false);
        userRepository.save(user);
        switch (user.getUserRole()) {
            case CUSTOMER: customerRepository.findByEmail(email).ifPresent(customer -> {
                customer.setLoggedIn(false);
                customerRepository.save(customer);
            });
            case SELLER: sellerRepository.findByEmail(email).ifPresent(seller -> {
                seller.setLoggedIn(false);
                sellerRepository.save(seller);
            });
            case ADMIN: adminRepository.findByEmail(email).ifPresent(admin -> {
                admin.setLoggedIn(false);
                adminRepository.save(admin);
            });
        }
        tokenBlacklist.blackListToken(token);
        return new LogoutResponse("We hope to see you soon...", null, true);
    }

    private void verifyNewEmail(String email) {
        if (userRepository.existsByEmail(email)) throw new DetailsAlreadyInUseException("Email already exists");
    }

    private void verifyNewPhone(String phone) {
        if(userRepository.existsByPhone(phone)) throw new DetailsAlreadyInUseException("Phone already exists");
    }
}
