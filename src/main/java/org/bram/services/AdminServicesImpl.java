package org.bram.services;

import org.bram.data.models.User;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicesImpl implements AdminServices{

    private final UserRepository userRepository;

    @Autowired
    public AdminServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse banUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found"));

        user
    }

    @Override
    public ApiResponse unBanUser(String id) {
        return null;
    }
}
