package com.sirmaacademy.finalexam.footballStatistics.utils;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.MatchCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.PlayerCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.RecordCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.TeamCsvDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for reading data from csv resource files.
 * Methods are separated for every single file for better maintenance if something change.
 */
@Service
public class ReadCSV {
    private static final String TEAMS_CSV_FILE = "src/main/resources/input_data_csv/teams.csv";
    private static final String PLAYERS_CSV_FILE = "src/main/resources/input_data_csv/players.csv";
    private static final String RECORDS_CSV_FILE = "src/main/resources/input_data_csv/records.csv";
    private static final String MATCHES_CSV_FILE = "src/main/resources/input_data_csv/matches.csv";

    private static final Integer MATCH_STANDARD_DURATION_MINUTES = 90;

    private static final Logger logger = LoggerFactory.getLogger(ReadCSV.class);

    /**
     * Extract all data from records.csv file.
     * Initial validation for datatype compatibility and removes all unnecessary whitespace.
     * Exceptions are logged via Logger and the application continues execution.
     * @return
     */
    public List<RecordCsvDto> extractRecordsData() {
        List<RecordCsvDto> records = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(RECORDS_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] data = line.split(",");

                try {
                    Long id = Long.parseLong(data[0].trim());
                    Long playerId = Long.parseLong(data[1].trim());
                    Long matchId = Long.parseLong(data[2].trim());
                    Integer fromMinutes = Integer.parseInt(data[3].trim());
                    Integer toMinutes;

                    if (data[4].equalsIgnoreCase("NULL") || data[4].isBlank() ) {
                        toMinutes = MATCH_STANDARD_DURATION_MINUTES;
                    } else {
                        toMinutes = Integer.parseInt(data[4].trim());
                    }

                    RecordCsvDto recordCsvDto = new RecordCsvDto(id, playerId, matchId, fromMinutes, toMinutes);
                    records.add(recordCsvDto);

                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid data has occurred. Check records.csv file. Warn: {}", e.getMessage());
                } catch (Exception e) {
                    logger.warn("Warning in records.csv data: {}", e.getMessage());
                }
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("Records.csv file warning: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Records.csv file error: {}", e.getMessage());
        }
        return records;
    }

    public List<MatchCsvDto> extractMatchesData() {
        List<MatchCsvDto> matches = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(MATCHES_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] data = line.split(",");

                try {
                    Long id = Long.parseLong(data[0].trim());
                    Long aTeamId = Long.parseLong(data[1].trim());
                    Long bTeamId = Long.parseLong(data[2].trim());
                    String dateString = data[3].trim(); //TODO: regex to match all date formats
                    String scoreString = data[4].trim();

                    MatchCsvDto matchCsvDto = new MatchCsvDto(id, aTeamId, bTeamId, dateString, scoreString);
                    matches.add(matchCsvDto);

                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid data has occurred. Check matches.csv file. Warn: {}", e.getMessage());
                } catch (Exception e) {
                    logger.warn("Warning in matches.csv data: {}", e.getMessage());
                }
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("Matches.csv file warning: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Matches.csv file error: {}", e.getMessage());
        }
        return matches;
    }

    public List<PlayerCsvDto> extractPlayersData() {
        List<PlayerCsvDto> players = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PLAYERS_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] data = line.split(",");

                try {
                    long id = Long.parseLong(data[0].trim());
                    int teamNumber = Integer.parseInt(data[1].trim());
                    String position = data[2].trim();
                    String fullName = data[3].trim();
                    Long teamId = Long.parseLong(data[4].trim());

                    PlayerCsvDto playerCsvDto = new PlayerCsvDto(id, teamNumber, position, fullName, teamId);
                    players.add(playerCsvDto);

                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid data  occurred. Check players.csv file. Warn: {}", e.getMessage());
                } catch (Exception e) {
                    logger.warn("Warning in players.csv data: {}", e.getMessage());
                }
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("Players.csv file warning: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Players.csv file error: {}", e.getMessage());
        }
        return players;
    }

    public List<TeamCsvDto> extractTeamsData() {
        List<TeamCsvDto> teams = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TEAMS_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] data = line.split(",");

                try {
                    long id = Long.parseLong(data[0].trim());
                    String teamName = data[1].trim();
                    String managerFullName = data[2].trim();
                    String footballGroup = data[3].trim();

                    TeamCsvDto teamCsvDto = new TeamCsvDto(id, teamName, managerFullName, footballGroup);
                    teams.add(teamCsvDto);

                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid data  occurred. Check teams.csv file. Warn: {}", e.getMessage());
                } catch (Exception e) {
                    logger.warn("Warning in teams.csv data: {}", e.getMessage());
                }
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("Teams.csv file warning: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Teams.csv file error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return teams;
    }

}
