package org.bram.services;

import org.bram.dtos.request.AddToCartRequest;
import org.bram.dtos.response.AddToCartResponse;

public interface CustomerServices {

    AddToCartResponse addItemToCart(AddToCartRequest request);
    //    removeFromCart(productID: String): void
//
//    checkout(): Order
//
//    getCartTotal(): double
//
//    addBillingInfo(info: BillingInfo): void


}

//    void addBillingInfo(String customerId, BillingInfo billingInfo);
//    void updateBillingInfo(String customerId, BillingInfo updatedBillingInfo);
//    void removeBillingInfo(String customerId, String cardNumber);
//    ShoppingCart getShoppingCart(String customerId);
//    void addItemToCart(String customerId, Item item);
//    void removeItemFromCart(String customerId, String productId);
//    void clearCart(String customerId);
//}
