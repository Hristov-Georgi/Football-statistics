package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.InvalidFieldPositionException;
import com.sirmaacademy.finalexam.footballStatistics.exceptions.InvalidTeamException;
import com.sirmaacademy.finalexam.footballStatistics.exceptions.PlayerNotFoundException;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Team;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerDeleteRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerRegisterRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerUpdateRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.response.PlayerDtoResponse;
import com.sirmaacademy.finalexam.footballStatistics.model.response.TeamDtoResponse;
import com.sirmaacademy.finalexam.footballStatistics.repository.PlayerRepository;
import com.sirmaacademy.finalexam.footballStatistics.repository.TeamRepository;
import com.sirmaacademy.finalexam.footballStatistics.service.PlayerService;
import com.sirmaacademy.finalexam.footballStatistics.validation.ValidateCsvDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceLogic implements PlayerService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceLogic(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public boolean isPlayerExist(Long id) {
        return this.playerRepository.existsById(id);
    }

    @Override
    public Player getById(Long id){
        return this.playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: '" + id + "' not found."));
    }

    @Override
    public List<PlayerDtoResponse> getByName(String name) {
       List<Player> playersList = this.playerRepository.findByFullNameContainingIgnoreCase(name)
               .orElseThrow(() -> new PlayerNotFoundException("Players with name: '" + name + "' were not found"));

        List<PlayerDtoResponse> responseList = new ArrayList<>();

        for (Player p : playersList) {
            TeamDtoResponse teamDtoResponse = new TeamDtoResponse(p.getTeam().getName(),
                    p.getTeam().getManagerFullName(), p.getTeam().getFootballGroup());

            responseList.add(new PlayerDtoResponse(p.getTeamNumber(), p.getFieldPosition(),
                    p.getFullName(),teamDtoResponse));
        }

        if (responseList.isEmpty()) {
            throw new PlayerNotFoundException("Players with name: '" + name + "' were not found");
        }
        return responseList;
    }

    @Override
    public Player addNewPlayer(PlayerRegisterRequest playerRequest) {

        FieldPosition position = extractFieldPosition(playerRequest.getPosition());
        Team team = extractTeam(playerRequest.getTeamName());

        if (this.playerRepository.existsByFullNameIgnoreCase(playerRequest.getFullName())
                && team.getName().equalsIgnoreCase(playerRequest.getTeamName())) {
            throw new IllegalArgumentException("Player: '" + playerRequest.getFullName()
                    + "' already exist in team: '" + playerRequest.getTeamName() + "'.");
        }
        return this.playerRepository
                .save(new Player(playerRequest.getTeamNumber(), position, playerRequest.getFullName(), team));
    }

    @Override
    public Player updatePlayer(PlayerUpdateRequest playerData) {

        if (playerData.getNewTeamNumber() == null || Objects.equals(playerData.getNewTeamNumber(), playerData.getTeamNumber())
            && playerData.getNewPosition() == null || playerData.getNewPosition().equalsIgnoreCase(playerData.getPosition())
            && playerData.getNewTeamName() == null || playerData.getNewTeamName().equalsIgnoreCase(playerData.getTeamName())) {

            throw new NullPointerException("Invalid update data.");
        }

        Player player = validatePlayerForUpdate(playerData);

        if (playerData.getNewPosition() != null) {
            FieldPosition position = extractFieldPosition(playerData.getNewPosition());
            player.setFieldPosition(position);
        }

        if (playerData.getNewTeamName() != null) {
            Team team = extractTeam(playerData.getNewTeamName());
            player.setTeam(team);
        }

        if (playerData.getNewTeamNumber() != null && player.getTeamNumber() != playerData.getNewTeamNumber()) {
            int teamNumber = ValidateCsvDto.validateTeamNumber(playerData.getNewTeamNumber());
            player.setTeamNumber(teamNumber);
        }
        return this.playerRepository.save(player);

    }

    @Override
    public void deletePlayer(PlayerDeleteRequest playerData) {
        Player player = validatePlayerForDelete(playerData);

        this.playerRepository.delete(player);
    }

    private Player validatePlayerForDelete(PlayerDeleteRequest playerData) {
        List<Player> players = this.playerRepository.findByFullNameContainingIgnoreCase(playerData.getFullName())
                .orElseThrow(() -> new PlayerNotFoundException("Player: '" + playerData.getFullName() + "' not  found."));

        Player forDelete = null;

        FieldPosition oldPosition = extractFieldPosition(playerData.getPosition());
        Team oldTeam  = extractTeam(playerData.getTeamName());

        for (Player p : players) {

            if (p.getFieldPosition().equals(oldPosition)
                    && p.getTeam().equals(oldTeam)
                    && p.getTeamNumber() == playerData.getTeamNumber()
                    && p.getFullName().equalsIgnoreCase(playerData.getFullName())) {

                forDelete = p;
            }
            break;
        }
        return forDelete;

    }

    private Player validatePlayerForUpdate(PlayerUpdateRequest  playerData) {
        List<Player> players = this.playerRepository.findByFullNameContainingIgnoreCase(playerData.getFullName())
                .orElseThrow(() -> new PlayerNotFoundException("Player: '" + playerData.getFullName() + "' not  found."));

        Player forUpdate = null;

        FieldPosition oldPosition = extractFieldPosition(playerData.getPosition());
        Team oldTeam  = extractTeam(playerData.getTeamName());

        for (Player p : players) {

            if (p.getFieldPosition().equals(oldPosition)
                    && p.getTeam().equals(oldTeam)
                    && p.getTeamNumber() == playerData.getTeamNumber()
                    && p.getFullName().equalsIgnoreCase(playerData.getFullName())) {

                forUpdate = p;
            }
            break;
        }
        return forUpdate;

    }

    private FieldPosition extractFieldPosition(String position) {

        for (FieldPosition p : FieldPosition.values()) {

            if (p.name().equals(position) || p.getValue().equals(position)) {
                return p;
            }

        }
        throw new InvalidFieldPositionException("Invalid field position: '" +
                position + "'.");
    }

    private Team extractTeam(String teamName) {
        return this.teamRepository.findByNameIgnoreCase(teamName)
                .orElseThrow(() -> new InvalidTeamException("Team: '" + teamName + "' does not exist."));
    }

}
