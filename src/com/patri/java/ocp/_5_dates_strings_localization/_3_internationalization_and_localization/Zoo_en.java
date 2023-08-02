package com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization;

import java.util.ListResourceBundle;

// ex: Java Class Resource Bundle
public class Zoo_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"hello", "Hello"},
                {"open", "The zoo is open "}
        };
    }
}
