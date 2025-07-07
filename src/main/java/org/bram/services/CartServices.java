package org.bram.services;

import org.bram.dtos.response.ApiResponse;
import org.bram.dtos.request.*;

public interface CartServices {

    ApiResponse addItemToCart(AddItemToCartRequest request);

    ApiResponse removeFromCart(RemoveItemRequest request);

//    getTotal(): double
//
//    clearCart(): void
}
