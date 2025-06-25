package org.bram.services;

import org.bram.data.models.Seller;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller is not logged in");
        boolean isSameEmail = request.getOldEmail().equals(request.getNewEmail());
        if(isSameEmail) throw new SameEmailException("New email cannot be same as old email");

        boolean isOldEmail = request.getOldEmail().equals(seller.getEmail());
        if(!isOldEmail) throw new IncorrectOldEmailException("Old email not correct");

        seller.setEmail(request.getNewEmail());
        sellerRepository.save(seller);

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

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller not logged in");
        boolean isSamePassword = request.getOldPassword().equals(request.getNewPassword());
        if(isSamePassword) throw new SamePasswordException("New password cannot be the same as old password");

        boolean isOldEmail = request.getOldPassword().equals(seller.getEmail());
        if(!isOldEmail) throw new IncorrectOldPasswordException("Old password not correct");

        seller.setPassword(request.getNewPassword());
        sellerRepository.save(seller);

        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setSuccess(true);
        response.setMessage("Password changed successfully");
        return response;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        return null;
    }
}
