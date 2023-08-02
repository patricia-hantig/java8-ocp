package com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class UsingResourceBundle {
    public static void main(String[] args) {
        // Resource bundle = contains the local specific objects to be used by a program
        // Resource bundle can be a property file or a Java class
        // Property file = file in a specific format with key/value pairs
        // are used in internationalization

        // ex: Our zoo program has been very successful and we have requests to use it at 3 more zoos
        // with resource bundles we can translate our application to multiple locales
        // so we have the following locales:
        Locale us_locale = new Locale("en", "US");
        Locale france = new Locale("fr", "FR");
        Locale englishCanada = new Locale("en", "CA");
        Locale frenchCanada = new Locale("fr", "CA");

        // ■ Property file format
        // most common syntax: key/value pairs => key = value
        // ex: animal=dolphin   or  animal:dolphin  or  animal dolphin
        // if line begins with # or ! => comment
        // spaces before or after the separator character are ignored
        // characters like \t and \n can be used
        // ex: firstPropertyFile.properties
        // printing it we get the following:
        Locale us_local = new Locale("en", "US");
        printFirstPropertyFile(us_local);
        System.out.println();


        // ■ Creating a Property File Resource Bundle
        // ex we need English and French property file resource bundles
        // we create 2 property files: Zoo_en.properties and Zoo_de.properties
        // the first program that uses a resource bundle to print this information
        Locale us = new Locale("en", "US");
        Locale de = new Locale("de", "DE");
        printProperties(us);
        System.out.println();
        printProperties(de);
        System.out.println();

        // since the resource bundle contains key\value pairs -> you can loop through them to list all the pairs
        // Resource bundle provides a method to get a set of all keys: keySet()
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.Zoo", us);
        Set<String> keys = resourceBundle.keySet();
        keys.stream().map(k -> k + " " + resourceBundle.getString(k)).forEach(System.out::println);

        // ■ Java has a class named Properties (in addition to ResourceBundle) - it's like a Map
        // Properties has additional features - including being able to pass a default
        // converting from ResourceBundle to Properties:
        Properties props = new Properties();
        resourceBundle.keySet().stream().forEach(k -> props.put(k, resourceBundle.getString(k)));
        // we went through each key and used a Consumer to add it to the Properties obj
        // get default value:
        System.out.println(props.getProperty("notReallyAProperty"));    // null - the property doesn't exist
        System.out.println(props.getProperty("notReallyAProperty", "default value")); // default value - the property wasn't found
        // we also have a get() method - but it doesn't allow default value

        // Return values for getProperty():
        // -----------------------------------------------------------------------
        // Key found?                       |       YES         |       NO
        // -----------------------------------------------------------------------
        // getProperty("key")               |       value       |       null
        // getProperty("key", "default")    |       value       |       default
        // -----------------------------------------------------------------------


        // ■ Creating a Java Class Resource Bundle
        // are used because property files allow only String values
        // Java class resource bundles allow any Java type as the value, the key is always String
        // name of a java class resource bundle is the same as for a property file

        // ex we have Zoo_en.java
        // There are two main advantages of using a Java class instead of a property file for a resource bundle:
        //■■ You can use a value type that is not a String.
        //■■ You can create the values of the properties at runtime.
        // we realize that we need to collect taxes differently in each country
        // we decide to set up just the code for the United States first
        // we create Tax_en_US.java


        // ■ Determining which Resource Bundle to use
        // there are 2 methods for getting a resource bundle:
        // ResourceBundle.getBundle("name");           // this one uses the default locale
        // Locale locale = new Locale("ro", "RO");
        // ResourceBundle.getBundle("name", locale);

        // ex of how Java searches for the best available resource bundle
        // for resource bundle Zoo with locale: new Locale("fr", "FR")
        // when the default locale is US English
        // The steps are the following:
        // 1. Search for Zoo_fr_FR.java             - the requested locale
        // 2. Search for Zoo_fr_FR.properties       - the requested locale
        // 3. Search for Zoo_fr.java                - the language we requested with no country
        // 4. Search for Zoo_fr.properties          - the language we requested with no country
        // 5. Search for Zoo_en_US.java             - the default locale
        // 6. Search for Zoo_en_US.properties       - the default locale
        // 7. Search for Zoo_en.java                - the default locale with no language
        // 8. Search for Zoo_en.properties          - the default locale with no language
        // 9. Search for Zoo.java                   - the default bundle
        // 10. Search for Zoo.properties            - the default bundle
        // 11. If still not found, throw MissingResourceException

        // the steps are:
        // look for the Java class and after for the property file
        // first drop the country and then the language
        // default locale and default resource bundle are last

        // ex How many files do you think Java would need to look for to find the resource bundle with the code?
        Locale.setDefault(new Locale("hi"));
        ResourceBundle rb = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.Zoo", new Locale("en"));
        // 1. Zoo_hi.java, 2. Zoo_hi.properties, 3. Zoo_en.java, 4. Zoo_en.properties, 5. Zoo.java, 6. Zoo.properties

        // listing the parent resource bundles
        // ex: Zoo_fr_FR.java       ->  files keys can come from: Zoo_fr_FR.java, Zoo_fr.java, Zoo.java
        // ex: Zoo_fr.properties    ->  files keys can come from: Zoo_fr.properties, Zoo.properties

        // example 2:
        // we have the following property files: PatriZoo.properties, PatriZoo_en.properties, PatriZoo_en_CA.properties, PatriZoo_fr.properties, PatriZoo_fr_CA.properties
        // suppose that we have a visitor from Quebec ( default locale of French Canada) who has asked to provide information in english
        Locale local = new Locale("en", "CA");
        ResourceBundle resourceBundl = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.PatriZoo", local);
        System.out.print(resourceBundl.getString("hello"));     // doesn’t find a match for the key 'hello' in PatriZoo_en_CA.properties file and goes in the hierarchy -> found in PatriZoo_en.properties file
        System.out.print(". ");
        System.out.print(resourceBundl.getString("name"));      // doesn’t find a match for the key 'name' in PatriZoo_en_CA.properties file and goes in the top of hierarchy -> found in PatriZoo.properties file
        System.out.print(" ");
        System.out.print(resourceBundl.getString("open"));      // doesn’t find a match for the key 'open' in PatriZoo_en_CA.properties file and goes in the hierarchy -> found in PatriZoo_en.properties file
        System.out.print(" ");
        System.out.print(resourceBundl.getString("visitor"));   // found in PatriZoo_en_CA.properties file
        System.out.println();


        // ■ Handling Variables Inside Resource Bundle
        // In real programs, it is common to substitute variables in the middle of a resource bundle string
        // the convention is to use a number inside brackets like {0}
        // although Java resource bundles don't support this -> MessageFormat class does
        // we have file: handlingVariablesInsideResourceBundles.properties
        Locale loc = new Locale("en", "CA");
        ResourceBundle resBundle = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.handlingVariablesInsideResourceBundles", loc);
        String format = resBundle.getString("helloByName");
        String formatted = MessageFormat.format(format, "Patricia");
        System.out.println(formatted);


    }

    public static void printProperties(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.Zoo", locale);
        System.out.println(resourceBundle.getString("hello"));
        System.out.println(resourceBundle.getString("open"));
    }

    public static void printFirstPropertyFile(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.firstPropertyFile", locale);
        System.out.println(resourceBundle.getString("key"));
        System.out.println(resourceBundle.getString("long"));
    }
}
