package com.sirmaacademy.finalexam.footballStatistics.utils;

import com.sirmaacademy.finalexam.footballStatistics.service.CsvToDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CsvToDatabaseService csvToDatabaseService;

    @Autowired
    public DatabaseInitializer(CsvToDatabaseService csvToDatabaseService) {
        this.csvToDatabaseService = csvToDatabaseService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.csvToDatabaseService.persistTeamsToDatabase();
        this.csvToDatabaseService.persistPlayersToDatabase();
        this.csvToDatabaseService.persistScoresAndMatchesToDatabase();
        this.csvToDatabaseService.persistRecordsToDatabase();

    }

}
