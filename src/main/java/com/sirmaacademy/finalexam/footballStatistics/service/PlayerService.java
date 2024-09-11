package com.sirmaacademy.finalexam.footballStatistics.service;

import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;
import com.sirmaacademy.finalexam.footballStatistics.model.request.PlayerRegisterRequest;
import com.sirmaacademy.finalexam.footballStatistics.model.response.PlayerDtoResponse;

import java.util.List;

public interface PlayerService {

    boolean isPlayerExist(Long id);
    Player getById(Long id);
    List<PlayerDtoResponse> getByName(String name);
    Player addNewPlayer(PlayerRegisterRequest playerRequest);
}
