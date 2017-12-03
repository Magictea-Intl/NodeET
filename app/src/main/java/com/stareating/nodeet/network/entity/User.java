
package com.stareating.nodeet.network.entity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class User {

    @SerializedName("age")
    private Long mAge;
    @SerializedName("banned")
    private Boolean mBanned;
    @SerializedName("birthday")
    private String mBirthday;
    @SerializedName("bodyClass")
    private String mBodyClass;
    @SerializedName("canBan")
    private Boolean mCanBan;
    @SerializedName("canChangePassword")
    private Boolean mCanChangePassword;
    @SerializedName("canEdit")
    private Boolean mCanEdit;
    @SerializedName("cover:position")
    private String mCoverPosition;
    @SerializedName("cover:url")
    private String mCoverUrl;
    @SerializedName("disableSignatures")
    private Boolean mDisableSignatures;
    @SerializedName("downvote:disabled")
    private Boolean mDownvoteDisabled;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("emailClass")
    private String mEmailClass;
    @SerializedName("email:confirmed")
    private Boolean mEmailConfirmed;
    @SerializedName("email:disableEdit")
    private Boolean mEmailDisableEdit;
    @SerializedName("followerCount")
    private long mFollowerCount;
    @SerializedName("followingCount")
    private long mFollowingCount;
    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("groups")
    private List<Object> mGroups;
    @SerializedName("hasPrivateChat")
    private Long mHasPrivateChat;
    @SerializedName("icon:bgColor")
    private String mIconBgColor;
    @SerializedName("icon:text")
    private String mIconText;
    @SerializedName("ips")
    private List<String> mIps;
    @SerializedName("isAdmin")
    private Boolean mIsAdmin;
    @SerializedName("isAdminOrGlobalModerator")
    private Boolean mIsAdminOrGlobalModerator;
    @SerializedName("isAdminOrGlobalModeratorOrModerator")
    private Boolean mIsAdminOrGlobalModeratorOrModerator;
    @SerializedName("isFollowing")
    private Boolean mIsFollowing;
    @SerializedName("isGlobalModerator")
    private Boolean mIsGlobalModerator;
    @SerializedName("isModerator")
    private Boolean mIsModerator;
    @SerializedName("isSelf")
    private Boolean mIsSelf;
    @SerializedName("isSelfOrAdminOrGlobalModerator")
    private Boolean mIsSelfOrAdminOrGlobalModerator;
    @SerializedName("isTargetAdmin")
    private Boolean mIsTargetAdmin;
    @SerializedName("joindate")
    private String mJoindate;
    @SerializedName("joindateISO")
    private String mJoindateISO;
    @SerializedName("lastonline")
    private String mLastonline;
    @SerializedName("lastonlineISO")
    private String mLastonlineISO;
    @SerializedName("lastposttime")
    private String mLastposttime;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("loggedIn")
    private Boolean mLoggedIn;
    @SerializedName("moderationNote")
    private String mModerationNote;
    @SerializedName("nextStart")
    private Long mNextStart;
    @SerializedName("passwordExpiry")
    private String mPasswordExpiry;
    @SerializedName("picture")
    private String mPicture;
    @SerializedName("postcount")
    private String mPostcount;
    @SerializedName("posts")
    private List<Object> mPosts;
    @SerializedName("profile_links")
    private List<Object> mProfileLinks;
    @SerializedName("profileviews")
    private String mProfileviews;
    @SerializedName("relative_path")
    private String mRelativePath;
    @SerializedName("reputation")
    private String mReputation;
    @SerializedName("reputation:disabled")
    private Boolean mReputationDisabled;
    @SerializedName("showHidden")
    private Boolean mShowHidden;
    @SerializedName("signature")
    private String mSignature;
    @SerializedName("sso")
    private List<Object> mSso;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("theirid")
    private String mTheirid;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("topiccount")
    private String mTopiccount;
    @SerializedName("uid")
    private String mUid;
    @SerializedName("uploadedpicture")
    private String mUploadedpicture;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("username:disableEdit")
    private Boolean mUsernameDisableEdit;
    @SerializedName("userslug")
    private String mUserslug;
    @SerializedName("website")
    private String mWebsite;
    @SerializedName("websiteLink")
    private String mWebsiteLink;
    @SerializedName("websiteName")
    private String mWebsiteName;
    @SerializedName("yourid")
    private Long mYourid;

    public Long getAge() {
        return mAge;
    }

    public void setAge(Long age) {
        mAge = age;
    }

    public Boolean getBanned() {
        return mBanned;
    }

    public void setBanned(Boolean banned) {
        mBanned = banned;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }

    public String getBodyClass() {
        return mBodyClass;
    }

    public void setBodyClass(String bodyClass) {
        mBodyClass = bodyClass;
    }

    public Boolean getCanBan() {
        return mCanBan;
    }

    public void setCanBan(Boolean canBan) {
        mCanBan = canBan;
    }

    public Boolean getCanChangePassword() {
        return mCanChangePassword;
    }

    public void setCanChangePassword(Boolean canChangePassword) {
        mCanChangePassword = canChangePassword;
    }

    public Boolean getCanEdit() {
        return mCanEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        mCanEdit = canEdit;
    }

    public String getCoverPosition() {
        return mCoverPosition;
    }

    public void setCoverPosition(String coverPosition) {
        mCoverPosition = coverPosition;
    }

    public String getCoverUrl() {
        return mCoverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        mCoverUrl = coverUrl;
    }

    public Boolean getDisableSignatures() {
        return mDisableSignatures;
    }

    public void setDisableSignatures(Boolean disableSignatures) {
        mDisableSignatures = disableSignatures;
    }

    public Boolean getDownvoteDisabled() {
        return mDownvoteDisabled;
    }

    public void setDownvoteDisabled(Boolean downvoteDisabled) {
        mDownvoteDisabled = downvoteDisabled;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getEmailClass() {
        return mEmailClass;
    }

    public void setEmailClass(String emailClass) {
        mEmailClass = emailClass;
    }

    public Boolean getEmailConfirmed() {
        return mEmailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        mEmailConfirmed = emailConfirmed;
    }

    public Boolean getEmailDisableEdit() {
        return mEmailDisableEdit;
    }

    public void setEmailDisableEdit(Boolean emailDisableEdit) {
        mEmailDisableEdit = emailDisableEdit;
    }

    public Long getFollowerCount() {
        return mFollowerCount;
    }

    public void setFollowerCount(Long followerCount) {
        mFollowerCount = followerCount;
    }

    public Long getFollowingCount() {
        return mFollowingCount;
    }

    public void setFollowingCount(Long followingCount) {
        mFollowingCount = followingCount;
    }

    public String getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname = fullname;
    }

    public List<Object> getGroups() {
        return mGroups;
    }

    public void setGroups(List<Object> groups) {
        mGroups = groups;
    }

    public Long getHasPrivateChat() {
        return mHasPrivateChat;
    }

    public void setHasPrivateChat(Long hasPrivateChat) {
        mHasPrivateChat = hasPrivateChat;
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

    public List<String> getIps() {
        return mIps;
    }

    public void setIps(List<String> ips) {
        mIps = ips;
    }

    public Boolean getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        mIsAdmin = isAdmin;
    }

    public Boolean getIsAdminOrGlobalModerator() {
        return mIsAdminOrGlobalModerator;
    }

    public void setIsAdminOrGlobalModerator(Boolean isAdminOrGlobalModerator) {
        mIsAdminOrGlobalModerator = isAdminOrGlobalModerator;
    }

    public Boolean getIsAdminOrGlobalModeratorOrModerator() {
        return mIsAdminOrGlobalModeratorOrModerator;
    }

    public void setIsAdminOrGlobalModeratorOrModerator(Boolean isAdminOrGlobalModeratorOrModerator) {
        mIsAdminOrGlobalModeratorOrModerator = isAdminOrGlobalModeratorOrModerator;
    }

    public Boolean getIsFollowing() {
        return mIsFollowing;
    }

    public void setIsFollowing(Boolean isFollowing) {
        mIsFollowing = isFollowing;
    }

    public Boolean getIsGlobalModerator() {
        return mIsGlobalModerator;
    }

    public void setIsGlobalModerator(Boolean isGlobalModerator) {
        mIsGlobalModerator = isGlobalModerator;
    }

    public Boolean getIsModerator() {
        return mIsModerator;
    }

    public void setIsModerator(Boolean isModerator) {
        mIsModerator = isModerator;
    }

    public Boolean getIsSelf() {
        return mIsSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        mIsSelf = isSelf;
    }

    public Boolean getIsSelfOrAdminOrGlobalModerator() {
        return mIsSelfOrAdminOrGlobalModerator;
    }

    public void setIsSelfOrAdminOrGlobalModerator(Boolean isSelfOrAdminOrGlobalModerator) {
        mIsSelfOrAdminOrGlobalModerator = isSelfOrAdminOrGlobalModerator;
    }

    public Boolean getIsTargetAdmin() {
        return mIsTargetAdmin;
    }

    public void setIsTargetAdmin(Boolean isTargetAdmin) {
        mIsTargetAdmin = isTargetAdmin;
    }

    public String getJoindate() {
        return mJoindate;
    }

    public void setJoindate(String joindate) {
        mJoindate = joindate;
    }

    public String getJoindateISO() {
        return mJoindateISO;
    }

    public void setJoindateISO(String joindateISO) {
        mJoindateISO = joindateISO;
    }

    public String getLastonline() {
        return mLastonline;
    }

    public void setLastonline(String lastonline) {
        mLastonline = lastonline;
    }

    public String getLastonlineISO() {
        return mLastonlineISO;
    }

    public void setLastonlineISO(String lastonlineISO) {
        mLastonlineISO = lastonlineISO;
    }

    public String getLastposttime() {
        return mLastposttime;
    }

    public void setLastposttime(String lastposttime) {
        mLastposttime = lastposttime;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public Boolean getLoggedIn() {
        return mLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        mLoggedIn = loggedIn;
    }

    public String getModerationNote() {
        return mModerationNote;
    }

    public void setModerationNote(String moderationNote) {
        mModerationNote = moderationNote;
    }

    public Long getNextStart() {
        return mNextStart;
    }

    public void setNextStart(Long nextStart) {
        mNextStart = nextStart;
    }

    public String getPasswordExpiry() {
        return mPasswordExpiry;
    }

    public void setPasswordExpiry(String passwordExpiry) {
        mPasswordExpiry = passwordExpiry;
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

    public List<Object> getPosts() {
        return mPosts;
    }

    public void setPosts(List<Object> posts) {
        mPosts = posts;
    }

    public List<Object> getProfileLinks() {
        return mProfileLinks;
    }

    public void setProfileLinks(List<Object> profileLinks) {
        mProfileLinks = profileLinks;
    }

    public String getProfileviews() {
        return mProfileviews;
    }

    public void setProfileviews(String profileviews) {
        mProfileviews = profileviews;
    }

    public String getRelativePath() {
        return mRelativePath;
    }

    public void setRelativePath(String relativePath) {
        mRelativePath = relativePath;
    }

    public String getReputation() {
        return mReputation;
    }

    public void setReputation(String reputation) {
        mReputation = reputation;
    }

    public Boolean getReputationDisabled() {
        return mReputationDisabled;
    }

    public void setReputationDisabled(Boolean reputationDisabled) {
        mReputationDisabled = reputationDisabled;
    }

    public Boolean getShowHidden() {
        return mShowHidden;
    }

    public void setShowHidden(Boolean showHidden) {
        mShowHidden = showHidden;
    }

    public String getSignature() {
        return mSignature;
    }

    public void setSignature(String signature) {
        mSignature = signature;
    }

    public List<Object> getSso() {
        return mSso;
    }

    public void setSso(List<Object> sso) {
        mSso = sso;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTheirid() {
        return mTheirid;
    }

    public void setTheirid(String theirid) {
        mTheirid = theirid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTopiccount() {
        return mTopiccount;
    }

    public void setTopiccount(String topiccount) {
        mTopiccount = topiccount;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }

    public String getUploadedpicture() {
        return mUploadedpicture;
    }

    public void setUploadedpicture(String uploadedpicture) {
        mUploadedpicture = uploadedpicture;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public Boolean getUsernameDisableEdit() {
        return mUsernameDisableEdit;
    }

    public void setUsernameDisableEdit(Boolean usernameDisableEdit) {
        mUsernameDisableEdit = usernameDisableEdit;
    }

    public String getUserslug() {
        return mUserslug;
    }

    public void setUserslug(String userslug) {
        mUserslug = userslug;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    public String getWebsiteLink() {
        return mWebsiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        mWebsiteLink = websiteLink;
    }

    public String getWebsiteName() {
        return mWebsiteName;
    }

    public void setWebsiteName(String websiteName) {
        mWebsiteName = websiteName;
    }

    public Long getYourid() {
        return mYourid;
    }

    public void setYourid(Long yourid) {
        mYourid = yourid;
    }

}
