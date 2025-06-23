package org.bram.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Product {

    @Id
    private String productId;
    private String description;
    private String productName;
    private int price;
    private ProductCategory category;

}
