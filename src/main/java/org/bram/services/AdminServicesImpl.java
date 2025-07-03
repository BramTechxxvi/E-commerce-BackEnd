package org.bram.services;

import org.bram.data.repository.UserRepository;
import org.bram.dtos.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServicesImpl implements AdminServices{

    private final UserRepository userRepository;

    @Autowired
    public AdminServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse banUser(String id) {
        return null;
    }

    @Override
    public ApiResponse unBanUser(String id) {
        return null;
    }
}
