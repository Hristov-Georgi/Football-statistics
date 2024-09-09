package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.repository.PlayerRepository;
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

}
