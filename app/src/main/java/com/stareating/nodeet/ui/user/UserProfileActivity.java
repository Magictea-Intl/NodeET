package com.stareating.nodeet.ui.user;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.stareating.nodeet.R;
import com.stareating.nodeet.imageloading.GlideApp;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.UserService;
import com.stareating.nodeet.network.entity.User;
import com.stareating.nodeet.ui.common.AvatarView;
import com.stareating.nodeet.util.DateTimeFormatter;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 婷 on 2017/12/3.
 */

@EActivity(R.layout.activity_userprofile)
public class UserProfileActivity extends AppCompatActivity {


    @ViewById(R.id.username)
    TextView mUserName;
    @ViewById(R.id.avatar)
    AvatarView mAvatarView;
    @ViewById(R.id.reputation)
    TextView mReputation;
    @ViewById(R.id.post)
    TextView mPost;
    @ViewById(R.id.dataBrowsing)
    TextView mProfileViews;
    @ViewById(R.id.fans)
    TextView mFollower;
    @ViewById(R.id.following)
    TextView mFollowing;
    @ViewById(R.id.email)
    TextView mEmail;
    @ViewById(R.id.login_time)
    TextView mLoginTime;
    @ViewById(R.id.register_time)
    TextView mRegTime;
    @ViewById(R.id.header)
    View mHeaderView;

    private static final String LOG_TAG = "UserProfileActivity";
    private UserService mUserService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserService = UserService.getInstance();

        getUserProfile();
    }

    private void getUserProfile() {
        mUserService.me()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u -> {
                            mUserName.setText(u.getUsername());
                            mAvatarView.setAvatarOfUser(u);
                            mReputation.setText(u.getReputation());
                            mPost.setText(u.getPostcount());
                            mFollowing.setText(String.valueOf(u.getFollowingCount()));
                            mFollower.setText(String.valueOf(u.getFollowerCount()));
                            mLoginTime.setText(DateTimeFormatter.format(Long.parseLong(u.getLastonline())));
                            mRegTime.setText(DateTimeFormatter.format(Long.parseLong(u.getJoindate())));
                            mProfileViews.setText(u.getProfileviews());
                            mEmail.setText(u.getEmail());
                            GlideApp.with(UserProfileActivity.this)
                                    .load(NodeBBService.url(u.getCoverUrl()))
                                    .into(new SimpleTarget<Drawable>() {
                                        @Override
                                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                            mHeaderView.setBackground(resource);
                                        }
                                    });
                        });
    }

}
