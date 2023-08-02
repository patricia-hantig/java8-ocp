package com.patri.java.ocp._1_advanced_class_design._5_enums;

public class EnumsAndSwitch {
    // ■ Enums are used in switch statements
    public static void main(String[] args) {
        Season summer = Season.SUMMER;
        switch (summer) {
            case WINTER:
                System.out.println("Get out the sled");
                break;
            case SUMMER:
                System.out.println("Time for the pool");
                break;
            default:
                System.out.println("Is it summer yet?");
        }

        /* switch (summer) {
            case 0:     //DOES NOT COMPILE
                System.out.println("Get out the sled");
                break;
        }*/
        // You can’t compare an int with an enum
    }
    // Java treats the enum type as implied - if you were to type case Season.WINTER, it would not compile
}
