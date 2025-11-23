package com.flightapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "airlines")
public class Airline {

    @Id
    private String id;

    private String airlineName;
    private String airlineCode;
    private String country;
}
