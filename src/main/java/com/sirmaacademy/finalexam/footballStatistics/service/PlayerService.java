package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.PlayerNotFoundException;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.repository.PlayerRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public boolean isPlayerExist(Long id) {
        return this.playerRepository.existsById(id);
    }

    public Player getById(Long id){
        return this.playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: '" + id + "' not found."));
    }

}
