package org.bram.dtos.request;

import lombok.Data;
import org.bram.data.models.ProductCategory;

@Data
public class UpdateProductRequest {
    private String imageUrl;
    private String description;
    private String productName;
    private double price;
    private int productQuantity;
    private String category;
}
