package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.bram.data.models.Seller;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddProductRequest {

    @NotBlank(message = "Enter product description")
    private String description;
    @NotBlank(message = "Enter product name")
    private String productName;
    @NotBlank(message = "Enter product price")
    private double price;
    @NotBlank(message = "Enter category")
    private String productCategory;
    @NotBlank(message = "Enter quantity")
    private int productQuantity;
    private MultipartFile image;
    @DBRef
    private Seller seller;

}
