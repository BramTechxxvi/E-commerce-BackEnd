package org.bram.services;

import org.bram.data.models.Customer;
import org.bram.data.models.Seller;
import org.bram.data.repository.CustomerRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.UpdateCustomerProfileRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static org.bram.utils.ProfileUpdateMapper.*;

@Service
public class CustomerServicesImpl implements UserServices, CustomerServices {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServicesImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ApiResponse changeEmail(ChangeEmailRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Customer not found"));

        Customer updatedCustomer = changeEmailMapper(customer, request);
        customerRepository.save(updatedCustomer);

        return new ApiResponse("Email changed successfully", true);
    }

    @Override
    public ApiResponse changePassword(ChangePasswordRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Customer not found"));

        Customer updatedCustomer = changePasswordMapper(customer, request);
        customerRepository.save(updatedCustomer);
        return new ApiResponse("Password changed successfully", true);
    }

    @Override
    public ApiResponse updateProfile(UpdateCustomerProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Seller not found"));

        Seller updatedCustomer = updateProfileMapper(customer, request);
        customerRepository.save(updatedCustomer);
        return new ApiResponse("Profile updated successfully", true);
    }
}
