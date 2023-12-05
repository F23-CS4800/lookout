package com.example.crimes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/crimes")
public class CrimeController {
    @Autowired
    private CrimeService crimeService;

    @Autowired
    private CrimeRepository crimeRepository;
    @GetMapping
    public ResponseEntity<List<Crime>> getAllCrimes() {
        return new ResponseEntity<List<Crime>>(crimeService.allCrimes(),
            HttpStatus.OK);
    }
    @GetMapping("/{caseNumber}")

    public ResponseEntity<Optional<Crime>> getCrimeCaseNumber(@PathVariable String caseNumber) {
        return new ResponseEntity<Optional<Crime>>(crimeService.crimeCaseNumber(caseNumber), HttpStatus.OK);
    }
}
