package com.stareating.nodeet.network.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stardust on 2017/11/8.
 */

public class CommonResponse<Payload> {

    @SerializedName("code")
    private String mCode;

    @SerializedName("payload")
    private Payload mPayload;

    public String getCode() {
        return mCode;
    }

    public Payload getPayload() {
        return mPayload;
    }
}
