package com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

// format numbers, currency and dates
// package: java.text
public class FormattingNumbers {
    public static void main(String[] args) {

        // ■■ Format and parse numbers and currency

        // first step is to create a NumberFormat - this class provides methods to get the desired formatter
        // Factory methods to get a NumberFormat:
        // --------------------------------------------------------------------------------------
        // Description                      |       Using Default Locale and a Specified Locale
        // --------------------------------------------------------------------------------------
        //  general purpose formatter       |   NumberFormat.getInstance()
        //                                  |   NumberFormat.getInstance(locale)
        //  same as getInstance()           |   NumberFormat.getNumberInstance()
        //                                  |   NumberFormat.getNumberInstance(locale)
        //  for formatting monetary         |   NumberFormat.getCurrencyInstance()
        //  amounts                         |   NumberFormat.getCurrencyInstance(locale)
        //  for formatting percentages      |   NumberFormat.getPercentInstance()
        //                                  |   NumberFormat.getPercentInstance(locale)
        //  rounds decimal values           |   NumberFormat.getIntegerInstance()
        //  before displaying               |   NumberFormat.getIntegerInstance(locale)
        // --------------------------------------------------------------------------------------

        // when you have NumberFormat instance  - you can call format() = to turn a number into a String
        //                                      - you can call parse() = to turn a String into a number

        // !IMPORTANT: Format classes are not thread-safe => Do not store them in instance variables or static variables

        // ■ Formatting == NR -> String
        // ex for formatting numbers:
        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;
        NumberFormat us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(attendeesPerMonth));
        NumberFormat g = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(g.format(attendeesPerMonth));
        NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(attendeesPerMonth));

        // ex for formatting currency:
        double price = 48;  // in the real world we use int or BigDecimal -> because with double we can lose pennies
        NumberFormat us_currency = NumberFormat.getCurrencyInstance();
        System.out.println(us_currency.format(price));
        Locale ro_locale = new Locale("ro", "RO");
        NumberFormat ro_currency = NumberFormat.getCurrencyInstance(ro_locale);
        System.out.println(ro_currency.format(price));
        Locale ar_locale = new Locale("es", "AR");
        NumberFormat ar_currency = NumberFormat.getCurrencyInstance(ar_locale);
        System.out.println(ar_currency.format(price));

        // ■ Parsing == String -> NR
        // NumberFormat class - has a parse method for parsing a String into a number using a specific locale
        // parse methods for the different types of formats throw the checked exception ParseException if they fail to parse
        // if you see parsing logic inside a method -> make sure that ParseException or Exception is handled or declared

        // ex:
        String s = "40.45";
        NumberFormat en = NumberFormat.getInstance(Locale.US);
        NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
        try {
            System.out.println(en.parse(s));    // in US dot is part of a number
            System.out.println(fr.parse(s));    // in FR it's not used decimal point to separate numbers
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // ex: what does Java with extra characters when parsing?
        // ! parse() method - parses only until it reaches a character that can be parsed,
        // it stops and returns what it parsed until it found that character
        NumberFormat nf = NumberFormat.getInstance();
        String one = "456abc";
        String two = "-2.5165x10";
        String three = "x85.3";
        try {
            System.out.println(nf.parse(one));
            System.out.println(nf.parse(two));
            System.out.println(nf.parse(three));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        // ex for parsing currency:
        String amount = "$92,807.99";
        NumberFormat cf = NumberFormat.getCurrencyInstance();
        try {
            double value = (double) cf.parse(amount);
            System.out.println(value);
            // The return value of parse is a Number object.
            // Number is the parent class of all the java.lang wrapper classes -> the return value can be cast to its appropriate data type.
            // The Number is cast to a Double and then automatically unboxed into a double.
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // ■■ Formatting Dates and Times

        // ■ Formatting == Date -> String
        // we can use LocalDate and LocalTime to get data ot of them - more work than necessary
        LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
        System.out.println(date.getDayOfWeek());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println(date.getDayOfYear());

        // or we can use DateTimeFormatter class from package java.time.format
        // DateTimeFormatter can be used to format any type of date/time obj
        LocalDate localDate = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime localTime = LocalTime.of(11, 12, 34);
        LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // predefined formats: SHORT, MEDIUM, FULL etc
        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(shortDateTime.format(dateTime));
        System.out.println(shortDateTime.format(localDate));
        //System.out.println(shortDateTime.format(localTime)); -> this throws UnsupportedTemporalTypeException
        // the lines above are equivalent with the next ones
        System.out.println(dateTime.format(shortDateTime));
        System.out.println(localDate.format(shortDateTime));
        //System.out.println(localTime.format(shortDateTime)); -> this throws UnsupportedTemporalTypeException

        // create your own formatter:
        DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(shortF.format(dateTime));
        System.out.println(mediumF.format(dateTime));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
        System.out.println(dateTime.format(formatter));
        // symbols: month: M(1), MM(01), MMM(Jan), MMMM(January); day: d(1), dd(01); year: yy(20), yyyy(2020); hour: h(1), hh(01); minute: m(1), mm(01)

        // ofLocalized methods:
        // DateTimeFormatter f = DateTimeFormatter.______(FormatStyle.SHORT);   |   Calling f.format(localDate) |   Calling f.format(local/zonedDateTime)   |   Calling f.format(localTime)
        // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //                              ofLocalizedDate                         |   OK, shows whole obj         |           OK, shows date part             |   throws runtime exception
        //                              ofLocalizedDateTime                     |   throws runtime exception    |           OK, shows whole obj             |   throws runtime exception
        //                              ofLocalizedTime                         |   throws runtime exception    |           OK, shows time part             |   OK, shows whole obj
        // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // formatting dates before Java 8
        // old way:
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        //simpleDateFormat.format(jan3);
        // new way:
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("hh:mm");
        System.out.println(dateTime.format(formatter1));

        // which line will throw exception?
        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm");
        f.format(dateTime);
        // f.format(localDate); -> this one
        f.format(localTime);

        // ■ Parsing == String -> Date
        // parse() takes a formatter as well and if you don't specify one it will use the default
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date1 = LocalDate.parse("01 02 2015", dateTimeFormatter); // custom formatter
        LocalTime time1 = LocalTime.parse("11:22"); // default value
        System.out.println(date1);
        System.out.println(time1);

        // if anything goes wrong - Java throws a runtime exception (ex: a format that doesn’t match the String to be parsed or an invalid date)

    }
}
