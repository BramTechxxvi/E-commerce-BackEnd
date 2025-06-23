package org.bram.data.models;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.List;

@Data
@Document(collection="Sellers")
public class Seller {

    private String storeName;
    private String storeDescription;
    private boolean isVerified;
    private List<Product> productList;



}
