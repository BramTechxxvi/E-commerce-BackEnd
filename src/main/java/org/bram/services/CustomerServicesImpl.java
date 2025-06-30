package org.bram.services;

import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.request.UpdateCustomerProfileRequest;
import org.bram.dtos.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerServicesImpl implements UserServices, CustomerServices {

    @Override
    public ApiResponse changeEmail(ChangeEmailRequest request) {
        return null;
    }

    @Override
    public ApiResponse changePassword(ChangePasswordRequest request) {
        return null;
    }

    @Override
    public ApiResponse updateProfile(UpdateCustomerProfileRequest request) {
        return null;
    }
}
