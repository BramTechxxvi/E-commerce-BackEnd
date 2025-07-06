package org.bram.services;

import lombok.RequiredArgsConstructor;
import org.bram.data.models.Customer;
import org.bram.data.models.Product;
import org.bram.data.repository.CartRepository;
import org.bram.data.repository.CustomerRepository;
import org.bram.data.repository.ProductRepository;
import org.bram.data.repository.UserRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.bram.exceptions.AccessDeniedException;
import org.bram.exceptions.ProductNotFoundException;
import org.bram.exceptions.UserNotFoundException;
import org.bram.exceptions.UserNotLoggedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServicesImpl implements CartServices{

    private final CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

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


        request.setProductId();

        return new ApiResponse("Item added", true);
    }

    @Override
    public ApiResponse removeItem(RemoveItemRequest request) {
        return new ApiResponse("Item removed successfully", false);
    }
/
//            Product product = productRepository.findById(request.getProductId())
//                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
//
//            if (product.getProductQuantity() < request.getQuantity()) {
//                throw new IllegalArgumentException("Not enough product quantity available");
//            }
//
//            ShoppingCart cart = user.getCart();
//            if (cart == null) {
//                cart = new ShoppingCart();
//                cart.setItems(new ArrayList<>());
//            }
//
//            // Check if product already in cart
//            boolean productAlreadyInCart = false;
//            for (Item item : cart.getItems()) {
//                if (item.getProduct().getProductId().equals(product.getProductId())) {
//                    item.setQuantity(item.getQuantity() + request.getQuantity());
//                    productAlreadyInCart = true;
//                    break;
//                }
//            }
//
//            if (!productAlreadyInCart) {
//                Item newItem = new Item();
//                newItem.setProduct(product);
//                newItem.setQuantity(request.getQuantity());
//                cart.getItems().add(newItem);
//            }
//
//            user.setCart(cart);
//            userRepository.save(user);
//
//            return new ApiResponse("Item added to cart successfully", true);
//        }
//
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
