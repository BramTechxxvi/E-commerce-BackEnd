package org.bram.services;

import org.bram.dtos.request.UpdateSellerProfileRequest;
import org.bram.dtos.response.ApiResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerServices {

    ApiResponse updateProfile(UpdateSellerProfileRequest request);

}
