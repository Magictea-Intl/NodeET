package com.stareating.nodeet.ui.topic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stareating.nodeet.R;

/**
 * Created by Stardust on 2017/9/14.
 */

public class TopicListActivity extends AppCompatActivity {

    //如何获取帖子列表：访问 http://39.108.231.37/api/category/cid
    //其中cid是对应板块的id
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";
    private String mCategoryName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int cid = getIntent().getIntExtra(CATEGORY_ID, -1);
        mCategoryName = getIntent().getStringExtra(CATEGORY_NAME);
        setContentView(R.layout.activity_post_list);
        setUpToolbar();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_post_list, TopicListFragment.newInstance(String.valueOf(cid)))
                .commit();
    }


    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mCategoryName);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar != null){
            //make Android Studio happy
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
