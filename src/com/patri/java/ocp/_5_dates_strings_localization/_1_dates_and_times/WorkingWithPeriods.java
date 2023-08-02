package com.patri.java.ocp._5_dates_strings_localization._1_dates_and_times;

import java.time.*;

// Period : >= Day
public class WorkingWithPeriods {

    // ex: zookeeper has decided to switch the toys every month

    private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {        // check if still before end
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plusMonths(1);      // add a month
        }
    }

    private static void performAnimalEnrichmentWithPeriod(LocalDate start, LocalDate end, Period period) {
        LocalDate upTo = start;
        while (upTo.isBefore(end)) {
            System.out.println("give new toy: " + upTo);
            upTo = upTo.plus(period);       // adds the period
        }
    }

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
        performAnimalEnrichment(start, end);
        // This code works fine - it adds a month to the date until it hits the end date
        // The problem is that this method can’t be reused
        // Solution: we use Java's class: PERIOD
        // solution with Period
        Period period = Period.ofMonths(1);
        performAnimalEnrichmentWithPeriod(start, end, period);
        // The method can add an arbitrary period of time that gets passed in
        // This allows us to reuse the same method for different periods of time as the zookeeper changes her mind

        // ■ There are five ways to create a Period class:
        Period annually = Period.ofYears(1);                                // every year
        Period quarterly = Period.ofMonths(3);                              // every 3 months
        Period everyThreeWeeks = Period.ofWeeks(3);                         // every 3 weeks
        Period everyOtherDay = Period.ofDays(2);                            // every 2 days
        Period everyYearAndWeek = Period.of(1, 0, 7);   // every year and 7 days

        // ! IMPORTANT: methods cannot be chained when creating a Period!
        // only the las method is used - because the Period of() methods are static methods
        Period wrong = Period.ofYears(1).ofWeeks(1);    // every week
        Period wrong1 = Period.ofYears(1);
        wrong1 = Period.ofWeeks(1);     // compiler warning here - suspicious

        // ■ Period format: P 1Y 2M 3D = Period #years #months #days
        // ex:
        System.out.println(Period.of(1, 2, 3));     // P1Y2M3D
        System.out.println(Period.ofMonths(3));                         // P3M - java omit what is with 0
        System.out.println(Period.of(0, 20, 47));   // P20M47D
        // It’s OK to have more days than are in a month and to have more months than are in a year
        System.out.println(Period.ofWeeks(3));                          // P21D
        // week is not one of the units a Period stores => a week is converted to 7 days

        // ■ With what objects can Period be used? -> can be used to LocalDate and LocalDateTime
        // on LocalTime -> we get UnsupportedTemporalTypeException
        LocalDate date = LocalDate.of(2015, 1, 20);
        LocalTime time = LocalTime.of(6, 15);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Period period1 = Period.ofMonths(1);
        System.out.println(date.plus(period1));
        System.out.println(dateTime.plus(period1));
        System.out.println(time.plus(period1));     // throws UnsupportedTemporalTypeException: Unsupported unit: Months
    }
}
