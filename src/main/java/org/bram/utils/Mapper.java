package org.bram.utils;

import org.bram.data.models.*;
import org.bram.dtos.request.*;
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

    public static Customer mapToCustomer(User user) {
        Customer customer = new Customer();
        customer.setId(user.getId());
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setEmail(user.getEmail());
        customer.setPhone(user.getPhone());
        customer.setPassword(user.getPassword());
        customer.setUserRole(user.getUserRole());
        customer.setAddress(user.getAddress());

        return customer;
    }

    public static Seller mapToSeller(User user) {
        Seller seller = new Seller();
        seller.setId(user.getId());
        seller.setFirstName(user.getFirstName());
        seller.setLastName(user.getLastName());
        seller.setEmail(user.getEmail());
        seller.setPhone(user.getPhone());
        seller.setPassword(user.getPassword());
        seller.setUserRole(user.getUserRole());
        seller.setAddress(user.getAddress());

        return seller;
    }
}


