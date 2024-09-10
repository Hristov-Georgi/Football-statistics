package com.sirmaacademy.finalexam.footballStatistics.model.response;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.CommonPlayedMatchDto;
import com.sirmaacademy.finalexam.footballStatistics.model.entity.Player;

import java.util.List;

public class LongestPlayedPairOfPlayers {

    private List<Player> players;
    private Integer totalTimePlayedTogether;
    private List<CommonPlayedMatchDto> commonPlayedMatches;

    public LongestPlayedPairOfPlayers(List<Player> players, Integer totalTimePlayedTogether,
                                      List<CommonPlayedMatchDto> commonPlayedMatches) {
        this.players = players;
        this.totalTimePlayedTogether = totalTimePlayedTogether;
        this.commonPlayedMatches = commonPlayedMatches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getTotalTimePlayedTogether() {
        return totalTimePlayedTogether;
    }

    public void setTotalTimePlayedTogether(Integer totalTimePlayedTogether) {
        this.totalTimePlayedTogether = totalTimePlayedTogether;
    }

    public List<CommonPlayedMatchDto> getCommonPlayedMatches() {
        return commonPlayedMatches;
    }

    public void setCommonPlayedMatches(List<CommonPlayedMatchDto> commonPlayedMatches) {
        this.commonPlayedMatches = commonPlayedMatches;
    }
}
