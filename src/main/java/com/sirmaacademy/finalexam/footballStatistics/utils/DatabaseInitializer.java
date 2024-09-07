package com.sirmaacademy.finalexam.footballStatistics.utils;

import com.sirmaacademy.finalexam.footballStatistics.repository.CSVToDatabaseInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CSVToDatabaseInsert csvToDatabaseInsert;

    @Autowired
    public DatabaseInitializer(CSVToDatabaseInsert csvToDatabaseInsert) {
        this.csvToDatabaseInsert = csvToDatabaseInsert;
    }


    @Override
    public void run(String... args) throws Exception {

       // this.csvToDatabaseInsert.insertTeamsCSVFile(ReadCSV.getTeamsList());

    }

}
