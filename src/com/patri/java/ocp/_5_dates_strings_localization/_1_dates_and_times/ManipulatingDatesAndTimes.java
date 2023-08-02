package com.patri.java.ocp._5_dates_strings_localization._1_dates_and_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ManipulatingDatesAndTimes {
    public static void main(String[] args) {
        // Date and Time classes are immutable => so we have to be sure that we assign the results of the methods that modify the dates so that they are not lost

        // methods: plusDays(int nr), plusWeeks(int nr), plusMonths(int nr), plusYears(int nr)
        LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
        System.out.println(date);
        date = date.plusDays(2);
        System.out.println(date);
        date = date.plusWeeks(1);
        System.out.println(date);
        date = date.plusMonths(1);
        System.out.println(date);
        date = date.plusYears(5);
        System.out.println(date);

        // methods: minusDays(int nr), minusHours(int nr), minusSeconds(int nr)
        LocalDate localDate = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(5, 15);
        LocalDateTime dateTime = LocalDateTime.of(localDate, time);
        System.out.println(dateTime);
        dateTime = dateTime.minusDays(1);
        System.out.println(dateTime);
        dateTime = dateTime.minusHours(10);
        System.out.println(dateTime);
        dateTime = dateTime.minusSeconds(30);
        System.out.println(dateTime);

        // we can use chaining methods
        LocalDate localDate1 = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time1 = LocalTime.of(5, 15);
        LocalDateTime dateTime1 = LocalDateTime.of(localDate1, time1)
                .minusDays(1).minusHours(10).minusSeconds(30);
        System.out.println(dateTime1);

        // what does this print?
        LocalDate date2 = LocalDate.of(2020, Month.JANUARY, 20);
        date2.plusDays(10);
        System.out.println(date2); // January 20, 2020 (Adding 10 days - the program ignored the result)

        // ex3:
        LocalDate date3 = LocalDate.of(2020, Month.JANUARY, 20);
        // date3 = date3.plusMinutes(1); // DOES NOT COMPILE - LocalDate does not contain time

        // ■ Methods in LocalDate, LocalTime, LocalDateTime, and ZonedDateTime
        //              Method          | Can call on LocalDate?    | Can call on LocalTime?    | Can call on LocalDateTime or ZonedDateTime?
        // ----------------------------------------------------------------------------------------------------------------------------------
        // plusYears / minusYears       |           Yes             |           No              |                   Yes
        // plusMonths / minusMonths     |           Yes             |           No              |                   Yes
        // plusWeeks / minusWeeks       |           Yes             |           No              |                   Yes
        // plusDays / minusDays         |           Yes             |           No              |                   Yes
        // plusHours / minusHours       |           No              |           Yes             |                   Yes
        // plusMinutes / minusMinutes   |           No              |           Yes             |                   Yes
        // plusSeconds / minusSeconds   |           No              |           Yes             |                   Yes
        // plusNanos / minusNanos       |           No              |           Yes             |                   Yes
        // ----------------------------------------------------------------------------------------------------------------------------------
    }

    // Manipulating Dates before Java8
    // ■ Old vs. new way of creating dates

    // adding a day
    // old way:
    public Date addDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return  calendar.getTime();
    }
    // new way:
    public LocalDate addDay(LocalDate date) {
        return date.plusDays(1);
    }

    // subtracting a day
    // old way:
    public Date subtractDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
    // new way:
    public LocalDate subtractDay(LocalDate date) {
        return date.minusDays(1);
    }

    // ■ LocalDate and LocalDateTime can be converted to a long
    // LocalDate and LocalDateTime have a method to convert themselves into long: epoch()
    // LocalDate has: toEpochDay() = is the number of days since January 1, 1970
    // LocalDateTime and ZonedDateTime have: toEpochSecond() = is the number of seconds since January 1, 1970
    // LocalTime does not have an epoch method - Since it represents a time that can occur on any date, it doesn’t make sense to compare it to 1970

    // ■ Working with Periods - WorkingwithPeriods.class
    // ■ Working with Durations - WorkingwithDurations.class
    // ■ Working with Instants - WorkingwithInstants.class
}
