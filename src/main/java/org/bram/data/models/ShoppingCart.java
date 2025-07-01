package org.bram.data.models;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCart {

    private List<Item> items;
}
