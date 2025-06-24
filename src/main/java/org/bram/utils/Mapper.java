package org.bram.utils;


import org.bram.data.models.Address;
import org.bram.data.models.Seller;
import org.bram.data.models.User;
import org.bram.dtos.request.RegisterRequest;

public class Mapper {

    public static Seller mapToSeller(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setPhone(registerRequest.getPhone());
        Address address = new Address();
        user.setAddress(registerRequest.getAddress());

        return seller;
    }
}
