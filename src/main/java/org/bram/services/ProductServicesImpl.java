package org.bram.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.bram.data.models.Product;
import org.bram.data.models.Seller;
import org.bram.data.repository.ProductRepository;
import org.bram.data.repository.SellerRepository;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.exceptions.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.bram.utils.ProductMapper.mapToProduct;

@Service
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final Cloudinary cloudinary;
    private final SellerRepository sellerRepository;

    public ProductServicesImpl(ProductRepository productRepository, Cloudinary cloudinary, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.cloudinary = cloudinary;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public ApiResponse addProduct(AddProductRequest request) {
        if(request.getImage() == null) throw new NullImageException("Product image is required");
        Map<?,?> uploadImage;
        try {
            uploadImage = cloudinary.uploader().upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload image");
        }
        String imageUrl = uploadImage.get("secure_url").toString();

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAuthorizedUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                        .equals("SELLER"));
        if (!isAuthorizedUser) throw new AccessDeniedException("You are not allowed to add products");

        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));
        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("Seller not logged in");
        Product product = mapToProduct(request, seller, imageUrl);

        productRepository.save(product);
        if(seller.getProducts() == null) seller.setProducts(new ArrayList<>());
        seller.getProducts().add(product);
        sellerRepository.save(seller);

        return  new ApiResponse("Product added successfully", true);
    }

    @Override
    public ApiResponse removeProduct(String productId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAuthorizedUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                        .equals("SELLER"));

        if(!isAuthorizedUser) throw new AccessDeniedException("You are not allowed to remove products");
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        if(!product.getSeller().getId().equals(seller.getId())) throw new AccessDeniedException("You are not allowed to remove this product");

        productRepository.delete(product);
        seller.getProducts().remove(product);
        sellerRepository.save(seller);

        return new ApiResponse("Product removed successfully", true);
    }

    @Override
    public ApiResponse updateProduct(String productId, UpdateProductRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAuthorizedUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(authority -> authority.getAuthority().equals("SELLER"));

        if(!isAuthorizedUser) throw new AccessDeniedException("You re not allowed to make any changed");
        Seller seller = sellerRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        if(!seller.isLoggedIn()) throw new UserNotLoggedInException("User not logged in");

    }
}
