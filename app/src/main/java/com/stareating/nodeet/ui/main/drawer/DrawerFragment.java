package com.stareating.nodeet.ui.main.drawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stareating.nodeet.R;
import com.stareating.nodeet.ui.login.LoginActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Stardust on 2017/9/16.
 */
//使用AA框架的Fragment或者Activity都需要用EFragment或EActivity指定布局。
//在使用有AA的Fragment都要加下划线
@EFragment(R.layout.fragment_drawer)
public class DrawerFragment extends Fragment {

    //因为这个Fragment比较简单，只用到Click的注解。这样子看起来其实代码行数差不多但是语义更清晰也看起来更简洁

    @Click(R.id.exit)
    void exit(){
        getActivity().finish();
    }

    @Click(R.id.settings)
    void goToSettings(){
        // TODO: 2017/9/16 跳转到设置
    }

    @Click(R.id.avatar)
    void showUserDetail(){
        LoginActivity_.intent(this).start();
    }

    @Click(R.id.header)
    void setUpHeaderImage(){
        // TODO: 2017/9/16 让用户设置背景图片
    }
}
