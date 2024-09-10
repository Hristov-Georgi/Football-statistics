package com.sirmaacademy.finalexam.footballStatistics.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

public abstract class DateFormatter {

    public static LocalDate formatDate(String date) {

        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();

        dateTimeFormatterBuilder.appendPattern("[M.d.yyyy]" + "[M/d/yyyy]" + "[M-d-yyyy]" + "[M d yyyy]");

//        dateTimeFormatterBuilder.appendPattern("[dd.MM.yyyy]" + "[dd/MM/yyyy]" + "[dd-MM-yyyy]" + "[dd MM yyyy]");
//        dateTimeFormatterBuilder.appendPattern("[MM.dd.yyyy]" + "[MM/dd/yyyy]" + "[MM-dd-yyyy]" + "[MM dd yyyy]");
//        dateTimeFormatterBuilder.appendPattern("[yyyy.MM.dd]" + "[yyyy/MM/dd]" + "[yyyy-MM-dd]" + "[yyyy MM dd]");
//        dateTimeFormatterBuilder.appendPattern("[d.M.yyyy]" + "[d/M/yyyy]" + "[d-M-yyyy]" + "[d M yyyy]");
//        dateTimeFormatterBuilder.appendPattern("[M.d.yyyy]" + "[M/d/yyyy]" + "[M-d-yyyy]" + "[M d yyyy]");
//        dateTimeFormatterBuilder.appendPattern("[yyyy.M.d]" + "[yyyy/M/d]" + "[yyyy-M-d]" + "[yyyy M d]");
//        dateTimeFormatterBuilder.appendPattern("[d.M.yy]" + "[d/M/yy]" + "[d-M-yy]" + "[d M yy]");
//        dateTimeFormatterBuilder.appendPattern("[M.d.yy]" + "[M/d/yy]" + "[M-d-yy]" + "[M d yy]" );
//        dateTimeFormatterBuilder.appendPattern("[yy.M.d]" + "[yy/M/d]" + "[yy-M-d]" + "[yy M d]");

                //TODO: append all possible variants. - should add month with letters - March...
        // add more patterns

        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

        return LocalDate.parse(date, dateTimeFormatter);

        //TODO: see how different formats are saved in database.
    }

}
