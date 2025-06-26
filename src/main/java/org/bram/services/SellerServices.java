package org.bram.services;

import org.bram.dtos.request.UpdateSellerProfileRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerServices {

    UpdateSellerProfileResponse updateProfile(UpdateSellerProfileRequest request);

}
