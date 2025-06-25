package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import org.bram.data.models.ProductCategory;
import org.springframework.data.annotation.Id;

public class CreateProductRequest {

    @NotBlank(message = "Enter product description")
    private String description;
    @NotBlank(message = "Enter product name")
    private String productName;
    @NotBlank(message = "Enter product price")
    private float price;
    @NotBlank(message = "Enter category")
    private String productCategory;
}