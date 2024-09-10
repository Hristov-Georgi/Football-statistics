package com.sirmaacademy.finalexam.footballStatistics.model.response;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.CommonPlayedMatchDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;

import java.util.List;

public class LongestPlayedPairOfPlayers {

    private List<Player> players;
    private Integer totalTimePlayedTogether;
    private List<CommonPlayedMatchDto> commonPlayedMatchDtos;
}
