package org.bram.dtos.request;

import lombok.Data;

@Data
public class UpdateCustomerProfileRequest {

    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String houseNumber;
    private String country;
}
