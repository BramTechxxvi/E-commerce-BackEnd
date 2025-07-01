package org.bram.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.bram.data.models.Product;
import org.bram.data.models.ProductCategory;
import org.bram.data.models.Seller;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.exceptions.ImageUploadException;

import java.io.IOException;
import java.util.Map;

public class ProductMapper {

    private final Cloudinary cloudinary;

    public ProductMapper(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public static Product mapToProduct(AddProductRequest request, Seller seller, String imageUrl) {
        Product product = new Product();
        product.setProductName(request.getProductName().trim());
        product.setDescription(request.getDescription());
        product.setProductQuantity(request.getProductQuantity());
        product.setPrice(request.getPrice());

        try {
            product.setCategory(ProductCategory.valueOf(request.getProductCategory().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product category" + request.getProductCategory());
        }
        product.setImageUrl(imageUrl);
        product.setSeller(seller);
        return product;
    }

    public static Product updateProductMapper(Product product, UpdateProductRequest request) {
        if(request.getImageUrl() != null && !request.getImageUrl().isEmpty()) {
            try {
                Map<?,?> uploadResult = cloudinary.uploader().upload(request.getImageUrl(), ObjectUtils.emptyMap());
                product.setImageUrl(uploadResult.get("secure_url").toString());
            } catch (IOException E) {
                throw new ImageUploadException("Failed to upload image");
            }}
        if(request.getProductName() != null) product.setProductName(request.getProductName());
        if(request.getDescription() != null) product.setDescription(request.getDescription());
        if(request.getPrice() > 0) product.setPrice(request.getPrice());
        if(request.getProductQuantity() >=0) product.setProductQuantity(request.getProductQuantity());
        if(request.getCategory() != null) {
            try {
                product.setCategory(ProductCategory.valueOf(request.getCategory().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid product category" + request.getCategory());
            }
        }
//
//    // Update other fields if provided
//    if (request.getProductName() != null) product.setProductName(request.getProductName());
//    if (request.getDescription() != null) product.setDescription(request.getDescription());
//    if (request.getPrice() > 0) product.setPrice(request.getPrice());
//    if (request.getProductQuantity() >= 0) product.setProductQuantity(request.getProductQuantity());
//    if (request.getCategory() != null) product.setCategory(request.getCategory());
    }
}

