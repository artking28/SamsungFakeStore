package org.arthurandrade.samsungfakestorefront.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Long seconds(Long n) {
        return n*1000;
    }

    public static Long minutes(Long n) {
        return n*seconds(60L);
    }

    public static String formatData(Date date) {
        if (date == null) {
            return "null";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
        return sdf.format(date);
    }
}
