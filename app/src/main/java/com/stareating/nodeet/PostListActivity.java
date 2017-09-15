package com.stareating.nodeet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Stardust on 2017/9/14.
 */

public class PostListActivity extends AppCompatActivity {

    //如何获取帖子列表：访问 http://39.108.231.37/api/category/cid/posts
    //其中cid是对应板块的id

    private static final String LOG_TAG = "PostListActivity";
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";
    private PostListAdapter mPostListAdapter = new PostListAdapter();
    private List<Posts.PostItem> mPosts = new ArrayList<>();
    private int cid;
    private String categoryName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
        fetchPosts();
    }

    private void fetchPosts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                doFetchPosts();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPostListAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void doFetchPosts(){
        cid = getIntent().getIntExtra(CATEGORY_ID, -1);
        categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        Log.d(LOG_TAG, "cid = " + cid + ", category = " + categoryName);
        try {
            OkHttpClient mHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://39.108.231.37/api/category/" + String.valueOf(cid) + "/posts")
                    .build();
            Response response = mHttpClient.newCall(request).execute();
            Log.d(LOG_TAG, response.toString());
            ResponseBody body = response.body();
            if (body == null)
                return;
            String json = body.string();
            Log.d(LOG_TAG, "body = " + json);
            Posts posts = new Gson().fromJson(json, new TypeToken<Posts>(){}.getType());
            mPosts = posts.getPostItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        setContentView(R.layout.activity_post_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mPostListAdapter);
        setUpToolbar();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    private class PostViewHolder extends RecyclerView.ViewHolder{

        TextView title, view_count, post_count, description;

        public PostViewHolder(View itemView) {
            super(itemView);
            // =.= 这里调用了Activity的findViewById....
            title = (TextView) itemView.findViewById(R.id.title);
            view_count = (TextView) itemView.findViewById(R.id.view_count);
            post_count = (TextView) itemView.findViewById(R.id.post_count_item_post);
            description = (TextView) itemView.findViewById(R.id.description_item_post);
        }
    }

    private class PostListAdapter extends RecyclerView.Adapter<PostViewHolder>{

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_post, parent, false));
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            Posts.PostItem postItem = mPosts.get(position);
            if(postItem.title.equals("[[topic:topic_is_deleted]]")){
                holder.title.setText("此主题已被删除！");
                holder.title.setTextColor(Color.GRAY);
                holder.view_count.setTextColor(Color.GRAY);
                holder.post_count.setTextColor(Color.GRAY);
            }
            else holder.title.setText(postItem.title);
            Log.d(LOG_TAG, "title = " + postItem.title);
            holder.description.setText(categoryName + "·" + postItem.time_stamp_ISO);
            holder.view_count.setText(String.valueOf(postItem.viewcount));
            holder.post_count.setText(String.valueOf(postItem.postcount));
        }

        @Override
        public int getItemCount() {
            return mPosts == null ? 0 : mPosts.size();
        }
    }
}
