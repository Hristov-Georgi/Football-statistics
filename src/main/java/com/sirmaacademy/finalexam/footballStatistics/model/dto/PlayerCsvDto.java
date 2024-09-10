package com.sirmaacademy.finalexam.footballStatistics.model.dto;

/**
 * Use this DTO to match and transfer data from players.csv file format to the application entity.
 */
public class PlayerCsvDto {

    private Long id;
    private int teamNumber;
    private String position;
    private String fullName;
    private Long teamId;

    public PlayerCsvDto(Long id, int teamNumber, String position, String fullName, Long teamId) {
        this.id = id;
        this.teamNumber = teamNumber;
        this.position = position;
        this.fullName = fullName;
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
