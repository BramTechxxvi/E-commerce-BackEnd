package org.bram.controllers;


import jakarta.validation.Valid;
import org.bram.dtos.request.AddProductRequest;
import org.bram.dtos.request.UpdateProductRequest;
import org.bram.dtos.response.ApiResponse;
import org.bram.services.ProductServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        ApiResponse response = productServices.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/removeProduct/{id}")
    public ResponseEntity<ApiResponse> removeProduct(@PathVariable("id") String id) {
        ApiResponse response = productServices.removeProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") String id, @RequestBody UpdateProductRequest request) {
        ApiResponse response = productServices.updateProduct(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
