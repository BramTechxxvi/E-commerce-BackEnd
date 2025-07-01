package org.bram.services;

import lombok.AllArgsConstructor;
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

import static org.bram.utils.ProfileUpdateMapper.changeEmailMapper;
import static org.bram.utils.ProfileUpdateMapper.changePasswordMapper;

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
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Customer updatedCustomer = changeEmailMapper(customer, request);
        customerRepository.save(updatedCustomer);

        return new ApiResponse("Email changed successfully", true);
    }

    @Override
    public ApiResponse changePassword(ChangePasswordRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = changePasswordMapper(seller, request);
        sellerRepository.save(updatedSeller);

        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Password changed successfully");
        return response;
    }

    @Override
    public ApiResponse updateProfile(UpdateCustomerProfileRequest request) {
        return null;
    }
}
