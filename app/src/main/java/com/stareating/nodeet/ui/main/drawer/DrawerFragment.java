package com.stareating.nodeet.ui.main.drawer;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stareating.nodeet.R;
import com.stareating.nodeet.imageloading.GlideApp;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.UserService;
import com.stareating.nodeet.network.api.UserApi;
import com.stareating.nodeet.network.entity.User;
import com.stareating.nodeet.ui.common.AvatarView;
import com.stareating.nodeet.ui.login.LoginActivity_;
import com.stareating.nodeet.ui.user.UserProfileActivity;
import com.stareating.nodeet.ui.user.UserProfileActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Stardust on 2017/9/16.
 */
//使用AA框架的Fragment或者Activity都需要用EFragment或EActivity指定布局。
//在使用有AA的Fragment都要加下划线
@EFragment(R.layout.fragment_drawer)
public class DrawerFragment extends Fragment {

    //因为这个Fragment比较简单，只用到Click的注解。这样子看起来其实代码行数差不多但是语义更清晰也看起来更简洁

    private UserService mUserService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserService = UserService.getInstance();
    }

    @ViewById(R.id.username)
    TextView userName;
    @ViewById(R.id.avatar)
    AvatarView mAvatarView;
    @ViewById(R.id.header)
    View mHeaderView;

    @Override
    public void onResume(){
        super.onResume();
        if(mUserService.isLoggedIn()){
            mUserService.me()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(u -> {
                        userName.setText(u.getUsername());
                        mAvatarView.setAvatarOfUser(u);
                        GlideApp.with(this)
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

    @Click(R.id.exit)
    void exit() {
        getActivity().finish();
    }

    @Click(R.id.settings)
    void goToSettings() {
        // TODO: 2017/9/16 跳转到设置
    }

    @Click(R.id.avatar)
    void showUserDetail() {
        if (mUserService.isLoggedIn()) {
            //mUserService.logout();
            //Toast.makeText(getActivity(), R.string.logout_success, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), UserProfileActivity_.class));
        } else {
            LoginActivity_.intent(this).start();
        }
    }

    @Click(R.id.header)
    void setUpHeaderImage() {
        // TODO: 2017/9/16 让用户设置背景图片
    }
}
