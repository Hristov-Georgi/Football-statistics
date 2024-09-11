package com.sirmaacademy.finalexam.footballStatistics.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public abstract class DateFormatter {

    public static LocalDate formatDate(String date) {

        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();

        dateTimeFormatterBuilder.appendPattern(
                "[M.d.yyyy]" + "[M/d/yyyy]" + "[M-d-yyyy]" + "[M d yyyy]"
                + "[MM.dd.yyyy]" + "[MM/dd/yyyy]" + "[MM-dd-yyyy]" + "[MM dd yyyy]"
                + "[M.d.yy]" + "[M/d/yy]" + "[M-d-yy]" + "[M d yy]");

        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

        return LocalDate.parse(date, dateTimeFormatter);

    }

}
