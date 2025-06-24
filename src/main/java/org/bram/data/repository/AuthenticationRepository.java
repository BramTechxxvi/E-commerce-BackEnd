package org.bram.data.repository;

import org.bram.dtos.response.LoginResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends MongoRepository<LoginResponse, String> {
}
