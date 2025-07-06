package org.bram.services;

import org.bram.data.models.Seller;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static org.bram.utils.ProfileUpdateMapper.*;

@Service
public class SellerServicesImpl implements UserServices, SellerServices {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServicesImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public ApiResponse changeEmail(ChangeEmailRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = changeEmailMapper(seller, request);
        sellerRepository.save(updatedSeller);

        return new ApiResponse("Email changed successfully", true);
    }

    @Override
    public ApiResponse changePassword(ChangePasswordRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = changePasswordMapper(seller, request);
        sellerRepository.save(updatedSeller);

        return new ApiResponse("Password changed successfully", true);
    }

    @Override
    public ApiResponse updateProfile(UpdateSellerProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = updateProfileMapper(seller, request);
        sellerRepository.save(updatedSeller);

        return new ApiResponse("Profile updated successfully", true);
    }

}
