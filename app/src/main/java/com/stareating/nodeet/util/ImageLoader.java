package com.stareating.nodeet.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by 婷 on 2017/9/29.
 */

public class ImageLoader {


    public static Drawable loadUrl(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void loadInto(ImageView imageView, String url) {
        // TODO: 2017/11/1  使用线程池
        new Thread(() -> {
            Drawable d = loadUrl(url);
            imageView.post(() -> imageView.setImageDrawable(d));
        }).start();
    }
}
