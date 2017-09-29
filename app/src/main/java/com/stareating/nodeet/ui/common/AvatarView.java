package com.stareating.nodeet.ui.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.entity.User;
import com.stareating.nodeet.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 婷 on 2017/9/29.
 */

public class AvatarView extends FrameLayout {
    
    //有的变量没有加任何修饰。是由于aa和butterknife的实现机制问题，
    //使用这些注解不能用private。
    @BindView(R.id.icon_text)
    TextView mIconText;
    
    @BindView(R.id.icon)
    ImageView mIcon;

    private GradientDrawable mIconTextBackground;
    
    
    public AvatarView(@NonNull Context context) {
        super(context);
        init();
    }

    public AvatarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvatarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init(){
        inflate(getContext(), R.layout.avatar_view, this);
        ButterKnife.bind(this);
        mIconTextBackground = (GradientDrawable) mIconText.getBackground();
    }
    
    public void setAvatarOfUser(final User user){
        if (TextUtils.isEmpty(user.getPicture())) {
            mIcon.setVisibility(View.GONE);
            mIconText.setVisibility(View.VISIBLE);
            mIconTextBackground.setColor(Color.parseColor(user.getIconBgColor()));
            mIconText.setText(user.getIconText());
        }
        else {
            mIcon.setVisibility(View.VISIBLE);
            mIconText.setVisibility(View.GONE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Drawable d = ImageLoader.loadUrl("http://www.autojs.org" + user.getPicture());
                    //View.post和Activity.runOnUiThread一样
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mIcon.setImageDrawable(d);
                        }
                    });
                }
            }).start();
        }
    }
}

