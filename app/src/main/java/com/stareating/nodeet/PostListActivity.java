package com.stareating.nodeet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Stardust on 2017/9/14.
 */

public class PostListActivity extends AppCompatActivity {

    //如何获取帖子列表：访问 http://39.108.231.37/api/category/cid
    //其中cid是对应板块的id
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";
    private int cid;
    private String categoryName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cid = getIntent().getIntExtra(CATEGORY_ID, -1);
        categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        setContentView(R.layout.activity_post_list);
        setUpToolbar();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_post_list, PostListFragment.newInstance("http://www.autojs.org/api/category/" + cid))
                .commit();
    }


    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(categoryName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
