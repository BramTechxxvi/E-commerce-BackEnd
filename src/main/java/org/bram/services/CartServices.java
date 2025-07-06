package org.bram.services;

import org.bram.dtos.response.ApiResponse;
import org.bram.dtos.request.*;

public interface CartServices {

    ApiResponse addItem(AddItemToCartRequest request);

    ApiResponse removeItem(RemoveItemRequest request);

//    getTotal(): double
//
//    clearCart(): void
}
