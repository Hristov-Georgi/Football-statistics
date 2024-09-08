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

    @ManyToOne
    private Match match;

    @Transient
    private Long matchId;

    public Score() {
    }

    public Score(Long id, Team team, String scoredGoals, Long matchId) {
        this.id = id;
        this.team = team;
        this.scoredGoals = scoredGoals;
        this.matchId = matchId;
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
