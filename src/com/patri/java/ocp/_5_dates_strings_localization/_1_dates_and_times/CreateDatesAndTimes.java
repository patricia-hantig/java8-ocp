package com.patri.java.ocp._5_dates_strings_localization._1_dates_and_times;

import java.awt.dnd.DropTarget;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateDatesAndTimes {

    // we need an import to work with dates and times classes: import java.time.*

    public static void main(String[] args) {

        // we have 4 options to create Dates and Times
        // LocalDate - contains just a date - no time and no time zone -> ex: 13.10.2020
        // LocalTime - contains just a time - no date and no time zone -> ex: midnight
        // LocalDateTime - contains date and time - no time zone -> ex: 13.10.2020 20:00
        // ZonedDateTime - contains date, time and time zone -> ex: 13.10.2020 20:00 PST

        // all the above classes have a static method: now() - gives the current date and time
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());

        // Time zone offset: +2:00 == GMT+2 == UTC+2 (GMT = Greenwich Mean Time and UTC = Coordinated Universal Time)

        // ■■ LocalDate
        // The method signatures are as follows:
        // public static LocalDate of(int year, int month, int dayOfMonth)
        // public static LocalDate of(int year, Month month, int dayOfMonth)
        LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
        System.out.println(date1);
        LocalDate date2 = LocalDate.of(2015, 1, 20);
        System.out.println(date2);
        // Month is an enum => can not be compared to one
        Month month = Month.JANUARY;
        //boolean b1 = month == 1;      // DOES NOT COMPILE
        boolean b2 = month == Month.APRIL;
        System.out.println(b2);         // false

        // ■■ LocalTime
        // The method signatures are as follows:
        // public static LocalTime of(int hour, int minute)
        // public static LocalTime of(int hour, int minute, int second)
        // public static LocalTime of(int hour, int minute, int second, int nanos)
        LocalTime time1 = LocalTime.of(6, 15); // hour and minutes
        System.out.println(time1);
        LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
        System.out.println(time2);
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
        System.out.println(time3);

        // ■■ LocalDateTime
        // The method signatures are as follows:
        // public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute)
        // public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second)
        // public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanos)
        // public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute)
        // public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second)
        // public static LocalDateTime of(int year, Month month, int dayOfMonth, int hour, int minute, int second, int nanos)
        // public static LocalDateTime of(LocalDate date, LocalTime time) - You can combine dates and times into one object
        LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        System.out.println(dateTime1);
        LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
        System.out.println(dateTime2);

        // ■■ ZonedDateTime
        // we need to get the desired time zone
        ZoneId zone = ZoneId.of("US/Pacific");
        // The method signatures are as follows:
        // public static ZonedDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanos, ZoneId zone)
        // public static ZonedDateTime of(LocalDate date, LocalTime time, ZoneId zone)
        // public static ZonedDateTime of(LocalDateTime dateTime, ZoneId zone)
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2015, 1, 20, 6, 15, 30, 200, zone);
        System.out.println(zonedDateTime1);
        ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
        System.out.println(zoned2);
        ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);
        System.out.println(zoned3);
        //!!!! There isn’t an option to pass in the Month enum -> oversight from the API creators and something that will be fixed in future versions of Java


        // ■ Finding a Time Zone
        System.out.println(ZoneId.systemDefault()); // your time zone
        // all time zones from America, from US and from Europe
        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("US") || z.contains("America") || z.contains("Europe"))
                .sorted().forEach(System.out::println);

        // Date and Time classes have private constructors => we have to use the factory's static methods
        // LocalDate d = new LocalDate(); // DOES NOT COMPILE
        // what happens when you pass invalid numbers to of()?
        // LocalDate.of(2015, Month.JANUARY, 32); // throws DateTimeException


        // ■ Creating dates before Java 8
        // there wasn't a way to create just a date without time -> using Date class and Calendar class
        // ex:
        Date jan = new Date(2015, Calendar.JANUARY, 1);
        System.out.println(jan);

        // ■ Old vs. new way of creating dates
        System.out.println("Old vs. new way of creating dates");

        // importing
        // old way:     import java.util.*;
        // new way:     import java.time.*;

        // creating an obj with the current date
        // old way:
        Date d = new Date();
        System.out.println(d);          // Tue Jun 02 14:00:45 PDT 2020
        // new way:
        LocalDate date = LocalDate.now();
        System.out.println(date);       //2020-06-02

        // creating an obj with the current date and time
        // old way:
        Date dt = new Date();
        System.out.println(dt);         // Tue Jun 02 14:00:45 PDT 2020
        // new way:
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);   // 2020-06-02T14:00:45.387

        // creating an obj representing January 1, 2015
        // old way:
        Calendar c = Calendar.getInstance();
        c.set(2015, Calendar.JANUARY, 1);
        Date january = c.getTime();
        System.out.println(january);    // Thu Jan 01 14:00:45 PST 2015
        //or
        Calendar calendar = new GregorianCalendar(2015, Calendar.JANUARY, 1);
        Date january1 = calendar.getTime();
        System.out.println(january1);   // Thu Jan 01 00:00:00 PST 2015
        // new way:
        LocalDate jan1 = LocalDate.of(2015, Month.JANUARY, 1);
        System.out.println(jan1);       // 2015-01-01

        // creating an obj representing January 1, 2015 without the constant
        // old way:
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2015, 0, 1);
        Date january2 = calendar1.getTime();
        System.out.println(january2);   // Thu Jan 01 14:00:45 PST 2015
        // new way:
        LocalDate jan2 = LocalDate.of(2015, 1, 1);
        System.out.println(jan2);       // 2015-01-01
    }//love you
}
