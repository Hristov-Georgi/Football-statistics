package com.sirmaacademy.finalexam.footballStatistics.model.response;

import java.time.LocalDate;
import java.util.List;

public class MatchDtoResponse {

    private LocalDate localDate;
    private List<ScoreDtoResponse> scoreDtoResponseList;

    public MatchDtoResponse(LocalDate localDate, List<ScoreDtoResponse> scoreDtoResponseList) {
        this.localDate = localDate;
        this.scoreDtoResponseList = scoreDtoResponseList;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<ScoreDtoResponse> getScoreDtoResponseList() {
        return scoreDtoResponseList;
    }

    public void setScoreDtoResponseList(List<ScoreDtoResponse> scoreDtoResponseList) {
        this.scoreDtoResponseList = scoreDtoResponseList;
    }
}
