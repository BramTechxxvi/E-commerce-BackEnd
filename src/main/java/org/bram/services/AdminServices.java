package org.bram.services;


import org.bram.dtos.response.ApiResponse;

public interface AdminServices {

    ApiResponse banUser(String id, String token);

    ApiResponse unBanUser(String id);

}
