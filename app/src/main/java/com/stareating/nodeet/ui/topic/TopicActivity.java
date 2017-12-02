package com.stareating.nodeet.ui.topic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.TopicApi;
import com.stareating.nodeet.network.entity.Topic;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Stardust on 2017/12/2.
 */

@EActivity(R.layout.activity_topic)
public class TopicActivity extends AppCompatActivity {

    public static final String EXTRA_TID = "tid";
    public static final String EXTRA_TITLE = "title";

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;
    @ViewById(R.id.post_list)
    RecyclerView mPostList;
    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String mTid;
    private String mTitle;
    private PostListAdapter mPostListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        mTid = intent.getStringExtra(EXTRA_TID);
        mTitle = intent.getStringExtra(EXTRA_TITLE);
    }

    @AfterViews
    void setUpViews() {
        setUpToolbar();
        setUpPostList();
        mSwipeRefreshLayout.setOnRefreshListener(this::refresh);
    }

    private void setUpPostList() {
        mPostList.setLayoutManager(new LinearLayoutManager(this));
        mPostList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .colorResId(R.color.color_topic_list_divider)
                .sizeResId(R.dimen.size_topic_list_divider)
                .build());
        mPostListAdapter = new PostListAdapter();
        mPostList.setAdapter(mPostListAdapter);
        refresh();
    }

    private void refresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        NodeBBService.getInstance().getRetrofit()
                .create(TopicApi.class)
                .getTopic(mTid)
                .enqueue(new Callback<Topic>() {
                    @Override
                    public void onResponse(@NonNull Call<Topic> call, @NonNull Response<Topic> response) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Topic topic = response.body();
                        if (topic != null)
                            setTopic(topic);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Topic> call, @NonNull Throwable t) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        t.printStackTrace();
                    }
                });
    }

    private void setTopic(Topic topic) {
        mPostListAdapter.setPosts(topic.getPosts());
        getSupportActionBar().setTitle(topic.getTitle());
    }

    private void setUpToolbar() {
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    // just to avoid warning of null pointer to make Android Studio happy
    @NonNull
    @Override
    public ActionBar getSupportActionBar() {
        ActionBar actionBar = super.getSupportActionBar();
        if (actionBar == null) {
            throw new NullPointerException();
        }
        return actionBar;
    }
}
