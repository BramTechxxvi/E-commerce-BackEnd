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

import static org.bram.utils.PasswordUtil.verifyPassword;
import static org.bram.utils.ProfileUpdateMapper.changeEmailMapper;

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

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller not logged in");
        boolean isSamePassword = request.getOldPassword().equals(request.getNewPassword());
        if(isSamePassword) throw new SamePasswordException("New password cannot be the same as old password");

        boolean isCorrectOldPassword = verifyPassword(request.getOldPassword(), seller.getPassword());
        if(!isCorrectOldPassword) throw new IncorrectOldPasswordException("Old password not correct");

        seller.setPassword(request.getNewPassword());
        sellerRepository.save(seller);

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

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller not logged in");
        boolean validStoreName = request.getStoreName() != null && ! request.getStoreName().trim().isBlank();
        if (validStoreName) seller.setStoreName(request.getStoreName().trim());

        boolean validStoreDescription = request.getStoreDescription() != null && ! request.getStoreDescription().trim().isBlank();
        if (validStoreDescription) seller.setStoreDescription(request.getStoreDescription().trim());

        Address address = new Address();
        address.setHouseNumber(request.getHouseNumber());
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());

        boolean validAddress = ((address.getHouseNumber() != null && address.getHouseNumber().trim().isBlank())
                && (address.getStreet() != null && address.getStreet().trim().isBlank())
                && (address.getCity() != null && address.getCity().trim().isBlank())
                && (address.getState() != null && address.getState().trim().isBlank())
                && (address.getCountry() != null && address.getCountry().trim().isBlank()));
        if (validAddress) seller.setAddress(address);
        sellerRepository.save(seller);

        UpdateSellerProfileResponse response = new UpdateSellerProfileResponse();
        response.setMessage("Profile updated successfully");
        response.setSuccess(true);
        return response;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {

        return null;
    }
}
