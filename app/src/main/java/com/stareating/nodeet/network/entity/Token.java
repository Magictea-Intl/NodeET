package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stardust on 2017/11/8.
 */

public class Token {

    @SerializedName("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }
}
