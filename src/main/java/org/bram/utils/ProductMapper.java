package org.bram.utils;

import org.bram.data.models.Product;
import org.bram.data.models.ProductCategory;
import org.bram.data.models.Seller;
import org.bram.dtos.request.AddProductRequest;

public class ProductMapper {

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

    public static Product updateProductMapper() {}
}

