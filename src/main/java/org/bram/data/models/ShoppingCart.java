package org.bram.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection="ShoppingCarts")
public class ShoppingCart {

    private List<Item> items;
}
