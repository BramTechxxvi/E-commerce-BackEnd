package org.bram.utils;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bram.data.models.Address;
import org.bram.data.models.Seller;
import org.bram.data.models.User;
import org.bram.data.models.UserRole;
import org.bram.dtos.request.RegisterRequest;
import org.bram.exceptions.DetailsAlreadyInUseException;
import org.springframework.data.annotation.Id;

public class Mapper {

    public static Seller mapToSeller(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setPassword(registerRequest.getPassword());

        Address address = new Address();
        address.setStreet(registerRequest.getStreet());
        address.setCity(registerRequest.getCity());
        address.setState(registerRequest.getState());
        address.setHouseNumber(registerRequest.getHouseNumber());
        address.setCountry(registerRequest.getCountry());

        user.setAddress(address);

        UserRole userRole;
        try {
            userRole = UserRole.valueOf(registerRequest.getUserRole());
        } catch (IllegalArgumentException e) {
            throw new DetailsAlreadyInUseException("");
        }

        return seller;
    }
}


