package org.bram.utils;


import org.bram.data.models.Seller;
import org.bram.dtos.request.RegisterRequest;

public class Mapper {

    public static Seller mapToSeller(RegisterRequest registerRequest) {
        Seller seller = new Seller();
        seller.setFirstName(registerRequest.getFirstName());
        seller.setLastName(registerRequest.getLastName());
        seller.setEmail(registerRequest.getEmail());
        seller.setPassword(registerRequest.getPassword());
        seller.setPhone(registerRequest.getPhone());
        seller.setAddress(registerRequest.getAddress());

        return seller;
    }
}
