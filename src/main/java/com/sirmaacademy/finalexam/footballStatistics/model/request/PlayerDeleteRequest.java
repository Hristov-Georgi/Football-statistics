package com.sirmaacademy.finalexam.footballStatistics.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class PlayerDeleteRequest {

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

    public PlayerDeleteRequest() {
    }

    public PlayerDeleteRequest(String fullName, Integer teamNumber, String position, String teamName) {
        this.fullName = fullName;
        this.teamNumber = teamNumber;
        this.position = position;
        this.teamName = teamName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
