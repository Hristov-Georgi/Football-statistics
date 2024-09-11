package com.sirmaacademy.finalexam.footballStatistics.model.dto;

import com.sirmaacademy.finalexam.footballStatistics.model.response.MatchDtoResponse;

public class CommonPlayedMatchDto {

    private MatchDtoResponse matchDtoResponse;
    private Integer playersTimePlayedTogether;
    private Integer matchTotalDuration;

    public CommonPlayedMatchDto(MatchDtoResponse matchDtoResponse, Integer playersTimePlayedTogether, Integer matchTotalDuration) {
        this.matchDtoResponse = matchDtoResponse;
        this.playersTimePlayedTogether = playersTimePlayedTogether;
        this.matchTotalDuration = matchTotalDuration;
    }

    public MatchDtoResponse getMatchDtoResponse() {
        return matchDtoResponse;
    }

    public void setMatchDtoResponse(MatchDtoResponse matchDtoResponse) {
        this.matchDtoResponse = matchDtoResponse;
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
