package org.bram.data.models;

import lombok.Data;

@Data
public class CreditCard {

    private String cardNumber;
    private String nameOnCard;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;
    private CardType cardType;
}

