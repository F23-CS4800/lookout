package com.example.crimes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataPopulationService {

    @Autowired
    private CrimeRepository crimeRepository;

    public void populateData(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, String>> jsonData = objectMapper.readValue(new File(jsonFilePath), List.class);
            for (Map<String, String> data : jsonData) {
                Crime crime = new Crime();

                // Map JSON data to the Crime entity fields
                // For fields with multiple values, split the strings into a list
                crime.setNature(Arrays.asList(data.get("Nature").split(",")));
                crime.setCaseNumber(Arrays.asList(data.get("Case #").split(",")));
                crime.setReported(Arrays.asList(data.get("Reported").split(",")));
                crime.setOccurred(Arrays.asList(data.get("Occurred").split(",")));
                crime.setLocation(Arrays.asList(data.get("Location").split(",")));
                crime.setDisposition(Arrays.asList(data.get("Disposition").split(",")));
                crime.setOnCampus(Arrays.asList(data.get("On Campus?").split(",")));

                // Save the populated Crime object to the repository
                crimeRepository.save(crime);
                System.out.println("MongoDB populated");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
