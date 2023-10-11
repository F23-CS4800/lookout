package com.example.crimes;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeRepository extends MongoRepository<Crime, ObjectId> {
    Optional<Crime> findCrimeByCaseNumber(String caseNumber);
}
