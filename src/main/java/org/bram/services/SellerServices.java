package org.bram.services;

import org.springframework.stereotype.Repository;

@Repository
public interface SellerServices {

    CreateProductResponse createProduct(CreateProductRequest request);
}
