
package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
public class Teaser {

    @SerializedName("content")
    private String mContent;
    @SerializedName("index")
    private Long mIndex;
    @SerializedName("pid")
    private String mPid;
    @SerializedName("tid")
    private String mTid;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("timestampISO")
    private String mTimestampISO;
    @SerializedName("uid")
    private String mUid;
    @SerializedName("user")
    private User mUser;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Long getIndex() {
        return mIndex;
    }

    public void setIndex(Long index) {
        mIndex = index;
    }

    public String getPid() {
        return mPid;
    }

    public void setPid(String pid) {
        mPid = pid;
    }

    public String getTid() {
        return mTid;
    }

    public void setTid(String tid) {
        mTid = tid;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public String getTimestampISO() {
        return mTimestampISO;
    }

    public void setTimestampISO(String timestampISO) {
        mTimestampISO = timestampISO;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
