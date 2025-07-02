package org.bram.controllers;


import jakarta.validation.Valid;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.services.ProductServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServicesImpl productServices;

    @Autowired
    public ProductController(ProductServicesImpl productServices) {
        this.productServices = productServices;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid AddProductRequest request) {
        try {
            ApiResponse response = productServices.addProduct(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch () {

        }
    }

    @PostMapping
}
