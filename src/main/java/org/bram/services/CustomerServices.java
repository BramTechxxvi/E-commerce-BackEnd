package org.bram.services;

import org.bram.dtos.request.UpdateCustomerProfileRequest;
import org.bram.dtos.response.ApiResponse;

public interface CustomerServices {

    ApiResponse updateProfile(UpdateCustomerProfileRequest request);

}
