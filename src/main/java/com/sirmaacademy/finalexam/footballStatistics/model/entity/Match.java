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

    @DateTimeFormat
    private LocalDate localDate; //TODO: universal format

    @OneToMany
    private List<Score> scores;

    @Transient
    private Long ATeamId; //do i need it ?

    @Transient
    private Long BTeamId;

    @Transient
    private String aTeamScore;

    @Transient
    private String bTeamScore;

    public Match() {
    }

    public Match(LocalDate localDate, List<Score> scores) {
        this.localDate = localDate;
        this.scores = scores;
    }

    public Match(Long id, Long ATeamId, Long BTeamId, LocalDate localDate, String ATeamScore, String BTeamScore) {
        this.id = id;
        this.ATeamId = ATeamId;
        this.BTeamId = BTeamId;
        this.localDate = localDate;
        this.aTeamScore = ATeamScore;
        this.bTeamScore = BTeamScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Long getATeamId() {
        return ATeamId;
    }

    public void setATeamId(Long ATeamId) {
        this.ATeamId = ATeamId;
    }

    public Long getBTeamId() {
        return BTeamId;
    }

    public void setBTeamId(Long BTeamId) {
        this.BTeamId = BTeamId;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public String getaTeamScore() {
        return aTeamScore;
    }

    public void setaTeamScore(String aTeamScore) {
        this.aTeamScore = aTeamScore;
    }

    public String getbTeamScore() {
        return bTeamScore;
    }

    public void setbTeamScore(String bTeamScore) {
        this.bTeamScore = bTeamScore;
    }

}

