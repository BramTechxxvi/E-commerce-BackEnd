package org.bram.data.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Addresses")
public class Address {

    private String street;
    private String city;
    private String state;
    private String houseNumber;
    private String country;
}
