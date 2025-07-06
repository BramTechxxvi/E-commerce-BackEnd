package org.bram.dtos.request;

import lombok.Data;
import org.bram.data.models.Product;

@Data
public class AddItemRequest {

    private int quantity;
    private Product product;
}
