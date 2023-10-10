package com.example.crimes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crimes")
public class CrimeController {
    @GetMapping
    public ResponseEntity<String> allCrimes() {
        return new ResponseEntity<String>("All crimes!", HttpStatus.OK);
    }
}
