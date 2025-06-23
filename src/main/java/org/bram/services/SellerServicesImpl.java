package org.bram.services;

import org.bram.data.models.Seller;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerServicesImpl implements UserServices{

    private SellerRepository sellerRepository;

    @Autowired
    public SellerServicesImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
        Seller seller = sellerRepository.findById(request.getUserId())
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller is not logged in");
        boolean isSameEmail = request.getOldEmail().equals(request.getNewEmail());
        if(isSameEmail) throw new SameEmailException("New email cannot be same as old email");

        boolean isOldEmail = request.getOldEmail().equals(seller.getEmail());
        if(!isOldEmail) throw new IncorrectOldEmailException("Old email not correct");

        seller.setEmail(request.getNewEmail());
        sellerRepository.save(seller);

        ChangeEmailResponse response = new ChangeEmailResponse();
        response.setSuccess(true);
        response.setMessage("Email changed successfully");
        return response;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        Optional<Seller> optionalSeller = sellerRepository.findById(request.)
    }
}
