package org.bram.services;

import org.bram.data.models.Product;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;

import java.util.List;

public interface ProductServices {

    ApiResponse addProduct(AddProductRequest request);

    ApiResponse removeProduct(String productId);

    ApiResponse updateProduct(String productId, UpdateProductRequest request);

    List<Product> getAllProducts();

}
