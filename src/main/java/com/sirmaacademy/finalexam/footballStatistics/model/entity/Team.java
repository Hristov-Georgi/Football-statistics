package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String managerFullName;

    @Enumerated(EnumType.STRING)
    private FootballGroup footballGroup;

    public Team() {
    }

    /**
     * Use when import/receive data from frontend client.
     * @param name
     * @param managerFullName
     * @param footballGroup
     */
    public Team(String name, String managerFullName, FootballGroup footballGroup) {
        this.name = name;
        this.managerFullName = managerFullName;
        this.footballGroup = footballGroup;
    }

    /**
     * Use when import data from csv file.
     * @param id
     * @param name
     * @param managerFullName
     * @param footballGroup
     */
    public Team(long id, String name, String managerFullName, FootballGroup footballGroup) {
        this.id = id;
        this.name = name;
        this.managerFullName = managerFullName;
        this.footballGroup = footballGroup;
    }

    public long getId() {
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

    public FootballGroup getFootballGroup() {
        return footballGroup;
    }

    public void setFootballGroup(FootballGroup footballGroup) {
        this.footballGroup = footballGroup;
    }

}
