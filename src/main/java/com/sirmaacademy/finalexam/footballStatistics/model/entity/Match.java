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

    @OneToMany(targetEntity = Score.class, mappedBy = "match")
    private List<Score> scores;

    public Match() {
    }

    public Match(LocalDate localDate, List<Score> scores) {
        this.localDate = localDate;
        this.scores = scores;
    }

    public Match(Long id, LocalDate localDate, List<Score> scores) {
        this.id = id;
        this.localDate = localDate;
        this.scores = scores;
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

//    public Long getATeamId() {
//        return ATeamId;
//    }
//
//    public void setATeamId(Long ATeamId) {
//        this.ATeamId = ATeamId;
//    }
//
//    public Long getBTeamId() {
//        return BTeamId;
//    }
//
//    public void setBTeamId(Long BTeamId) {
//        this.BTeamId = BTeamId;
//    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

//    public String getaTeamScore() {
//        return aTeamScore;
//    }
//
//    public void setaTeamScore(String aTeamScore) {
//        this.aTeamScore = aTeamScore;
//    }
//
//    public String getbTeamScore() {
//        return bTeamScore;
//    }
//
//    public void setbTeamScore(String bTeamScore) {
//        this.bTeamScore = bTeamScore;
//    }

}

