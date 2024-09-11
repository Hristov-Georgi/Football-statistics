package com.sirmaacademy.finalexam.footballStatistics.service.logic;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.PlayerNotFoundException;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.repository.PlayerRepository;
import com.sirmaacademy.finalexam.footballStatistics.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
