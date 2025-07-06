package org.bram.dtos.request;

import lombok.Data;
import org.bram.data.models.Product;

@Data
public class RemoveItemRequest {

    private int quantity;
    private Product product;
}
