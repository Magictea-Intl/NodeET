package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stardust on 2017/9/16.
 */

public class User {
    int uid;
    String userName;
    String picture;

    @SerializedName("icon:bgColor")
    String iconBgColor;

    @SerializedName("icon:text")
    String iconText;

    public int getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    public String getPicture() {
        return picture;
    }

    public String getIconBgColor() {
        return iconBgColor;
    }

    public String getIconText() {
        return iconText;
    }
}
