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
        List<Match> matches = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(MATCHES_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] data = line.split(",");

                Long id = Long.parseLong(data[0].trim());
                Long aTeamId = Long.parseLong(data[1].trim());
                Long bTeamId = Long.parseLong(data[2].trim());
                String dateString = data[3].trim();
                String scoreString = data[4].trim();

                //TODO: implement class MatchesFromCSV
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("Warning in match.csv file: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Error in match.csv file: {}", e.getMessage());
        }
        return null;

    }

    public List<PlayerCsvDto> extractPlayersData() {
        List<Player> players = new ArrayList<>(); // TODO: handle database exception if ids are equal

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PLAYERS_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] data = line.split(",");
//TODO: try-catch for parsing exception and log the error.
                long id = Long.parseLong(data[0].trim());
                int teamNumber = Integer.parseInt(data[1].trim());
                String position = data[2].trim();
                String fullName = data[3].trim();
                Long teamId = Long.parseLong(data[4].trim());

                //TODO
                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("MY CUSTOM warning: {}", e.getMessage());
        } catch (NoSuchElementException e) {
            logger.info("info: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Error in players.csv file: {}", e.getMessage());
        }
        return null;
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

                } catch (NumberFormatException e) {
                    logger.warn("Invalid data  occurred. Check teams.csv file. Warn: {}", e.getMessage());
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
