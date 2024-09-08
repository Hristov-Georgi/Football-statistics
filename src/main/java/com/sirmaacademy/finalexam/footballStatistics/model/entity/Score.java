package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;

    @Column(nullable = false, updatable = false)
    private String scoredGoals;

    public Score() {
    }

    public Score(Long id, Team team, String scoredGoals) {
        this.id = id;
        this.team = team;
        this.scoredGoals = scoredGoals;
    }

    public Score(Team team, String scoredGoals) {
        this.team = team;
        this.scoredGoals = scoredGoals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(String scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

}
