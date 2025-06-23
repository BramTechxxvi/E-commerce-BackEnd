package org.bram.data.models;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Customer extends User{

    @Setter
    @Getter
    private List<BillingInfo> billing;
    @Getter
    @Setter
    private ShoppingCart cart;

}
