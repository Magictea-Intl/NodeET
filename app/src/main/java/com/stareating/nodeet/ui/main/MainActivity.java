package com.stareating.nodeet.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.stareating.nodeet.R;
import com.stareating.nodeet.ui.main.page.CategoryListFragment_;
import com.stareating.nodeet.ui.topic.TopicListFragment;
import com.stareating.nodeet.ui.topic.TopicListFragment_;
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
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        //用Builder模式來重構代碼。
        viewPager.setAdapter(new FragmentPagerAdapterBuilder(getSupportFragmentManager())
                .add(getString(R.string.category), new CategoryListFragment_())
                .add(getString(R.string.recent), TopicListFragment_.builder()
                        .arg(TopicListFragment.ARGUMENT_TOPIC_SOURCE, TopicListFragment.TOPIC_SOURCE_RECENT)
                        .build())
                .add(getString(R.string.popular), TopicListFragment_.builder()
                        .arg(TopicListFragment.ARGUMENT_TOPIC_SOURCE, TopicListFragment.TOPIC_SOURCE_POPULAR)
                        .build())
                .build());
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpToolbarWithDrawerLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }


}
