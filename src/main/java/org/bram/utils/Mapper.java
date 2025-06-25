package org.bram.utils;

import org.bram.data.models.*;
import org.bram.dtos.request.*;
import org.bram.dtos.response.LoginResponse;
import org.bram.exceptions.*;

import static org.bram.utils.PasswordUtil.hashPassword;

public class Mapper {

    public static User mapToUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName().trim());
        user.setLastName(registerRequest.getLastName().trim());
        user.setEmail(registerRequest.getEmail().trim().toLowerCase());
        user.setPhone(registerRequest.getPhone().trim());
        user.setPassword(hashPassword(registerRequest.getPassword().trim()));

        Address address = new Address();
        address.setStreet(registerRequest.getStreet());
        address.setCity(registerRequest.getCity());
        address.setState(registerRequest.getState());
        address.setHouseNumber(registerRequest.getHouseNumber());
        address.setCountry(registerRequest.getCountry());

        user.setAddress(address);

        UserRole userRole;
        try {
            userRole = UserRole.valueOf(registerRequest.getUserRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("Invalid role specified" + registerRequest.getUserRole());
        }
        user.setUserRole(userRole);

        return user;
    }


    public static Customer mapToCustomer(RegisterRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName().trim());
        customer.setLastName(request.getLastName().trim());
        customer.setEmail(request.getEmail().trim().toLowerCase());
        customer.setPhone(request.getPhone().trim());
        customer.setPassword(hashPassword(request.getPassword().trim()));

        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setHouseNumber(request.getHouseNumber());
        address.setCountry(request.getCountry());

        customer.setAddress(address);
        customer.setUserRole(UserRole.CUSTOMER);

        return customer;
    }

    public static Seller mapToSeller(RegisterRequest request) {
        Seller seller = new Seller();
        seller.setFirstName(request.getFirstName().trim());
        seller.setLastName(request.getLastName().trim());
        seller.setEmail(request.getEmail().trim().toLowerCase());
        seller.setPhone(request.getPhone().trim());
        seller.setPassword(hashPassword(request.getPassword().trim()));

        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setHouseNumber(request.getHouseNumber());
        address.setCountry(request.getCountry());

        seller.setAddress(address);
        seller.setUserRole(UserRole.SELLER);

        return seller;
    }


    public static LoginResponse mapToLoginResponse(String message, boolean success, String token) {
        return new LoginResponse(message, token, success);
    }
}


