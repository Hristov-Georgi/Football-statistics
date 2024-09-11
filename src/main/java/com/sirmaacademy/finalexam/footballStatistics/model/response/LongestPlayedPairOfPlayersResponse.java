package com.sirmaacademy.finalexam.footballStatistics.model.response;

import com.sirmaacademy.finalexam.footballStatistics.model.dto.CommonPlayedMatchDto;

import java.util.List;

public class LongestPlayedPairOfPlayersResponse {

    private List<PlayerDtoResponse> playersDtoResponse;
    private Integer totalTimePlayedTogether;
    private List<CommonPlayedMatchDto> commonPlayedMatches;

    public LongestPlayedPairOfPlayersResponse(List<PlayerDtoResponse> playersDtoResponse, Integer totalTimePlayedTogether,
                                              List<CommonPlayedMatchDto> commonPlayedMatches) {
        this.playersDtoResponse = playersDtoResponse;
        this.totalTimePlayedTogether = totalTimePlayedTogether;
        this.commonPlayedMatches = commonPlayedMatches;
    }

    public List<PlayerDtoResponse> getPlayersDtoResponse() {
        return playersDtoResponse;
    }

    public void setPlayersDtoResponse(List<PlayerDtoResponse> playersDtoResponse) {
        this.playersDtoResponse = playersDtoResponse;
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
