package org.bram.data.repository;

import org.bram.data.models.Product;
import org.bram.data.models.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByProductNameAndSeller(String productName, Seller seller);
}
