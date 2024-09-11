package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int teamNumber;

    @Enumerated(value = EnumType.STRING)
    private FieldPosition fieldPosition;

    @Column(nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @Transient
    private Long teamId;

    public Player() {
    }

    /**
     * Use when import/receive data from frontend client.
     * @param teamNumber - player number in the team
     * @param fieldPosition - player position on the field - enum value.
     * @param fullName
     * @param team - Team object required.
     */
    public Player(int teamNumber, FieldPosition fieldPosition, String fullName, Team team) {
        this.teamNumber = teamNumber;
        this.fieldPosition = fieldPosition;
        this.fullName = fullName;
        this.team = team;
    }

    /**
     * Use when import data from csv file.
     * @param id
     * @param teamNumber
     * @param fieldPosition
     * @param fullName
     * @param teamId
     */
    public Player(Long id, int teamNumber, FieldPosition fieldPosition, String fullName, Long teamId) {
        this.id = id;
        this.teamNumber = teamNumber;
        this.fieldPosition = fieldPosition;
        this.fullName = fullName;
        this.teamId = teamId;
    }

    public long getId() {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

}
