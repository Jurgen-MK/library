package kz.lib_mob_client.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtils {

    public static String getMonth(int month) {
        Locale locale = new Locale( "ru" , "RU" );
       return new SimpleDateFormat("LLLL", locale).format(month);
    }

}
