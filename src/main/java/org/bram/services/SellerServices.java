package org.bram.services;

import org.bram.dtos.request.CreateProductRequest;
import org.bram.dtos.response.CreateProductResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerServices {

    CreateProductResponse createProduct(CreateProductRequest request);
}
