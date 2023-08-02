package com.patri.java.ocp._5_dates_strings_localization._1_dates_and_times;

import java.time.*;
import java.time.temporal.ChronoUnit;

// Instant class represents a specific moment in time in the GMT time zone
public class WorkingWithInstants {

    public static void main(String[] args) {
        // ex: run a timer
        Instant now = Instant.now();
        // do something while time consuming
        System.out.println("Let's do something while time passing");
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
        System.out.println("Let's see how many millis have passed");
        Instant later = Instant.now();
        Duration duration = Duration.between(now, later);
        System.out.println(duration.toMillis());

        // ■ ZonedDateTime can be turn into an Instant:
        LocalDate date = LocalDate.of(2015, 5, 25);
        LocalTime time = LocalTime.of(11, 55, 00);
        ZoneId zoneId = ZoneId.of("US/Pacific");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zoneId);
        Instant instant = zonedDateTime.toInstant();
        System.out.println(zonedDateTime);
        System.out.println(instant);
        // the 2 last lines represent the same moment in time - the ZonedDateTime includes a time zone
        // the instant gets rid of the time zone and turns it into an Instant of time in GMT

        // ■ LocalDateTime cannot be converted to an Instant - instant is a point in time

        // ■ Instant allows you to add any unit day or smaller
        Instant nextDay = instant.plus(1, ChronoUnit.DAYS);
        System.out.println(nextDay);
        Instant nextHour = instant.plus(1, ChronoUnit.HOURS);
        System.out.println(nextHour);
        // Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS);
        // System.out.println(nextWeek); // exception = Unsupported unit: Weeks

        // ■■ Daylight Savings Time
        // one day in March has 23 hours and one day in November has 25 hours => edge case
        // ex in 13 March 2016 there was a change of hour in US from 2:00 am to 3:00 am
        // when the hour is changed this change appears also in Java 8
        LocalDate localDate = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime localTime = LocalTime.of(1, 30);
        ZoneId zone = ZoneId.of("US/Pacific");
        ZonedDateTime dateTime = ZonedDateTime.of(localDate, localTime, zone);
        System.out.println(dateTime); // 2016-03-13T01:30-08:00[US/Pacific]
        dateTime = dateTime.plusHours(1);
        System.out.println(dateTime); // 2016-03-13T03:30-07:00[US/Pacific]

        // similarly in November
        LocalDate localDate2 = LocalDate.of(2016, Month.NOVEMBER, 6);
        LocalTime localTime2 = LocalTime.of(1, 30);
        ZoneId zone2 = ZoneId.of("US/Pacific");
        ZonedDateTime dateTime2 = ZonedDateTime.of(localDate2, localTime2, zone2);
        System.out.println(dateTime2);      // 2016-11-06T01:30-07:00[US/Pacific]
        dateTime2 = dateTime2.plusHours(1);
        System.out.println(dateTime2);      // 2016-11-06T01:30-08:00[US/Pacific]
        dateTime2 = dateTime2.plusHours(1);
        System.out.println(dateTime2);      // 2016-11-06T02:30-08:00[US/Pacific]

        // if we try to create a date that doesn't exists we will get the next one:
        LocalDate localDate3 = LocalDate.of(2016, Month.MARCH, 13);
        LocalTime localTime3 = LocalTime.of(2, 30);
        ZoneId zone3 = ZoneId.of("US/Pacific");
        ZonedDateTime dateTime3 = ZonedDateTime.of(localDate3, localTime3, zone3);
        System.out.println(dateTime3);      // 2016-03-13T03:30-07:00[US/Pacific
        // Java is smart enough to know that there is no 2:30 a.m. that night and switches over to the appropriate GMT offset

    }
}
