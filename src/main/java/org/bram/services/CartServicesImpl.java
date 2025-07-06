package org.bram.services;

import com.cloudinary.api.exceptions.ApiException;
import org.bram.data.repository.CartRepository;
import org.bram.dtos.request.*;
import org.bram.dtos.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServicesImpl implements CartServices{

    private CartRepository cartRepository;

    @Autowired
    public CartServicesImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ApiResponse addItem(AddItemRequest request) {
        return new ApiResponse("Item added", true);
    }

    @Override
    public ApiResponse removeItem(RemoveItemRequest request) {
        return new ApiResponse("Item removed successfully", false);
    }
}
