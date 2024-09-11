package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.*;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.MatchCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.PlayerCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.RecordCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.dto.TeamCsvDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.*;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;
import com.sirmaacademy.finalexam.footballStatistics.repository.CsvToDatabaseRepository;
import com.sirmaacademy.finalexam.footballStatistics.service.PlayerService;
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

/**
 * Main class responsible for final csv data validation before objects being persisted in database.
 */
@Service
public class CsvToDatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(CsvToDatabaseService.class);

    private final ReadCSV readCSV;
    private final CsvToDatabaseRepository csvToDatabaseRepository;
    private final TeamService teamService;
    private final ScoreService scoreService;
    private final PlayerService playerService;
    private final MatchService matchService;

    @Autowired
    public CsvToDatabaseService(ReadCSV readCSV, CsvToDatabaseRepository csvToDatabaseRepository,
                                TeamService teamService, ScoreService scoreService,
                                PlayerService playerService, MatchService matchService) {
        this.readCSV = readCSV;
        this.csvToDatabaseRepository = csvToDatabaseRepository;
        this.teamService = teamService;
        this.scoreService = scoreService;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    /**
     * Final validation for data from records.csv file.
     * Persist all valid data in database.
     * If some data is invalid it is logged via Logger and method continues with validation of next data object.
     */
    public void persistRecordsToDatabase() {
        List<Records> recordsList = new ArrayList<>();

        for (RecordCsvDto r : this.readCSV.extractRecordsData()) {

            try {
                Long id = ValidateCsvDto.validateId(r.getId());
                Long playerId = ValidateCsvDto.validateId(r.getPlayerId());
                Long matchId = ValidateCsvDto.validateId(r.getMatchId());

                if (!this.playerService.isPlayerExist(playerId)) {
                    throw new InvalidIdException("Player with id: '" + playerId
                            + "' does not exist.");
                } else if (!this.matchService.isMatchExist(matchId)) {
                    throw new InvalidIdException("Match with id: '" + matchId
                            + "' does not exist.");
                }
                Integer fromMinutes = ValidateCsvDto.validateMinutes(r.getFromMinutes());
                Integer toMinutes = ValidateCsvDto.validateMinutes(r.getToMinutes());

                Records record = new Records(id, playerId, matchId, fromMinutes, toMinutes);
                recordsList.add(record);

            } catch (InvalidIdException
                    | InvalidMatchDurationException e) {
                logger.warn("Warning in records.csv data: {}", e.getMessage());
            }

        }
        this.csvToDatabaseRepository.saveRecords(recordsList);
    }

    /**
     * I know it is not recommended. Method should do only one operation.
     * But all data comes from matches.csv file and according to my project structure
     * this is the optimal decision I make, because I read only once the file data.
     * Final validation for data from matches.csv file.
     * Persist all valid data in database.
     * If some data is invalid it is logged via Logger and method continues with validation of next data object.
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
                    throw new InvalidIdException("Team with id: '" + aTeamId + "' does not exist.");
                } else if (!this.teamService.isTeamExist(bTeamId)) {
                    throw new InvalidIdException("Team with id: '" + bTeamId + "' does not exist.");
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

            } catch(InvalidIdException
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

    /**
     * Final validation for data from players.csv file.
     * Persist all valid data in database.
     * If some data is invalid it is logged via Logger and method continues with validation of next data object.
     */
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
                    throw new InvalidIdException("Team with id: '" + teamId + "' does not exist.");
                }

            } catch (InvalidIdException
                     | InvalidSymbolException
                     | InvalidLengthException
                     | InvalidFootballGroupException
                     | InvalidPlayerTeamNumberException e) {
                logger.warn("Players data Warn: {}", e.getMessage());
            }

        }
        this.csvToDatabaseRepository.savePlayers(players);
    }

    /**
     * Final validation for data from teams.csv file.
     * Persist all valid data in database.
     * If some data is invalid it is logged via Logger and method continues with validation of next data object.
     */
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
