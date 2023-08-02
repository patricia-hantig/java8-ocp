package com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Tax_en_US extends ListResourceBundle { // extends the ListResourceBundle so that we can define a resource bundle
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"tax", new USTaxCode()}
        };
    }

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization.Tax", Locale.US);
        // we specify the name of the package. name of class
        System.out.println(resourceBundle.getObject("tax")); // here we see how to retrieve a non-String resource bundle
    }
}
