package com.sirmaacademy.finalexam.footballStatistics.utils;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.MatchCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.PlayerCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.RecordCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.TeamCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Match;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReadCSV {
    private static final String TEAMS_CSV_FILE = "src/main/resources/input_data_csv/teams.csv";
    private static final String PLAYERS_CSV_FILE = "src/main/resources/input_data_csv/players.csv";
    private static final String RECORDS_CSV_FILE = "src/main/resources/input_data_csv/records.csv";
    private static final String MATCHES_CSV_FILE = "src/main/resources/input_data_csv/matches.csv";

    private static final Logger logger = LoggerFactory.getLogger(ReadCSV.class);

    public List<RecordCsvDto> extractRecordsData() {
        return null;
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
