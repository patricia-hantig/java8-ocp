package com.patri.java.ocp._5_dates_strings_localization._1_dates_and_times;

import java.time.*;
import java.time.temporal.ChronoUnit;

// Duration : <= Day
public class WorkingWithDurations {

    // For Duration, you can specify the number of days, hours, minutes, seconds, or nanoseconds
    // Duration works the same way as Period, but with objects that have time
    // duration starts with PT (period of time)
    // A duration is stored in hours, minutes and seconds

    public static void main(String[] args) {

        // ■ Duration format: PT 1H 2M 3S = Duration #hours #minutes #seconds
        // ■ There are a lot of ways to create a Duration class:
        Duration daily = Duration.ofDays(1);                // PT24H
        Duration hourly = Duration.ofHours(1);              // PT1H
        Duration everyMinute = Duration.ofMinutes(1);       // PT1M
        Duration everyTenSeconds = Duration.ofSeconds(10);  // PT10S
        Duration everyMilli = Duration.ofMillis(1);         // PT0.001S
        Duration everyNano = Duration.ofNanos(1);           // PT0.000000001S
        System.out.println(daily + " " + hourly + " " + everyMinute + " " + everyTenSeconds + " " + everyMilli + " " + everyNano);

        // Duration doesn’t have a constructor that takes multiple units like Period does
        // so we don't have something like Duration.of(...)
        // ■ Duration includes another generic factory method - it takes a number and a TemporalUnit - interface with implementation ChronoUnit

        // the above 6 durations can be written using ChronoUnit
        Duration daily1 = Duration.of(1, ChronoUnit.DAYS);
        Duration hourly1 = Duration.of(1, ChronoUnit.HOURS);
        Duration everyMinute1 = Duration.of(1, ChronoUnit.MINUTES);
        Duration everyTenSeconds1 = Duration.of(10, ChronoUnit.SECONDS);
        Duration everyMilli1 = Duration.of(1, ChronoUnit.MILLIS);
        Duration everyNano1 = Duration.of(1, ChronoUnit.NANOS);

        Duration halfDay = Duration.of(1, ChronoUnit.HALF_DAYS);
        System.out.println(halfDay);

        // ■ Using ChronoUnit we can determine the difference between 2 temporal values
        LocalTime one = LocalTime.of(5, 15);
        LocalTime two = LocalTime.of(6, 30);
        LocalDate localDate = LocalDate.of(2016, 1, 20);
        System.out.println(ChronoUnit.HOURS.between(one, two));
        System.out.println(ChronoUnit.MINUTES.between(one, two));
        // System.out.println(ChronoUnit.MINUTES.between(one, localDate)); // DateTimeException - Unable to obtain LocalTime from TemporalAccessor: 2016-01-20 of type java.time.LocalDate

        // Duration is used for LocalTime and LocalDateTime
        // when we have LocalDate and we want to add a day we use Period
        LocalDate date = LocalDate.of(2015, 1, 20);
        LocalTime time = LocalTime.of(6, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Duration duration = Duration.ofHours(6);
        System.out.println(dateTime.plus(duration));
        System.out.println(time.plus(duration));
        // System.out.println(date.plus(duration)); // UnsupportedTemporalException

        LocalDate date1 = LocalDate.of(2015, 1, 20);
        LocalTime time1 = LocalTime.of(6, 15);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        Duration duration1 = Duration.ofHours(23);
        System.out.println(dateTime1.plus(duration1));
        System.out.println(time1.plus(duration1));
        // System.out.println(date1.plus(duration1)); // UnsupportedTemporalException

        // ■ Period and Duration are not equivalent
        // ex: a Period and Duration of the same length
        LocalDate date2 = LocalDate.of(2015, 5, 25);
        Period period = Period.ofDays(1);
        Duration duration2 = Duration.ofDays(1);
        System.out.println(date2.plus(period));
        System.out.println(date2.plus(duration2)); // Unsupported unit: Seconds

        // ■ Where to use Duration and Period?
        // LocalDate        uses    Period
        // LocalDateTime    uses    Period + Duration
        // LocalTime        uses    Duration
        // ZonedDateTime    uses    Period + Duration
    }
}
