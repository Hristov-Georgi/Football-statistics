package com.sirmaacademy.finalexam.footballStatistics.model.dto;

/**
 * Use this DTO to match and transfer data from matches.csv file format to the application entity.
 */
public class MatchCsvDto {

    private Long id;
    private Long aTeamId;
    private Long bTeamId;
    private String date;
    private String score;

    public MatchCsvDto(Long id, Long aTeamId, Long bTeamId, String date, String score) {
        this.id = id;
        this.aTeamId = aTeamId;
        this.bTeamId = bTeamId;
        this.date = date;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getaTeamId() {
        return aTeamId;
    }

    public void setaTeamId(Long aTeamId) {
        this.aTeamId = aTeamId;
    }

    public Long getbTeamId() {
        return bTeamId;
    }

    public void setbTeamId(Long bTeamId) {
        this.bTeamId = bTeamId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
