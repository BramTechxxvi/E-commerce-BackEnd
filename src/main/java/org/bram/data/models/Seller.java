package org.bram.data.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Document(collection="Sellers")
public class Seller extends User{

    private String storeName;
    private String storeDescription;
    private boolean isVerified;
    private List<Product> products;
}
