package com.example.crimes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DataPopulationService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CrimeRepository crimeRepository;

    public void clearDatabase() {
        // Drop specific collection
        mongoTemplate.dropCollection("crimes");
    }

    public void populateData(String jsonFilePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, String>> jsonData = objectMapper.readValue(new File(jsonFilePath), List.class);
            for (Map<String, String> data : jsonData) {
                Crime crime = new Crime();

                // Map JSON data to the Crime entity fields
                // Add null checks before splitting strings
                if (data.get("Nature") != null)
                    crime.setNature(Arrays.asList(data.get("Nature").split(",")));
                if (data.get("Case #") != null)
                    crime.setCaseNumber(Arrays.asList(data.get("Case #").split(",")));
                if (data.get("Reported") != null)
                    crime.setReported(Arrays.asList(data.get("Reported").split(",")));
                if (data.get("Occurred") != null)
                    crime.setOccurred(Arrays.asList(data.get("Occurred").split(",")));
                if (data.get("Location") != null)
                    crime.setLocation(Arrays.asList(data.get("Location").split(",")));
                if (data.get("Disposition") != null)
                    crime.setDisposition(Arrays.asList(data.get("Disposition").split(",")));
                if (data.get("On Campus?") != null)
                    crime.setOnCampus(Arrays.asList(data.get("On Campus?").split(",")));

                // Save the populated Crime object to the repository
                crimeRepository.save(crime);
            }
            System.out.println("MongoDB populated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
