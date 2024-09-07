package com.sirmaacademy.finalexam.footballStatistics.utils;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Team;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ReadCSV {
    private static final String TEAMS_CSV_FILE = "src/main/resources/input_data_csv/teams.csv";
    private static final Logger logger = LoggerFactory.getLogger(ReadCSV.class);

    public static List<Team> getTeamsList() {

        List<Team> teams = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TEAMS_CSV_FILE))) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {

                String[] data = line.split(",");

                long id = Long.parseLong(data[0]);
                String teamName = data[1];
                String managerFullName = data[2];
                String group = data[3];

                Team team = new Team(id, teamName, managerFullName, FootballGroup.valueOf(group));

                teams.add(team);

                line = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            logger.warn("warning: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("error: {}", e.getMessage());
        }

        return teams;

    }

}
