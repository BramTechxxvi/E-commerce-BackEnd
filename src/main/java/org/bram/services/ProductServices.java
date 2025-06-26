package org.bram.services;

import org.bram.dtos.request.CreateProductRequest;
import org.bram.dtos.request.RemoveProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.dtos.response.CreateProductResponse;

public interface ProductServices {

    ApiResponse createProduct(CreateProductRequest request);

    ApiResponse removeProduct(RemoveProductRequest request);

    ApiResponse updateProduct(UpdateProductRequest request);
}
