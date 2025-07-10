package org.bram.services;

import org.bram.data.models.Customer;
import org.bram.data.models.Item;
import org.bram.data.models.Product;
import org.bram.data.models.ShoppingCart;
import org.bram.data.repository.CartRepository;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.ProductRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServicesImpl implements CartServices{

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartServicesImpl(CustomerRepository customerRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ApiResponse addItemToCart(AddItemToCartRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAuthorizedUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("CUSTOMER"));
        if(!isAuthorizedUser) throw new AccessDeniedException("Only customers can add item to cart");

        Customer customer = customerRepository.findByEmail(email.toLowerCase())
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        if(customer.isBanned()) throw new AccessDeniedException("Your account has been banned");
        if(!customer.isLoggedIn()) throw new UserNotLoggedInException("Customer not logged in");

        Product savedProduct = productRepository.findById(request.getProductId())
                        .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        if(savedProduct.getProductQuantity() < request.getQuantity()) {
            throw new QuantityUnAvailableException("Not enough product quantity available");
        }
        ShoppingCart cart = customer.getCart();
        if(cart == null) {
            cart = new ShoppingCart();
            cart.setItems(new ArrayList<>());
        }
        boolean productAlreadyInCart = false;
        for (Item item : cart.getItems()) {
            if(item.getProduct().getProductId().equals(savedProduct.getProductId())) {
                item.setQuantity(item.getQuantity() + request.getQuantity());
                productAlreadyInCart = true; break;
            }}
        if(!productAlreadyInCart) {
            Item newItem = new Item();
            newItem.setProduct(savedProduct);
            newItem.setQuantity(request.getQuantity());
            cart.getItems().add(newItem);
        }
        cart.setUserId(customer.getId());
        cartRepository.save(cart);
        customer.setCart(cart);
        customerRepository.save(customer);

        return new ApiResponse("Item added to cart successfully", true);
    }

    @Override
    public ApiResponse removeFromCart(RemoveItemRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAuthorizedUser = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("CUSTOMER"));

        return new ApiResponse("Item removed successfully", false);
    }
//98
//        @Override
//        public ApiResponse removeFromCart(String productId) {
//            String email = SecurityContextHolder.getContext().getAuthentication().getName();
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//            if (!user.isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
//
//            ShoppingCart cart = user.getCart();
//            if (cart == null || cart.getItems() == null) {
//                throw new RuntimeException("Cart is empty");
//            }
//
//            boolean removed = cart.getItems().removeIf(item ->
//                    item.getProduct().getProductId().equals(productId));
//
//            if (!removed) throw new ProductNotFoundException("Product not in cart");
//
//            user.setCart(cart);
//            userRepository.save(user);
//
//            return new ApiResponse("Item removed from cart", true);
//        }
//
//        @Override
//        public ApiResponse updateItemQuantity(UpdateCartItemRequest request) {
//            String email = SecurityContextHolder.getContext().getAuthentication().getName();
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(() -> new UserNotFoundException("User not found"));
//
//            if (!user.isLoggedIn()) throw new UserNotLoggedInException("User not logged in");
//
//            ShoppingCart cart = user.getCart();
//            if (cart == null || cart.getItems() == null) {
//                throw new RuntimeException("Cart is empty");
//            }
//
//            boolean updated = false;
//            for (Item item : cart.getItems()) {
//                if (item.getProduct().getProductId().equals(request.getProductId())) {
//                    item.setQuantity(request.getNewQuantity());
//                    updated = true;
//                    break;
//                }
//            }
//
//            if (!updated) throw new ProductNotFoundException("Product not found in cart");
//
//            user.setCart(cart);
//            userRepository.save(user);
//
//            return new ApiResponse("Cart updated successfully", true);
//        }
//    }

}
