package com.sirmaacademy.finalexam.footballStatistics.model.enums;

/**
 * Football groups according UEFA website. Last information update - 09.09.2024
 */
public enum FootballGroup {

    A ("A"),
    B ("B"),
    C ("C"),
    D ("D"),
    E ("E"),
    F ("F"),
    G ("G"),
    H ("H"),
    I ("I"),
    J ("J");

    private final String value;

    FootballGroup(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
