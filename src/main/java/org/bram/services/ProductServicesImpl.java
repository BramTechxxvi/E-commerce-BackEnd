package org.bram.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.bram.data.models.*;
import org.bram.data.repository.*;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bram.utils.ProductMapper.mapToProduct;
import static org.bram.utils.ProductMapper.updateProductMapper;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {

    private final ProductRepository productRepository;
    private final Cloudinary cloudinary;
    private final SellerRepository sellerRepository;


    @Override
    public ApiResponse addProduct(AddProductRequest request) {
        if(request.getImage() == null) throw new NullImageException("Product image is required");
        String imageUrl;
        try {
            Map<?,?> uploadImage = cloudinary.uploader().upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
            imageUrl = uploadImage.get("secure_url").toString();
        } catch (IOException e) {
            throw new ImageUploadException("Failed to upload image");
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAuthorizedUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority()
                        .equals("SELLER"));
        if (!isAuthorizedUser) throw new AccessDeniedException("You are not allowed to add products");

        Seller seller = sellerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));
        if(seller.isBanned()) throw new AccessDeniedException("Your account has been banned");
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
        Seller seller = sellerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));
        if(seller.isBanned()) throw new AccessDeniedException("Your account has been banned");
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

        if (!isAuthorizedUser) throw new AccessDeniedException("You're not allowed to make any changes");
        Seller seller = sellerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UserNotFoundException("Seller not found"));

        if(seller.isBanned()) throw new AccessDeniedException("Your account has been banned");
        if (!seller.isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (!product.getSeller().getId().equals(seller.getId()))
            throw new AccessDeniedException("You're not allowed to make any changes");
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(request.getImage().getBytes(), ObjectUtils.emptyMap());
                product.setImageUrl(uploadResult.get("secure_url").toString());
            } catch (IOException E) {
                throw new ImageUploadException("Failed to upload image");
            }
        }
        Product updatedProduct = updateProductMapper(product, request);
        productRepository.save(updatedProduct);
        seller.getProducts().replaceAll(productt -> productt.getProductId()
                .equals(productId) ? updatedProduct : productt);

        return new ApiResponse("Product updated successfully", true);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
