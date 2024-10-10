package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "records")
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Match match;

    @Column(nullable = false)
    private Integer fromMinutes;

    @Column(nullable = false)
    private Integer toMinutes;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Transient
    private Long playerIdFromCsvFile;

    @Transient
    private Long matchIdFromCsvFile;

    public Records() {
    }

    /**
     * Use when import/receive data from frontend client.
     */
    public Records(Player player, Match match, Integer fromMinutes, Integer toMinutes) {
        this.player = player;
        this.match = match;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

    /**
     * Use when import data from csv file.
     */
    public Records(long id, Long playerIdFromCsvFile, Long matchIdFromCsvFile, Integer fromMinutes, Integer toMinutes) {
        this.id = id;
        this.playerIdFromCsvFile = playerIdFromCsvFile;
        this.matchIdFromCsvFile = matchIdFromCsvFile;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes (Integer fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public Integer getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(Integer toMinutes) {
        this.toMinutes = toMinutes;
    }

    public Long getPlayerIdFromCsvFile() {
        return playerIdFromCsvFile;
    }

    public void setPlayerIdFromCsvFile(Long playerIdFromCsvFile) {
        this.playerIdFromCsvFile = playerIdFromCsvFile;
    }

    public Long getMatchIdFromCsvFile() {
        return matchIdFromCsvFile;
    }

    public void setMatchIdFromCsvFile(Long matchIdFromCsvFile) {
        this.matchIdFromCsvFile = matchIdFromCsvFile;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
