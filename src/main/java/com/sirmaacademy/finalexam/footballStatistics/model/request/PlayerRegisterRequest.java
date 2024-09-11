package com.sirmaacademy.finalexam.footballStatistics.model.request;

public class PlayerRegisterRequest {

    private int teamNumber;
    private String position;
    private String fullName;
    private String teamName;

    public PlayerRegisterRequest() {
    }

    public PlayerRegisterRequest(int teamNumber, String position, String fullName, String teamName) {
        this.teamNumber = teamNumber;
        this.position = position;
        this.fullName = fullName;
        this.teamName = teamName;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
