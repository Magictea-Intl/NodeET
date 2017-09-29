package com.stareating.nodeet.ui.common;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by 婷 on 2017/9/29.
 */

//Typeface是字體。這個類是管理用到的字體的。
public class Typefaces {

    private static Typeface sAvatarTypeface;

    public static Typeface getAwesomeFont(Context context){
        if(sAvatarTypeface == null){
            sAvatarTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
        }
        return sAvatarTypeface;
    }
}
