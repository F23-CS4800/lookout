package com.example.crimes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrimeService {
    @Autowired
    private CrimeRepository crimeRepository;

    public List<Crime> allCrimes() {
        return crimeRepository.findAll();
    }
}
