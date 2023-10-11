package com.example.crimes;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrimeService {
    @Autowired
    private CrimeRepository crimeRepository;

    public List<Crime> allCrimes() {
        return crimeRepository.findAll();
    }

    // public Optional<Crime> singleCrime(ObjectId id) {
    // return crimeRepository.findById(id);
    // }
    public Optional<Crime> crimeCaseNumber(String caseNumber) {
        return crimeRepository.findCrimeByCaseNumber(caseNumber);
    }
}
