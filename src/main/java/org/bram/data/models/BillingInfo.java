package org.bram.data.models;

import lombok.Data;

@Data
public class BillingInfo {

    private String receiverName;
    private CreditCard card;
    private Address deliveryAddress;
    private String receiverPhoneNumber;
}
