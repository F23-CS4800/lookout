package com.example.crimes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeRepository extends MongoRepository<Crime, ObjectId> {

}
