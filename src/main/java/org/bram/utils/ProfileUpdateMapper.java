package org.bram.utils;

import org.bram.data.models.Address;
import org.bram.data.models.Customer;
import org.bram.data.models.Seller;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.UpdateCustomerProfileRequest;
import org.bram.dtos.request.UpdateSellerProfileRequest;
import org.bram.exceptions.*;

import static org.bram.utils.PasswordUtil.verifyPassword;

public class ProfileUpdateMapper {

    public static Seller changeEmailMapper(Seller seller, ChangeEmailRequest request) {
        if(seller.isBanned()) throw new AccessDeniedException("Your account has been banned");
        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller is not logged in");
        boolean isSameEmail = request.getOldEmail().equals(request.getNewEmail());
        if(isSameEmail) throw new SameEmailException("New email cannot be same as old email");

        boolean isCorrectOldEmail = request.getOldEmail().equals(seller.getEmail());
        if(!isCorrectOldEmail) throw new IncorrectOldEmailException("Old email not correct");

        seller.setEmail(request.getNewEmail());
        return seller;
    }

    public static Seller changePasswordMapper(Seller seller, ChangePasswordRequest request) {
        if(seller.isBanned()) throw new AccessDeniedException("Your accunt has been banned");
        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller not logged in");
        boolean isSamePassword = request.getOldPassword().equals(request.getNewPassword());
        if(isSamePassword) throw new SamePasswordException("New password cannot be the same as old password");

        boolean isCorrectOldPassword = verifyPassword(request.getOldPassword(), seller.getPassword());
        if(!isCorrectOldPassword) throw new IncorrectOldPasswordException("Old password not correct");

        seller.setPassword(request.getNewPassword());
        return seller;
    }

    public static Seller updateProfileMapper(Seller seller, UpdateSellerProfileRequest request) {
        if(seller.isBanned()) throw new AccessDeniedException("Your accunt has been banned");
        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller not logged in");
        boolean validStoreName = request.getStoreName() != null && ! request.getStoreName().trim().isBlank();
        if (validStoreName) seller.setStoreName(request.getStoreName().trim());

        boolean validStoreDescription = request.getStoreDescription() != null && ! request.getStoreDescription().trim().isBlank();
        if (validStoreDescription) seller.setStoreDescription(request.getStoreDescription().trim());

        boolean validPhone = request.getPhoneNumber() != null && ! request.getPhoneNumber().trim().isBlank();
        if (validPhone) seller.setPhone(request.getPhoneNumber().trim());

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
        return seller;
    }

    public static Customer changeEmailMapper(Customer customer, ChangeEmailRequest request) {
        if(!customer.isLoggedIn()) throw new UserNotLoggedInException("Customer is not logged in");
        boolean isSameEmail = request.getOldEmail().equals(request.getNewEmail());
        if(isSameEmail) throw new SameEmailException("New email cannot be same as old email");

        boolean isCorrectOldEmail = request.getOldEmail().equals(customer.getEmail());
        if(!isCorrectOldEmail) throw new IncorrectOldEmailException("Old email not correct");
        customer.setEmail(request.getNewEmail());

        return customer;
    }

    public static Customer changePasswordMapper(Customer customer, ChangePasswordRequest request) {
        if(!customer.isLoggedIn()) throw new UserNotLoggedInException("Customer not logged in");
        boolean isSamePassword = request.getOldPassword().equals(request.getNewPassword());
        if(isSamePassword) throw new SamePasswordException("New password cannot be the same as old password");

        boolean isCorrectOldPassword = verifyPassword(request.getOldPassword(), customer.getPassword());
        if(!isCorrectOldPassword) throw new IncorrectOldPasswordException("Old password not correct");
        customer.setPassword(request.getNewPassword());

        return customer;
    }

    public static Customer updateProfileMapper(Customer customer, UpdateCustomerProfileRequest request) {
        if(!customer.isLoggedIn()) throw new UserNotLoggedInException("Customer not logged in");
        boolean validPhone = request.getPhoneNumber() != null && ! request.getPhoneNumber().trim().isBlank();
        if (validPhone) customer.setPhone(request.getPhoneNumber().trim());

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

        if (validAddress) customer.setAddress(address);
        return customer;
    }
}
