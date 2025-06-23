package org.bram.services;

import org.bram.data.models.Seller;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.bram.dtos.response.ChangePasswordResponse;
import org.bram.exceptions.UserNotFoundException;
import org.bram.exceptions.UserNotLoggedInException;

public class SellerServicesImpl implements UserServices{

    private SellerRepository sellerRepository;

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
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        return null;
    }
}
