package org.bram.services;

import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.bram.dtos.response.ChangePasswordResponse;

public class SellerServicesImpl implements UserServices{

    private SellerRepository sellerRepository;

    public SellerServicesImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
        Seller seller = sellerRepository.findById(request.getUserId())
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        return null;
    }
}
