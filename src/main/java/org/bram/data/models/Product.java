package org.bram.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@Document(collection="Products")
public class Product {

    @Id
    private String productId;
    private String imageUrl;
    private String description;
    private String productName;
    private double price;
    private int productQuantity;
    private ProductCategory category;

    @DBRef
    private Seller seller;
}
