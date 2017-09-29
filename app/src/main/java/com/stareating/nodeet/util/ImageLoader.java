package com.stareating.nodeet.util;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by 婷 on 2017/9/29.
 */

public class ImageLoader {


    public static Drawable loadUrl(String url){
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
