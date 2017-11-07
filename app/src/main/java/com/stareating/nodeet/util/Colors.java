package com.stareating.nodeet.util;

import android.graphics.Color;

/**
 * Created by Stardust on 2017/11/7.
 */

public class Colors {

    public static int parse(String color) {
        if (color.startsWith("#")) {
            String c = color.substring(1);
            //parse color such as #fff, #123
            if (c.length() == 3) {
                return Color.rgb(to8Bits(c.charAt(0)), to8Bits(c.charAt(0)), to8Bits(c.charAt(0)));
            }
        }
        return Color.parseColor(color);
    }

    private static int to8Bits(char c) {
        int n = Integer.valueOf(c + "", 16);
        return n * 17;
    }
}
