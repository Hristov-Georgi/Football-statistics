package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.PlayerNotFoundException;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.model.response.PlayerDtoResponse;
import com.sirmaacademy.finalexam.footballStatistics.model.response.TeamDtoResponse;
import com.sirmaacademy.finalexam.footballStatistics.repository.PlayerRepository;
import com.sirmaacademy.finalexam.footballStatistics.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceLogic implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceLogic(PlayerRepository playerRepository) {
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
}
