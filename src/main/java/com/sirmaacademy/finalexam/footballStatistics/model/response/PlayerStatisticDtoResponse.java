package com.sirmaacademy.finalexam.footballStatistics.model.response;

import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;

public class PlayerStatisticDtoResponse {

    private int teamNumber;
    private FieldPosition fieldPosition;
    private String fullName;

    public PlayerStatisticDtoResponse(int teamNumber, FieldPosition fieldPosition, String fullName) {
        this.teamNumber = teamNumber;
        this.fieldPosition = fieldPosition;
        this.fullName = fullName;
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
}
