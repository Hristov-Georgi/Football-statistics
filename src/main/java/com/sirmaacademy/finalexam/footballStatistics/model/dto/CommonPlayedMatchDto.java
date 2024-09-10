package com.sirmaacademy.finalexam.footballStatistics.model.dto;


import com.sirmaacademy.finalexam.footballStatistics.model.entity.Match;

public class CommonPlayedMatchDto {

    private Match match;
    private Integer playersTimePlayedTogether;
    private Integer matchTotalDuration;

    public CommonPlayedMatchDto(Match match, Integer playersTimePlayedTogether, Integer matchTotalDuration) {
        this.match = match;
        this.playersTimePlayedTogether = playersTimePlayedTogether;
        this.matchTotalDuration = matchTotalDuration;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getPlayersTimePlayedTogether() {
        return playersTimePlayedTogether;
    }

    public void setPlayersTimePlayedTogether(Integer playersTimePlayedTogether) {
        this.playersTimePlayedTogether = playersTimePlayedTogether;
    }

    public Integer getMatchTotalDuration() {
        return matchTotalDuration;
    }

    public void setMatchTotalDuration(Integer matchTotalDuration) {
        this.matchTotalDuration = matchTotalDuration;
    }
}
