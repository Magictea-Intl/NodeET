package com.stareating.nodeet.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 婷 on 2017/12/3.
 */

public class DateTimeFormatter {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateTimeInstance();

    public static String format(long millis){
        PrettyTime p = new PrettyTime(Locale.CHINESE);
        long milliseconds = new Date().getTime();
        milliseconds = milliseconds - millis/1000;
        return p.format(new Date(milliseconds));
    }

}
