package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "teams")
@SQLDelete(sql = "UPDATE teams SET deleted = true WHERE id=?")
@FilterDef(name = "deletedTeamFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedTeamFilter", condition = "deleted = :isDeleted")
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

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    public Team() {
    }

    /**
     * Use when import/receive data from frontend client.
     */
    public Team(String name, String managerFullName, FootballGroup footballGroup) {
        this.name = name;
        this.managerFullName = managerFullName;
        this.footballGroup = footballGroup;
    }

    /**
     * Use when import data from csv file.
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
