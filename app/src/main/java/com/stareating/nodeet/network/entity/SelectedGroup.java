
package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;

public class SelectedGroup {

    @SerializedName("icon")
    private String mIcon;
    @SerializedName("labelColor")
    private String mLabelColor;
    @SerializedName("name")
    private String mName;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("userTitle")
    private String mUserTitle;

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getLabelColor() {
        return mLabelColor;
    }

    public void setLabelColor(String labelColor) {
        mLabelColor = labelColor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public String getUserTitle() {
        return mUserTitle;
    }

    public void setUserTitle(String userTitle) {
        mUserTitle = userTitle;
    }

}
