package com.sirmaacademy.finalexam.footballStatistics.model.dto;

public class TeamCsvDto {

    private Long id;
    private String name;
    private String managerFullName;
    private String footballGroup;

    public TeamCsvDto(Long id, String name, String managerFullName, String footballGroup) {
        this.id = id;
        this.name = name;
        this.managerFullName = managerFullName;
        this.footballGroup = footballGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFootballGroup() {
        return footballGroup;
    }

    public void setFootballGroup(String footballGroup) {
        this.footballGroup = footballGroup;
    }

}
