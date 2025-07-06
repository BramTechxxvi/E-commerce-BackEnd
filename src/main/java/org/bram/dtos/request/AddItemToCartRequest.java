package org.bram.dtos.request;

import lombok.Data;

@Data
public class AddItemToCartRequest {

    private int quantity;
    private String productId;
}
