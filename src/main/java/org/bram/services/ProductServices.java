package org.bram.services;

import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;

public interface ProductServices {

    ApiResponse addProduct(AddProductRequest request);

    ApiResponse removeProduct(String productId);

    ApiResponse updateProduct(String productId, UpdateProductRequest request);

    void getAllProducts();

}
