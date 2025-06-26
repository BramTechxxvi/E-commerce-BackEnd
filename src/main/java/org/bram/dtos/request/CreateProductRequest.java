package org.bram.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductRequest {

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

}


//private String productId;
//private String imageUrl;
//private String description;
//private String productName;
//private double price;
//private int productQuantity;
//private ProductCategory category;
//
//@DBRef
//private Seller seller;