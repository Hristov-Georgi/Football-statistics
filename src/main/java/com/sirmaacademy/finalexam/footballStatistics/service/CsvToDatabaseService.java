package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.*;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.MatchCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.PlayerCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.TeamCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Match;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Score;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Team;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;
import com.sirmaacademy.finalexam.footballStatistics.repository.CsvToDatabaseRepository;
import com.sirmaacademy.finalexam.footballStatistics.utils.DateFormatter;
import com.sirmaacademy.finalexam.footballStatistics.utils.ReadCSV;
import com.sirmaacademy.finalexam.footballStatistics.validation.ValidateCsvDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvToDatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(CsvToDatabaseService.class);

    private final ReadCSV readCSV;
    private final CsvToDatabaseRepository csvToDatabaseRepository;
    private final TeamService teamService;
    private final ScoreService scoreService;

    @Autowired
    public CsvToDatabaseService(ReadCSV readCSV, CsvToDatabaseRepository csvToDatabaseRepository, TeamService teamService, ScoreService scoreService) {
        this.readCSV = readCSV;
        this.csvToDatabaseRepository = csvToDatabaseRepository;
        this.teamService = teamService;
        this.scoreService = scoreService;
    }

    /**
     * I know it is not recommended. Method should do only one operation.
     * But all data comes from matches.csv file and according to my project structure
     * this is the optimal decision I make, because I read only once the file data.
     */
    public void persistScoresAndMatchesToDatabase() {
        List<Match> matches = new ArrayList<>();
        List<Score> scores = new ArrayList<>();

        for (MatchCsvDto m : this.readCSV.extractMatchesData()) {

            try {

                Long id = ValidateCsvDto.validateId(m.getId());
                Long aTeamId = ValidateCsvDto.validateId(m.getaTeamId());
                Long bTeamId = ValidateCsvDto.validateId(m.getbTeamId());

                if (!this.teamService.isTeamExist(aTeamId)) {
                    throw new InvalidTeamException("Team with id: '" + aTeamId + "' does not exist.");
                } else if (!this.teamService.isTeamExist(bTeamId)) {
                    throw new InvalidTeamException("Team with id: '" + bTeamId + "' does not exist.");
                }
                LocalDate localDate = DateFormatter.formatDate(m.getDate());
                String aTeamScore = ValidateCsvDto.validateGoalsInput(m.getScore(), "aTeamScore");
                String bTeamScore = ValidateCsvDto.validateGoalsInput(m.getScore(), "bTeamScore");

                Match match = new Match(id, localDate);
                matches.add(match);

                Score aTeam = new Score(teamService.findById(aTeamId), aTeamScore, match);
                Score bTeam = new Score(teamService.findById(bTeamId), bTeamScore, match);
                scores.add(aTeam);
                scores.add(bTeam);

            } catch(InvalidTeamException
                    | InvalidIdException
                    | DateTimeParseException
                    | InvalidMatchScoreException e) {
                logger.warn("Warning in matches.csv data: {}", e.getMessage());

            } catch (IllegalStateException | IllegalArgumentException e) {
                logger.warn("Something went wrong with regex pattern for match score validation. Warn: {}", e.getMessage());
            }

        }
        this.csvToDatabaseRepository.saveMatches(matches);
        this.scoreService.saveAllScores(scores);
    }

    public void persistPlayersToDatabase() {
        List<Player> players = new ArrayList<>();

        for (PlayerCsvDto p : this.readCSV.extractPlayersData()) {

            try {
                Long id = ValidateCsvDto.validateId(p.getId());
                int teamNumber = ValidateCsvDto.validateTeamNumber(p.getTeamNumber());
                FieldPosition fieldPosition = ValidateCsvDto.validateFieldPosition(p.getPosition());
                String fullName = ValidateCsvDto.validatePersonFullName(p.getFullName());
                Long teamId = ValidateCsvDto.validateId(p.getTeamId());

                if (this.teamService.isTeamExist(teamId)) {
                    Player player = new Player(id, teamNumber, fieldPosition, fullName, teamId);
                    players.add(player);
                } else {
                    throw new InvalidTeamException("Team with id: '" + teamId + "' does not exist.");
                }

            } catch (InvalidIdException
                     | InvalidSymbolException
                     | InvalidLengthException
                     | InvalidFootballGroupException
                     | InvalidPlayerTeamNumberException
                     | InvalidTeamException e) {
                logger.warn("Players data Warn: {}", e.getMessage());
            }

        }
        this.csvToDatabaseRepository.savePlayers(players);
    }

    public void persistTeamsToDatabase() {
        List<Team> teams = new ArrayList<>();

        for (TeamCsvDto t : this.readCSV.extractTeamsData()) {

            try {
                Long id = ValidateCsvDto.validateId(t.getId());
                String name = ValidateCsvDto.validateTeamName(t.getName());
                String managerFullName = ValidateCsvDto.validatePersonFullName(t.getManagerFullName());
                FootballGroup footballGroup = ValidateCsvDto.validateFootballGroup(t.getFootballGroup());

                Team team = new Team(id, name, managerFullName, footballGroup);
                teams.add(team);
            } catch (InvalidIdException
                     | InvalidSymbolException
                     | InvalidLengthException
                     | InvalidFootballGroupException e) {
                logger.warn("Teams data Warn: {}", e.getMessage());
            }

        }
        this.csvToDatabaseRepository.saveTeams(teams);
    }

}
