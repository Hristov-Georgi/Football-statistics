package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLDelete;
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
    private LocalDate localDate;

    @OneToMany(targetEntity = Score.class, mappedBy = "match")
    private List<Score> scores = new ArrayList<>();

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    public Match() {
    }

    /**
     * Use when receive data from frontend.
     * @param localDate
     * @param scores
     */
    public Match(LocalDate localDate, List<Score> scores) {
        this.localDate = localDate;
        this.scores = scores;
    }

    /**
     * Use when import data from csv file.
     * @param id
     * @param localDate
     */
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

