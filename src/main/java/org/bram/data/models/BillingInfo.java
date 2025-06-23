package org.bram.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class BillingInfo {

    private String receiverName;
    private CreditCard card;
    private Address deliveryAddress;
    private String receiverPhoneNumber;
}
