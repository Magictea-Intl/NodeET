package com.stareating.nodeet.ui.main;

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

import com.stareating.nodeet.R;
import com.stareating.nodeet.ui.main.page.CategoryListFragment;
import com.stareating.nodeet.ui.main.page.PopularFragment;
import com.stareating.nodeet.ui.main.page.RecentFragment;
import com.stareating.nodeet.widget.FragmentPagerAdapterBuilder;

/**
 * Created by Stardust on 2017/9/14.
 */

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "MainActivity";

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
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        //這裡就可以重構了。儘管這樣寫還不算很複雜，但是太難看了
        //我們用Builder模式來重構這段代碼。我們希望到達的效果是：
        //嗯，看起来确实好看很多了。
        viewPager.setAdapter(new FragmentPagerAdapterBuilder(getSupportFragmentManager())
            .add(getString(R.string.category), new CategoryListFragment())
            .add(getString(R.string.recent), new RecentFragment())
            .add(getString(R.string.popular), new PopularFragment())
            .build());
        tabLayout.setupWithViewPager(viewPager);
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
