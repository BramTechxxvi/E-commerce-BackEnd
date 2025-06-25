package org.bram.data.repository;

import org.bram.data.models.Seller;
import org.bram.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends MongoRepository<Seller, String> {

    Optional<Seller> findByEmail(String email);

    Optional<Seller>findByUser(User user);
}
