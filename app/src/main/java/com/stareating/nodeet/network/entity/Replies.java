
package com.stareating.nodeet.network.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Replies {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("hasMore")
    private Boolean mHasMore;
    @SerializedName("text")
    private String mText;
    @SerializedName("users")
    private List<User> mUsers;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Boolean getHasMore() {
        return mHasMore;
    }

    public void setHasMore(Boolean hasMore) {
        mHasMore = hasMore;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

}
