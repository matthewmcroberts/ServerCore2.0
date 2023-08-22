package com.matthew.plugin.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    /**
     * State whether a string is also the correct format for an integer
     *
     * @param string - number being checked
     * @return if string can be parsed as an integer
     */
    public static boolean isNumeric(String string) {

        int intValue;

        if (string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Return the difference in time for start_date and end_date as a one-dimensional array formatted as follows:
     * - {Years, Days, Hours, Minutes, Seconds}
     *
     * @param start_date - Issued
     * @param end_date   - Expiration
     */
    public static String findTimeDifference(String start_date, String end_date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            //time difference in milliseconds
            long timeDifference = d2.getTime() - d1.getTime();

            //conversions
            long diffInSeconds = (timeDifference / 1000) % 60;

            long diffInMinutes = (timeDifference / (1000 * 60)) % 60;

            long diffInHours = (timeDifference / (1000 * 60 * 60)) % 24;

            long diffInYears = (timeDifference / (1000L * 60 * 60 * 24 * 365));

            long diffInDays = (timeDifference / (1000 * 60 * 60 * 24)) % 365;

            return "[" + diffInYears + "," + diffInDays + "," + diffInHours + "," + diffInMinutes + "," + diffInSeconds + "]";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
