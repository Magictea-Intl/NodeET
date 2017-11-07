
package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
public class Category {

    @SerializedName("bgColor")
    private String mBgColor;
    @SerializedName("cid")
    private String mCid;
    @SerializedName("color")
    private String mColor;
    @SerializedName("disabled")
    private Boolean mDisabled;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("image")
    private Object mImage;
    @SerializedName("name")
    private String mName;
    @SerializedName("slug")
    private String mSlug;

    public String getBgColor() {
        return mBgColor;
    }

    public void setBgColor(String bgColor) {
        mBgColor = bgColor;
    }

    public String getCid() {
        return mCid;
    }

    public void setCid(String cid) {
        mCid = cid;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public Boolean getDisabled() {
        return mDisabled;
    }

    public void setDisabled(Boolean disabled) {
        mDisabled = disabled;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Object getImage() {
        return mImage;
    }

    public void setImage(Object image) {
        mImage = image;
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

}
