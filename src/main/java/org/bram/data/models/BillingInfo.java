package org.bram.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="BillingInfos")
public class BillingInfo {

    private String receiverName;
    private CreditCard card;
    @DBRef
    private Address deliveryAddress;
    private String receiverPhoneNumber;
}
