package org.bram.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.bram.data.models.Product;
import org.bram.data.models.ProductCategory;
import org.bram.data.models.Seller;
import org.bram.data.repository.ProductRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.RemoveProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final Cloudinary cloudinary;
    private final SellerRepository sellerRepository;

    public ProductServiceImpl(ProductRepository productRepository, Cloudinary cloudinary, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.cloudinary = cloudinary;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public ApiResponse addProduct(AddProductRequest request) {
        try {
            if(request.getImage() == null) throw new NullImageException("Product image is required");
            Map<?,?> uploadImage = cloudinary.uploader().upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadImage.get("secure_url").toString();

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Seller seller = sellerRepository.findByEmail(email)
                    .orElseThrow(()-> new UserNotFoundException("Seller not found"));

            Seller updatedSeller = mapProductToSeller()

            Product product = new Product();
            product.setProductName(request.getProductName().trim());
            product.setDescription(request.getDescription());
            product.setProductQuantity(request.getProductQuantity());
            product.setPrice(request.getPrice());
            product.setCategory(ProductCategory.valueOf(request.getProductCategory().toUpperCase()));
            product.setImageUrl(imageUrl);
            product.setSeller(seller);

            productRepository.save(product);
            if(seller.getProducts() == null) seller.setProducts(new ArrayList<>());
            seller.getProducts().add(product);
            sellerRepository.save(seller);

            return  new ApiResponse("Product added successfully", true);

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
