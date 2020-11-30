package com.project.Service;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateService {

    public static String getParsedDate(LocalDateTime localDateTime){

        StringBuilder sb=new StringBuilder();
        sb.append(localDateTime.getDayOfMonth());
        sb.append('.');
        sb.append(localDateTime.getMonthOfYear());
        sb.append('.');
        sb.append(localDateTime.getYear());
        sb.append(' ');
        sb.append(localDateTime.getHourOfDay());
        sb.append(':');
        sb.append(localDateTime.getMinuteOfHour());
        sb.append(':');
        sb.append(localDateTime.getSecondOfMinute());
        return sb.toString();
    }

    public static LocalDateTime getAfterDurationDateTime(LocalDateTime localDateTime, int dur){
        Period days =new Period().withDays(dur);
        return localDateTime.plus(days);
    }

    public static DateTime parseToDateTime(String s){
        DateTimeFormatter dateTimeFormatter= DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");
        DateTime dateTime=DateTime.parse(s,dateTimeFormatter);
        return dateTime;
    }
}
