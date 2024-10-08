package com.sirmaacademy.finalexam.footballStatistics.controller;

import com.sirmaacademy.finalexam.footballStatistics.model.response.LongestPlayedPairOfPlayersResponse;
import com.sirmaacademy.finalexam.footballStatistics.service.logic.LongestPlayedPairOfPlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class PlayerStatisticsController {

    private final LongestPlayedPairOfPlayersService longestPlayedPairOfPlayersService;

    @Autowired
    public PlayerStatisticsController(LongestPlayedPairOfPlayersService longestPlayedPairOfPlayersService) {
        this.longestPlayedPairOfPlayersService = longestPlayedPairOfPlayersService;
    }

    /**
     * Return all players who have played for the longest time together in common matches, and
     * matches they played together.
     */
    @GetMapping("/players/longestPlayedPairInCommonMatches")
    public ResponseEntity<List<LongestPlayedPairOfPlayersResponse>> pairOfLongestPlayedPlayersInCommonMatches() {
        return ResponseEntity.ok(longestPlayedPairOfPlayersService.findLongestPlayedPlayerPairInCommonMatches());
    }

}
