package com.sirmaacademy.finalexam.footballStatistics.controller;

import com.sirmaacademy.finalexam.footballStatistics.model.response.LongestPlayedPairOfPlayers;
import com.sirmaacademy.finalexam.footballStatistics.service.LongestPlayedPairOfPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class PlayerStatisticsController {

    private final LongestPlayedPairOfPlayersService longestPlayedPairOfPlayersService;

    @Autowired
    public PlayerStatisticsController(LongestPlayedPairOfPlayersService longestPlayedPairOfPlayersService) {
        this.longestPlayedPairOfPlayersService = longestPlayedPairOfPlayersService;
    }

    @GetMapping("/players/longestPlayedPairInCommonMatches")
    public ResponseEntity<List<LongestPlayedPairOfPlayers>> pairOfLongestPlayedPlayersInCommonMatches() {
        return ResponseEntity.ok(longestPlayedPairOfPlayersService.findLongestPlayedPlayerPairInCommonMatches());
    }

    //TODO: create responseDto's for data in LongestPlayedPairOfPlayers !!!!!!

}
