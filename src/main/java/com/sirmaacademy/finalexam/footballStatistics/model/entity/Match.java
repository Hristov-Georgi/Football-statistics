package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private List<Score> scores = new ArrayList<>();

    public Match() {
    }

    public Match(LocalDate localDate, List<Score> scores) {
        this.localDate = localDate;
        this.scores = scores;
    }

    public Match(Long id, LocalDate localDate) {
        this.id = id;
        this.localDate = localDate;
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

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

}

