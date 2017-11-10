package com.stareating.nodeet;

import android.app.Application;

import com.stareating.nodeet.network.NodeBBService;

/**
 * Created by 婷 on 2017/11/10.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NodeBBService.init(this);
    }
}
