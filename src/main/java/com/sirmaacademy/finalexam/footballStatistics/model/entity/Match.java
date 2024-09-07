package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "matches", fetch = FetchType.EAGER)
    private List<Team> teams;

    @DateTimeFormat
    private LocalDate localDate; //TODO: universal format

    @Column(nullable = false)
    private int ATeamScore;

    @Column(nullable = false)
    private int BTeamScore;

    public Match() {
    }

    public Match(Team ATeam, Team BTeam, LocalDate localDate, int ATeamScore, int BTeamScore) {
        this.teams = List.of(ATeam, BTeam);
        this.localDate = localDate;
        this.ATeamScore = ATeamScore;
        this.BTeamScore = BTeamScore;
    }

    public Match(Long id, Team ATeam, Team BTeam, LocalDate localDate, int ATeamScore, int BTeamScore) {
        this.id = id;
        this.teams = List.of(ATeam, BTeam);
        this.localDate = localDate;
        this.ATeamScore = ATeamScore;
        this.BTeamScore = BTeamScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getATeamScore() {
        return ATeamScore;
    }

    public void setATeamScore(int ATeamScore) {
        this.ATeamScore = ATeamScore;
    }

    public int getBTeamScore() {
        return BTeamScore;
    }

    public void setBTeamScore(int BTeamScore) {
        this.BTeamScore = BTeamScore;
    }

}

