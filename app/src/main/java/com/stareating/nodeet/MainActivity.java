package com.stareating.nodeet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Stardust on 2017/9/14.
 */

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private TabLayout mTabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
    }


    private void setUpViews() {
        setContentView(R.layout.activity_main);
        setUpToolbarWithDrawerLayout();
        setUpTabLayout();
    }

    private void setUpTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //我们是用ViewPager和TabLayout一起滑动，來实现翻动Tab時會显示不同的画面。
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final String[] titles = {"版块", "最新", "热门"};
        //这里设置了一个Adapter，用于指定对应位置显示的Fragment。看一下函数名应该就都知道是什么意思了
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public int getCount() {
                return 3;
            }

            //这里由一个问题是。我们的最新和最热的页面本质上都是一个帖子列表，只是获取帖子的地址不同而已。
            //这样的話我们可以用同一个的Fragment但传进去不同的参数（地址）来实现。
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new CategoryListFragment();
                    case 1:
                        return PostListFragment.newInstance("http://www.autojs.org/api/recent");
                    case 2:
                        return PostListFragment.newInstance("http://www.autojs.org/api/popular");
                    default:
                        throw new IllegalArgumentException();
                }
            }
        });
        //然后调用setupWithViewPager就ok
        mTabLayout.setupWithViewPager(viewPager);
    }

    private void setUpToolbarWithDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }


}
