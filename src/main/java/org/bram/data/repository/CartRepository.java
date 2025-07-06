package org.bram.data.repository;

import org.bram.data.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<ShoppingCart, String> {
}
