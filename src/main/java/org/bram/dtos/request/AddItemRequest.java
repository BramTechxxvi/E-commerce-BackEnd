package org.bram.dtos.request;

import lombok.Data;

@Data
public class AddItemRequest {

    private int quantity;
    private String productId;
}
