
package com.stareating.nodeet.network.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

//@Generated("net.hexar.json2pojo")
public class Topic {

    @SerializedName("bookmark")
    private Object mBookmark;
    @SerializedName("category")
    private Category mCategory;
    @SerializedName("cid")
    private String mCid;
    @SerializedName("deleted")
    private Boolean mDeleted;
    @SerializedName("icons")
    private List<Object> mIcons;
    @SerializedName("ignored")
    private Boolean mIgnored;
    @SerializedName("index")
    private Long mIndex;
    @SerializedName("isOwner")
    private Boolean mIsOwner;
    @SerializedName("lastposttime")
    private String mLastposttime;
    @SerializedName("lastposttimeISO")
    private String mLastposttimeISO;
    @SerializedName("locked")
    private Boolean mLocked;
    @SerializedName("mainPid")
    private String mMainPid;
    @SerializedName("pinned")
    private Boolean mPinned;
    @SerializedName("postcount")
    private String mPostcount;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("tags")
    private List<Object> mTags;
    @SerializedName("teaser")
    private Teaser mTeaser;
    @SerializedName("teaserPid")
    private String mTeaserPid;
    @SerializedName("tid")
    private String mTid;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("timestampISO")
    private String mTimestampISO;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("titleRaw")
    private String mTitleRaw;
    @SerializedName("uid")
    private String mUid;
    @SerializedName("unread")
    private Boolean mUnread;
    @SerializedName("unreplied")
    private Boolean mUnreplied;
    @SerializedName("user")
    private User mUser;
    @SerializedName("viewcount")
    private String mViewcount;

    public Object getBookmark() {
        return mBookmark;
    }

    public void setBookmark(Object bookmark) {
        mBookmark = bookmark;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public String getCid() {
        return mCid;
    }

    public void setCid(String cid) {
        mCid = cid;
    }

    public boolean isDeleted() {
        return mDeleted;
    }

    public void setDeleted(Boolean deleted) {
        mDeleted = deleted;
    }

    public List<Object> getIcons() {
        return mIcons;
    }

    public void setIcons(List<Object> icons) {
        mIcons = icons;
    }

    public Boolean getIgnored() {
        return mIgnored;
    }

    public void setIgnored(Boolean ignored) {
        mIgnored = ignored;
    }

    public Long getIndex() {
        return mIndex;
    }

    public void setIndex(Long index) {
        mIndex = index;
    }

    public Boolean getIsOwner() {
        return mIsOwner;
    }

    public void setIsOwner(Boolean isOwner) {
        mIsOwner = isOwner;
    }

    public String getLastposttime() {
        return mLastposttime;
    }

    public void setLastposttime(String lastposttime) {
        mLastposttime = lastposttime;
    }

    public String getLastposttimeISO() {
        return mLastposttimeISO;
    }

    public void setLastposttimeISO(String lastposttimeISO) {
        mLastposttimeISO = lastposttimeISO;
    }

    public Boolean getLocked() {
        return mLocked;
    }

    public void setLocked(Boolean locked) {
        mLocked = locked;
    }

    public String getMainPid() {
        return mMainPid;
    }

    public void setMainPid(String mainPid) {
        mMainPid = mainPid;
    }

    public Boolean getPinned() {
        return mPinned;
    }

    public void setPinned(Boolean pinned) {
        mPinned = pinned;
    }

    public String getPostcount() {
        return mPostcount;
    }

    public void setPostcount(String postcount) {
        mPostcount = postcount;
    }

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }

    public List<Object> getTags() {
        return mTags;
    }

    public void setTags(List<Object> tags) {
        mTags = tags;
    }

    public Teaser getTeaser() {
        return mTeaser;
    }

    public void setTeaser(Teaser teaser) {
        mTeaser = teaser;
    }

    public String getTeaserPid() {
        return mTeaserPid;
    }

    public void setTeaserPid(String teaserPid) {
        mTeaserPid = teaserPid;
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

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitleRaw() {
        return mTitleRaw;
    }

    public void setTitleRaw(String titleRaw) {
        mTitleRaw = titleRaw;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public Boolean getUnread() {
        return mUnread;
    }

    public void setUnread(Boolean unread) {
        mUnread = unread;
    }

    public Boolean getUnreplied() {
        return mUnreplied;
    }

    public void setUnreplied(Boolean unreplied) {
        mUnreplied = unreplied;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getViewcount() {
        return mViewcount;
    }

    public void setViewcount(String viewcount) {
        mViewcount = viewcount;
    }

}
