package org.bram.data.models;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection="Customers")
public class Customer extends User{

    private List<BillingInfo> billing;
    private ShoppingCart cart;

}
