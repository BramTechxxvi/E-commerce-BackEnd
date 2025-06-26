package org.bram.services;

import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.RemoveProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;

public interface ProductServices {

    ApiResponse addProduct(AddProductRequest request);

    ApiResponse removeProduct(RemoveProductRequest request);

    ApiResponse updateProduct(UpdateProductRequest request);

}
