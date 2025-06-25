package org.bram.services;

import org.bram.dtos.request.AddToCartRequest;
import org.bram.dtos.response.AddToCartResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServices {

    AddToCartResponse addItemToCart(AddToCartRequest request);
    //    removeFromCart(productID: String): void
    // CLEARcART
    //    void addBillingInfo(String customerId, BillingInfo billingInfo);
//    void updateBillingInfo(String customerId, BillingInfo updatedBillingInfo);
//    void removeBillingInfo(String customerId, String cardNumber);
//
//    checkout(): Order
//
//    getCartTotal(): double
//

}
//    void clearCart(String customerId);
