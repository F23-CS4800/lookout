package com.example.crimes;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crimes")
public class CrimeController {
    @Autowired
    private CrimeService crimeService;
    //@Autowired
   // private CrimeScrapingService scrapingService;


    @GetMapping
    public ResponseEntity<List<Crime>> getAllCrimes() {
        return new ResponseEntity<List<Crime>>(crimeService.allCrimes(),
                HttpStatus.OK);
    }
    // public ResponseEntity<String> getAllCrimes() {
    // return new ResponseEntity<String>("All crimes!", HttpStatus.OK);
    // }
//    @GetMapping("/scrape")
//    public List<Crime> scrapeCrimes() {
//        return scrapingService.scrapeCrimes();
//    }
    @GetMapping("/{caseNumber}")
    // public ResponseEntity<Optional<Crime>> getSingleCrime(@PathVariable ObjectId
    // id) {
    // return new ResponseEntity<Optional<Crime>>(crimeService.singleCrime(id),
    // HttpStatus.OK);
    // }
    public ResponseEntity<Optional<Crime>> getCrimeCaseNumber(@PathVariable String caseNumber) {
        return new ResponseEntity<Optional<Crime>>(crimeService.crimeCaseNumber(caseNumber), HttpStatus.OK);
    }
}
