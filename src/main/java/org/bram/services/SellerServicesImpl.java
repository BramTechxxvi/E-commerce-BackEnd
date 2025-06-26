package org.bram.services;

import org.bram.data.models.Address;
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
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = changeEmailMapper(seller, request);
        sellerRepository.save(updatedSeller);

        ChangeEmailResponse response = new ChangeEmailResponse();
        response.setMessage("Email changed successfully");
        response.setSuccess(true);

        return response;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = changePasswordMapper(seller, request);
        sellerRepository.save(updatedSeller);

        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setSuccess(true);
        response.setMessage("Password changed successfully");
        return response;
    }

    @Override
    public UpdateSellerProfileResponse updateProfile(UpdateSellerProfileRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        Seller updatedSeller = updateProfileMapper(seller, request);
        sellerRepository.save(updatedSeller);

        UpdateSellerProfileResponse response = new UpdateSellerProfileResponse();
        response.setMessage("Profile updated successfully");
        response.setSuccess(true);
        return response;
    }

}
