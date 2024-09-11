package com.sirmaacademy.finalexam.footballStatistics.utils;

import com.sirmaacademy.finalexam.footballStatistics.service.logic.CsvToDatabaseService;
import com.sirmaacademy.finalexam.footballStatistics.service.logic.LongestPlayedPairOfPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Provides initial upload of all files data when program starts.
 * Be sure to comment out code after first start or error will occur if program starts for second time
 * and database is already populated.
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CsvToDatabaseService csvToDatabaseService;
    private final LongestPlayedPairOfPlayersService longestPlayedPairOfPlayersService; //TODO: delete at the end

    @Autowired
    public DatabaseInitializer(CsvToDatabaseService csvToDatabaseService, LongestPlayedPairOfPlayersService longestPlayedPairOfPlayersService) {
        this.csvToDatabaseService = csvToDatabaseService;
        this.longestPlayedPairOfPlayersService = longestPlayedPairOfPlayersService;
    }

    /**
     * Comment out the code after first start.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

//        this.csvToDatabaseService.persistTeamsToDatabase();
//        this.csvToDatabaseService.persistPlayersToDatabase();
//        this.csvToDatabaseService.persistScoresAndMatchesToDatabase();
//        this.csvToDatabaseService.persistRecordsToDatabase();


    }

}
