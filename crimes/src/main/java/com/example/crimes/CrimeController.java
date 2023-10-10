package com.example.crimes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crimes")
public class CrimeController {
    @Autowired
    private CrimeService crimeService;

    @GetMapping
    public ResponseEntity<List<Crime>> getAllCrimes() {
        return new ResponseEntity<List<Crime>>(crimeService.allCrimes(),
                HttpStatus.OK);
    }
    // public ResponseEntity<String> getAllCrimes() {
    // return new ResponseEntity<String>("All crimes!", HttpStatus.OK);
    // }
}
