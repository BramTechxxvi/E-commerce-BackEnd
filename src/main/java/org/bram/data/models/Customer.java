package org.bram.data.models;

import lombok.Data;

import java.util.List;

@Data
public class Customer extends User{

    private List<BillingInfo> billing;
    private ShoppingCart cart;

}
