package com.stareating.nodeet;

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
}
