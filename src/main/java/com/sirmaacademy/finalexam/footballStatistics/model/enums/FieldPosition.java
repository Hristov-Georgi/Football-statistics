package com.sirmaacademy.finalexam.footballStatistics.model.enums;

public enum FieldPosition {

    CF("Centre forward"),
    LW ("Left winger"),
    SS ("Second striker"),
    RW ("Right winger"),
    AM ("Attacking midfielder"),
    LM ("Left midfielder"),
    CM ("Central midfielder"),
    RM ("Right midfielder"),
    FW ("Forward"),
    MF ("Midfielder"),
    DM ("Defensive midfielder"),
    DF ("Defender"),
    LWB ("Left wing-back"),
    RWB ("Right wing-back"),
    LB ("Left-back"),
    CB ("Center-back"),
    RB ("Right-back"),
    SW ("Sweeper"),
    GK ("Goalkeeper");

    private final String value;

    FieldPosition(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
