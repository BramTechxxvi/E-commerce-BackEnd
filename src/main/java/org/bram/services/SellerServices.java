package org.bram.services;

import org.bram.dtos.request.CreateProductRequest;
import org.bram.dtos.request.UpdateSellerProfileRequest;
import org.bram.dtos.response.CreateProductResponse;
import org.bram.dtos.response.UpdateSellerProfileResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerServices {

    UpdateSellerProfileResponse updateProfile(UpdateSellerProfileRequest request);
    CreateProductResponse createProduct(CreateProductRequest request);
}
