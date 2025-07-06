package org.bram.services;

import org.bram.dtos.request.AddItemRequest;
import org.bram.dtos.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class CartServicesImpl implements CartServices{


    @Override
    public ApiResponse addItem(AddItemRequest request) {
        return new ApiResponse("Item added", true);
    }
}
