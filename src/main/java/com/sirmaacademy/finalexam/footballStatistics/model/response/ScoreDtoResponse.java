package com.sirmaacademy.finalexam.footballStatistics.model.response;

public class ScoreDtoResponse {

    private TeamDtoResponse teamDtoResponse;
    private String scoredGoals;

    public ScoreDtoResponse(TeamDtoResponse teamDtoResponse, String scoredGoals) {
        this.teamDtoResponse = teamDtoResponse;
        this.scoredGoals = scoredGoals;
    }

    public TeamDtoResponse getTeamDtoResponse() {
        return teamDtoResponse;
    }

    public void setTeamDtoResponse(TeamDtoResponse teamDtoResponse) {
        this.teamDtoResponse = teamDtoResponse;
    }

    public String getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(String scoredGoals) {
        this.scoredGoals = scoredGoals;
    }
}
