package com.project.Service;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateService {

    public static String getParsedDate(LocalDateTime localDateTime){

        StringBuilder sb=new StringBuilder();
        String v=String.valueOf(localDateTime.getDayOfMonth());
        if(v.length()==1){
            sb.append('0');
        }
        sb.append(localDateTime.getDayOfMonth());
        sb.append('.');
        v=String.valueOf(localDateTime.getMonthOfYear());
        if(v.length()==1){
            sb.append('0');
        }
        sb.append(localDateTime.getMonthOfYear());
        sb.append('.');
        v=String.valueOf(localDateTime.getYear());
        if(v.length()==1){
            sb.append('0');
        }
        sb.append(localDateTime.getYear());
        sb.append(' ');
        v=String.valueOf(localDateTime.getHourOfDay());
        if(v.length()==1){
            sb.append('0');
        }
        sb.append(localDateTime.getHourOfDay());
        sb.append(':');
        v=String.valueOf(localDateTime.getMinuteOfHour());
        if(v.length()==1){
            sb.append('0');
        }
        sb.append(localDateTime.getMinuteOfHour());
        sb.append(':');
        v=String.valueOf(localDateTime.getSecondOfMinute());
        if(v.length()==1){
            sb.append('0');
        }
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

    public static int getDaysDifference(String endDate){
        LocalDateTime localDateTime=LocalDateTime.now();
        DateTime currentDateTime =parseToDateTime(getParsedDate(localDateTime));
        DateTime endDateTime=parseToDateTime(endDate);
        return Days.daysBetween(endDateTime,currentDateTime).getDays();
    }
}
