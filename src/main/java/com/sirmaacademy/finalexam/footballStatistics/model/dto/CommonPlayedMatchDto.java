package com.sirmaacademy.finalexam.footballStatistics.model.dto;


public class CommonPlayedMatchDto {

    private Long matchId;
    private Integer playersTimePlayedTogether;
    private Integer matchTotalDuration;

    public CommonPlayedMatchDto(Long matchId, Integer playersTimePlayedTogether, Integer matchTotalDuration) {
        this.matchId = matchId;
        this.playersTimePlayedTogether = playersTimePlayedTogether;
        this.matchTotalDuration = matchTotalDuration;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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
