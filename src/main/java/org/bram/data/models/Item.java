package org.bram.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Item {

    private int quantity;
    private Product product;
}
