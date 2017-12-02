
package com.stareating.nodeet.network.entity;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("bookmarked")
    private Boolean mBookmarked;
    @SerializedName("content")
    private String mContent;
    @SerializedName("deleted")
    private Boolean mDeleted;
    @SerializedName("display_delete_tools")
    private Boolean mDisplayDeleteTools;
    @SerializedName("display_edit_tools")
    private Boolean mDisplayEditTools;
    @SerializedName("display_moderator_tools")
    private Boolean mDisplayModeratorTools;
    @SerializedName("display_move_tools")
    private Boolean mDisplayMoveTools;
    @SerializedName("downvoted")
    private Boolean mDownvoted;
    @SerializedName("downvotes")
    private Long mDownvotes;
    @SerializedName("editedISO")
    private String mEditedISO;
    @SerializedName("editor")
    private Object mEditor;
    @SerializedName("index")
    private Long mIndex;
    @SerializedName("pid")
    private String mPid;
    @SerializedName("replies")
    private Replies mReplies;
    @SerializedName("selfPost")
    private Boolean mSelfPost;
    @SerializedName("tid")
    private String mTid;
    @SerializedName("timestamp")
    private String mTimestamp;
    @SerializedName("timestampISO")
    private String mTimestampISO;
    @SerializedName("uid")
    private String mUid;
    @SerializedName("upvoted")
    private Boolean mUpvoted;
    @SerializedName("upvotes")
    private Long mUpvotes;
    @SerializedName("user")
    private User mUser;
    @SerializedName("votes")
    private Long mVotes;

    public Boolean getBookmarked() {
        return mBookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        mBookmarked = bookmarked;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Boolean getDeleted() {
        return mDeleted;
    }

    public void setDeleted(Boolean deleted) {
        mDeleted = deleted;
    }

    public Boolean getDisplayDeleteTools() {
        return mDisplayDeleteTools;
    }

    public void setDisplayDeleteTools(Boolean displayDeleteTools) {
        mDisplayDeleteTools = displayDeleteTools;
    }

    public Boolean getDisplayEditTools() {
        return mDisplayEditTools;
    }

    public void setDisplayEditTools(Boolean displayEditTools) {
        mDisplayEditTools = displayEditTools;
    }

    public Boolean getDisplayModeratorTools() {
        return mDisplayModeratorTools;
    }

    public void setDisplayModeratorTools(Boolean displayModeratorTools) {
        mDisplayModeratorTools = displayModeratorTools;
    }

    public Boolean getDisplayMoveTools() {
        return mDisplayMoveTools;
    }

    public void setDisplayMoveTools(Boolean displayMoveTools) {
        mDisplayMoveTools = displayMoveTools;
    }

    public Boolean getDownvoted() {
        return mDownvoted;
    }

    public void setDownvoted(Boolean downvoted) {
        mDownvoted = downvoted;
    }

    public Long getDownvotes() {
        return mDownvotes;
    }

    public void setDownvotes(Long downvotes) {
        mDownvotes = downvotes;
    }

    public String getEditedISO() {
        return mEditedISO;
    }

    public void setEditedISO(String editedISO) {
        mEditedISO = editedISO;
    }

    public Object getEditor() {
        return mEditor;
    }

    public void setEditor(Object editor) {
        mEditor = editor;
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

    public Replies getReplies() {
        return mReplies;
    }

    public void setReplies(Replies replies) {
        mReplies = replies;
    }

    public Boolean getSelfPost() {
        return mSelfPost;
    }

    public void setSelfPost(Boolean selfPost) {
        mSelfPost = selfPost;
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

    public Boolean getUpvoted() {
        return mUpvoted;
    }

    public void setUpvoted(Boolean upvoted) {
        mUpvoted = upvoted;
    }

    public Long getUpvotes() {
        return mUpvotes;
    }

    public void setUpvotes(Long upvotes) {
        mUpvotes = upvotes;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Long getVotes() {
        return mVotes;
    }

    public void setVotes(Long votes) {
        mVotes = votes;
    }

}
