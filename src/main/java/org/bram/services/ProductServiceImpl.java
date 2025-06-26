package org.bram.services;

import org.bram.dtos.request.CreateProductRequest;
import org.bram.dtos.request.RemoveProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductServices {

    @Override
    public ApiResponse createProduct(CreateProductRequest request) {
        try {
            Map uploadImage = cloudinary.uploader().upload(request.getImage())
        }
        return null;
    }
//    @Override
//    public ApiResponse createProduct(CreateProductRequest request) {
//        try {
//            // Upload image to Cloudinary
//            Map uploadResult = cloudinary.uploader().upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
//            String imageUrl = (String) uploadResult.get("secure_url");
//
//            Product product = new Product();
//            product.setDescription(request.getDescription());
//            product.setProductName(request.getProductName());
//            product.setPrice(request.getPrice());
//            product.setCategory(ProductCategory.valueOf(request.getProductCategory().toUpperCase()));
//            product.setImageUrl(imageUrl);
//
//            // Save to DB (assume productRepository is injected)
//            productRepository.save(product);
//
//            return new ApiResponse(true, "Product created successfully", product);
//        } catch (Exception e) {
//            return new ApiResponse(false, "Product creation failed: " + e.getMessage());
//        }
//    }

    @Override
    public ApiResponse removeProduct(RemoveProductRequest request) {
        return null;
    }

    @Override
    public ApiResponse updateProduct(UpdateProductRequest request) {
        return null;
    }
}
