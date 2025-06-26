package org.bram.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.bram.data.models.Product;
import org.bram.data.models.ProductCategory;
import org.bram.data.repository.ProductRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.RemoveProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final Cloudinary cloudinary;

    public ProductServiceImpl(ProductRepository productRepository, Cloudinary cloudinary) {
        this.productRepository = productRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public ApiResponse addProduct(AddProductRequest request) {
        try {
            if(request.getImage() == null && request.getImage().isEmpty()) throw new NullImageException("Product image is required");
            Map<?,?> uploadImage = cloudinary.uploader().upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadImage.get("secure_url").toString();

            Product product = new Product();
            product.setProductName(request.getProductName());
            product.setDescription(request.getDescription());
            product.setProductQuantity(request.getProductQuantity());
            product.setPrice(request.getPrice());
            product.setCategory(ProductCategory.valueOf(request.getProductCategory().toUpperCase()));
            product.setImageUrl(imageUrl);

            productRepository.save(product);
            return  new ApiResponse("Product created successfully", true);

        } catch (IllegalArgumentException | IOException e) {
            return new ApiResponse("Failed to create: " + request.getProductName(), false);
        }
    }


    @Override
    public ApiResponse removeProduct(RemoveProductRequest request) {
        return null;
    }

    @Override
    public ApiResponse updateProduct(UpdateProductRequest request) {
        return null;
    }
}
