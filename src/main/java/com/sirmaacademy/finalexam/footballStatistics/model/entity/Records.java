package com.sirmaacademy.finalexam.footballStatistics.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

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
    @Size(max = 300)
    private Integer fromMinutes;

    @Column(nullable = false) // TODO: if input data is NULL -> set to 90 min
    @Size(max = 300)
    private Integer toMinutes;

    public Records() {
    }

    public Records(Player player, Match match, Integer fromMinutes, Integer toMinutes) {
        this.player = player;
        this.match = match;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

    public Records(long id, Player player, Match match, Integer fromMinutes, Integer toMinutes) {
        this.id = id;
        this.player = player;
        this.match = match;
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

    public @Size(max = 300) Integer getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(@Size(max = 300) Integer fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public @Size(max = 300) Integer getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(@Size(max = 300) Integer toMinutes) {
        this.toMinutes = toMinutes;
    }

}
