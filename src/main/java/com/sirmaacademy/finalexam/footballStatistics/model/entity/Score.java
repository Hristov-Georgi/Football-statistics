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

    @ManyToOne(fetch = FetchType.LAZY)
    private Match match;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    public Score() {
    }

    /**
     * Created this class for better data handling.
     * Use when import/receive data either from frontend client or csv file.
     * Data is fetched from matches.csv
     */
    public Score(Team team, String scoredGoals, Match match) {
        this.team = team;
        this.scoredGoals = scoredGoals;
        this.match = match;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
