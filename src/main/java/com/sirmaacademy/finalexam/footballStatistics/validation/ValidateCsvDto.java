package com.sirmaacademy.finalexam.footballStatistics.validation;

import com.sirmaacademy.finalexam.footballStatistics.exceptions.*;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FieldPosition;
import com.sirmaacademy.finalexam.footballStatistics.model.enums.FootballGroup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidateCsvDto {

    public static int validateMinutes(Integer minutes) {

        if (minutes < 0 || 190 < minutes) {
            throw new InvalidMatchDurationException("Invalid data: '" + minutes
            + "' minutes. Match duration is higher than 0 minutes and usually no more than 120 minutes.");
        }
        return minutes;
    }

    public static String validateGoalsInput(String goals, String requiredTeamScore) {

        String regex = "^(?<aGoals>[0-9]){1,3}\\s{0,3}(?<aPenalty>\\([0-9]{1,3}\\))?\\s{0,3}(-)\\s{0,3}(?<bGoals>[0-9]){1,3}\\s{0,3}(?<bPenalty>\\([0-9]{1,3}\\))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(goals);

        if (matcher.matches()) {

            String aPenalty = matcher.group("aPenalty");
            String bPenalty = matcher.group("bPenalty");

            if ((aPenalty == null || aPenalty.isBlank()) && (bPenalty == null || bPenalty.isBlank()) ) {

                if (requiredTeamScore.equals("aTeamScore")) {
                    return matcher.group("aGoals");
                } else {
                    return matcher.group("bGoals");
                }

            } else if (aPenalty != null && !aPenalty.isBlank() && bPenalty != null && !bPenalty.isBlank()) {

                if (requiredTeamScore.equals("aTeamScore")) {
                    return matcher.group("aGoals") + matcher.group("aPenalty");
                } else {
                    return matcher.group("bGoals") + matcher.group("bPenalty");
                }

            } else {
                throw new InvalidMatchScoreException("Score: '" + goals
                        + "' is in invalid format. Make corrections in matches.csv data.");
            }

        } else {
            throw new InvalidMatchScoreException("Score: '" + goals
                    + "' is in invalid format. Make corrections in matches.csv data.");
        }

    }

    public static FieldPosition validateFieldPosition(String position) {

        for (FieldPosition p : FieldPosition.values()) {

            if (p.name().equals(position.toUpperCase())) {
                return FieldPosition.valueOf(position.toUpperCase());
            }

        }
        throw new InvalidFieldPositionException("Field position: '" + position
                + "' does not exist in modern football.");
    }

    public static int validateTeamNumber(int teamNumber) {

        if (teamNumber < 1 || 99 < teamNumber) {
            throw new InvalidPlayerTeamNumberException("Invalid team number: '" + teamNumber
                    + "'. Team numbers should be in range: from 1 to 99");
        }
        return teamNumber;
    }

    public static FootballGroup validateFootballGroup(String group) {

        for (FootballGroup g : FootballGroup.values()) {

            if (g.getValue().equals(group.toUpperCase())) {
                return FootballGroup.valueOf(group.toUpperCase());
            }

        }
        throw new InvalidFootballGroupException("Invalid group: " + group + ". Football group should be a symbol from A to J");
    }

    public static String validatePersonFullName(String personFullName) {

        if (personFullName.isBlank()) {
            throw new InvalidLengthException("Name can not be blank string.");
        } else if (personFullName.length() < 4) {
            throw new InvalidLengthException("Full name should be at least 4 symbols long");
        } else if (personFullName.length() > 100) {
            throw new InvalidLengthException("Name can't be more than 100 symbols long");
        }
        return personFullName;
    }

    public static String validateTeamName(String name) {

        if (name.isBlank()) {
            throw new InvalidLengthException("Name can not be blank string.");
        } else if (name.length() < 4) {
            throw new InvalidLengthException("Name should be at least 4 symbols long");
        } else if (name.length() > 100) {
            throw new InvalidLengthException("Name can't be more than 100 symbols long");
        }

//        for (char s : name.toCharArray()) {
//
//            if ( 122 < s
//                    || (90 < s && s < 97)
//                    || (57 < s && s < 65)
//                    || (s < 48 && 45 < s)
//                    || (s < 45 && 41 < s)
//                    || (s < 40 && 32 < s)
//                    || s < 32) {
//                throw new InvalidSymbolException("Invalid symbol: '" + s + "' in football team's name.");
//            }
//
//        }
        return name;
    }

    public static Long validateId(Long id) {

        if (id > 0) {
            return id;
        }
        throw new InvalidIdException("Team Id can not be less than or equal to zero.");
    }

}
