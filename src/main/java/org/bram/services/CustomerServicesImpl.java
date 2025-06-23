package org.bram.services;

import org.bram.data.repository.CustomerRepository;
import org.bram.dtos.request.ChangeEmailRequest;
import org.bram.dtos.request.ChangePasswordRequest;
import org.bram.dtos.response.ChangeEmailResponse;
import org.bram.dtos.response.ChangePasswordResponse;

public class CustomerServicesImpl implements UserServices{

    private CustomerRepository customerRepository;

    @Override
    public String getFullName() {
        return
    }

    @Override
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
        return null;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        return null;
    }
}
