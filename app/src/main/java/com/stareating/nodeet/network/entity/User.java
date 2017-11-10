
package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class User {

    @SerializedName("banned")
    private String mBanned;
    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("icon:bgColor")
    private String mIconBgColor;
    @SerializedName("icon:text")
    private String mIconText;
    @SerializedName("picture")
    private String mPicture;
    @SerializedName("postcount")
    private String mPostcount;
    @SerializedName("reputation")
    private String mReputation;
    @SerializedName("signature")
    private String mSignature;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("uid")
    private String mUid;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("userslug")
    private String mUserslug;

    public String getBanned() {
        return mBanned;
    }

    public void setBanned(String banned) {
        mBanned = banned;
    }

    public String getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname = fullname;
    }

    public String getIconBgColor() {
        return mIconBgColor;
    }

    public void setIconBgColor(String iconBgColor) {
        mIconBgColor = iconBgColor;
    }

    public String getIconText() {
        return mIconText;
    }

    public void setIconText(String iconText) {
        mIconText = iconText;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    public String getPostcount() {
        return mPostcount;
    }

    public void setPostcount(String postcount) {
        mPostcount = postcount;
    }

    public String getReputation() {
        return mReputation;
    }

    public void setReputation(String reputation) {
        mReputation = reputation;
    }

    public String getSignature() {
        return mSignature;
    }

    public void setSignature(String signature) {
        mSignature = signature;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUserslug() {
        return mUserslug;
    }

    public void setUserslug(String userslug) {
        mUserslug = userslug;
    }

}
