package org.bram.data.models;

import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Data
public class BillingInfo {

    private String receiverName;
    private CreditCard card;
    private Address deliveryAddress;
    private String receiverPhoneNumber;
}
