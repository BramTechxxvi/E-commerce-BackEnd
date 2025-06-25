package org.bram.data.models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection="Customers")
public class Customer extends User{

    @Id
    private String id;
    private List<BillingInfo> billing;
    private ShoppingCart cart;

}
