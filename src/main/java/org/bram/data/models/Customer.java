package org.bram.data.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection="Customers")
public class Customer extends User{

    @Setter
    @Getter
    private List<BillingInfo> billing;
    @Getter
    @Setter
    private ShoppingCart cart;

}
