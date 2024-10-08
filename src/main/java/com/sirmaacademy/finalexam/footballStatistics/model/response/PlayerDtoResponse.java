package com.sirmaacademy.finalexam.footballStatistics.model.response;

import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;

public class PlayerDtoResponse {

    private int teamNumber;
    private FieldPosition fieldPosition;
    private String fullName;
    private TeamDtoResponse teamDtoResponse;

    public PlayerDtoResponse() {
    }

    public PlayerDtoResponse(int teamNumber, FieldPosition fieldPosition, String fullName,
                             TeamDtoResponse teamDtoResponse) {
        this.teamNumber = teamNumber;
        this.fieldPosition = fieldPosition;
        this.fullName = fullName;
        this.teamDtoResponse = teamDtoResponse;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(FieldPosition fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public TeamDtoResponse getTeamDtoResponse() {
        return teamDtoResponse;
    }

    public void setTeamDtoResponse(TeamDtoResponse teamDtoResponse) {
        this.teamDtoResponse = teamDtoResponse;
    }

}
