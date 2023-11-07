package com.example.crimes;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "crimes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crime {
    @Id
    private ObjectId id;
    private List<String> Nature ;  // Represents an array of 'nature'
    private List<String> caseNumber;
    private List<String> reported; // Represents an array of 'reported'
    private List<String> occurred; // Represents an array of 'occurred'
    private List<String> location;
    private List<String> disposition;
    private List<String>  onCampus;
}
