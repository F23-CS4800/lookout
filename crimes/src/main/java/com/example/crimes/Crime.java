package com.example.crimes;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "crimes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crime {
    @Id
    private ObjectId id;
    private String crime;
    private String caseNumber;
    private String reportedDate;
    private String occurredDate;
    private String location;
    private String disposition;
    private String onCampus;
}
