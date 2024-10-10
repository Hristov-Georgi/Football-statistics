package com.sirmaacademy.finalexam.footballStatistics.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class PlayerUpdateRequest {

    @NotBlank
    @Length(min = 4, max = 100)
    private String fullName;

    @NotNull
    @Range(max = 99)
    private Integer teamNumber;

    @NotBlank
    @Length(min = 2)
    private String position;

    @NotBlank
    @Length(min = 4, max = 100)
    private String teamName;

    private Integer newTeamNumber;

    private String newPosition;

    private String newTeamName;

    public PlayerUpdateRequest() {
    }

    public PlayerUpdateRequest(String fullName, Integer teamNumber, String position,
                               String teamName, Integer newTeamNumber, String newPosition,
                               String newTeamName) {
        this.fullName = fullName;
        this.teamNumber = teamNumber;
        this.position = position;
        this.teamName = teamName;
        this.newTeamNumber = newTeamNumber;
        this.newPosition = newPosition;
        this.newTeamName = newTeamName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getNewTeamNumber() {
        return newTeamNumber;
    }

    public void setNewTeamNumber(Integer newTeamNumber) {
        this.newTeamNumber = newTeamNumber;
    }

    public String getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }

    public String getNewTeamName() {
        return newTeamName;
    }

    public void setNewTeamName(String newTeamName) {
        this.newTeamName = newTeamName;
    }

}
