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
    private String fullName; //TODO: Divide names - first and last name ??;

    @ManyToOne
    private Team team;

    public Player() {
    }

    public Player(int teamNumber, FieldPosition fieldPosition, String fullName, Team team) {
        this.teamNumber = teamNumber;
        this.fieldPosition = fieldPosition;
        this.fullName = fullName;
        this.team = team;
    }

    public Player(long id, int teamNumber, FieldPosition fieldPosition, String fullName, Team team) {
        this.id = id;
        this.teamNumber = teamNumber;
        this.fieldPosition = fieldPosition;
        this.fullName = fullName;
        this.team = team;
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
}
