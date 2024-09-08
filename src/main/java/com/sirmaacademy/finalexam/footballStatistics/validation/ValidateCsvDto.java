package com.sirmaacademy.finalexam.footballStatistics.validation;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.InvalidFootballGroupException;
import com.sirmaacademy.finalexam.footballStatistics.exceptions.InvalidIdException;
import com.sirmaacademy.finalexam.footballStatistics.exceptions.InvalidLengthException;
import com.sirmaacademy.finalexam.footballStatistics.exceptions.InvalidSymbolException;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;

public abstract class ValidateCsvDto {

    public static FootballGroup validateFootballGroup(String group) {

        for (FootballGroup g : FootballGroup.values()) {

            if (g.getValue().equals(group.toUpperCase())) {
                return FootballGroup.valueOf(group.toUpperCase());
            }

        }
        throw new InvalidFootballGroupException("Invalid group: " + group + ". Football group should be a symbol from A to J");
    }

    public static String validateManagerFullName(String managerFullName) {

        if (managerFullName.isBlank()) {
            throw new InvalidLengthException("Name can not be blank string.");
        } else if (managerFullName.length() < 4) {
            throw new InvalidLengthException("Full name should be at least 4 symbols long");
        } else if (managerFullName.length() > 100) {
            throw new InvalidLengthException("Name can't be more than 100 symbols long");
        }

        for (char s : managerFullName.toCharArray()) {

            if (( s < 32)
                    || ( 32 < s && s < 65)
                    || (90 < s && s < 97)
                    ||  122 < s) {
                throw new InvalidSymbolException("Invalid symbol: '" + s + "' in manager full name.");
            }

        }
        return managerFullName;
    }

    public static String validateTeamName(String name) {

        if (name.isBlank()) {
            throw new InvalidLengthException("Name can not be blank string.");
        } else if (name.length() < 4) {
            throw new InvalidLengthException("Name should be at least 4 symbols long");
        } else if (name.length() > 100) {
            throw new InvalidLengthException("Name can't be more than 100 symbols long");
        }

        for (char s : name.toCharArray()) {

            if ( 122 < s
                    || (90 < s && s < 97)
                    || (57 < s && s < 65)
                    || (s < 48 && 45 < s)
                    || (s < 45 && 41 < s)
                    || (s < 40 && 32 < s)
                    || s < 32) {
                throw new InvalidSymbolException("Invalid symbol: '" + s + "' in football team's name.");
            }

        }
        return name;
    }

    public static Long validateId(Long id) {

        if (id > 0) {
            return id;
        }
        throw new InvalidIdException("Team Id can not be less than or equal to zero.");
    }

}
