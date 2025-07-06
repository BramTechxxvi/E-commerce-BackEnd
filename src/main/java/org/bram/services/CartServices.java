package org.bram.services;

import org.bram.dtos.response.ApiResponse;
import org.bram.dtos.request.*;

public interface CartServices {

    ApiResponse addItem(AddItemRequest request);
//
//    removeItem(productID: String): void
//
//    getTotal(): double
//
//    clearCart(): void
}
