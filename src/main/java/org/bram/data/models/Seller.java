package org.bram.data.models;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection="Sellers")
public class Seller {

    private String StoreName
}
