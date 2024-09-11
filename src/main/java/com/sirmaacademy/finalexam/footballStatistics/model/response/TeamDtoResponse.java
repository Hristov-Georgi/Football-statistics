package com.sirmaacademy.finalexam.footballStatistics.model.response;

import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;

public class TeamDtoResponse {

    private String name;
    private String managerFullName;
    private FootballGroup footballGroup;

    public TeamDtoResponse(String name, String managerFullName, FootballGroup footballGroup) {
        this.name = name;
        this.managerFullName = managerFullName;
        this.footballGroup = footballGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    public FootballGroup getFootballGroup() {
        return footballGroup;
    }

    public void setFootballGroup(FootballGroup footballGroup) {
        this.footballGroup = footballGroup;
    }
}
