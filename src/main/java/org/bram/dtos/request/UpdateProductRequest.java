package org.bram.dtos.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductRequest {
    private MultipartFile imageUrl;
    private String description;
    private String productName;
    private double price;
    private int productQuantity;
    private String category;
}
