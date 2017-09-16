package com.stareating.nodeet;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.stareating.nodeet.PostListActivity.CATEGORY_ID;
import static com.stareating.nodeet.PostListActivity.CATEGORY_NAME;

/**
 * Created by Stardust on 2017/9/14.
 */

public class CategoryListActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CategoryListActivity";

    static Typeface TYPEFACE_ICON;

    private CategoryListAdapter mCategoryListAdapter = new CategoryListAdapter();
    private List<Categories.CategoryItem> mCategories = new ArrayList<>();
    private TabLayout mTabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIconTypeFaceIfNeeded();
        setUpViews();
        fetchCategories();

    }

    private void initIconTypeFaceIfNeeded() {
        if (TYPEFACE_ICON != null)
            return;
        TYPEFACE_ICON = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
    }

    private void fetchCategories() {
        //网络访问等耗时操作不能在主线程(UI线程)中执行，否则会造成界面卡顿
        new Thread(new Runnable() {
            @Override
            public void run() {
                doFetchingCategories();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCategoryListAdapter.notifyDataSetChanged();
                    }
                });
            }

        }).start();
    }

    private void doFetchingCategories() {
        try {
            OkHttpClient mHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://www.autojs.org/api/categories")
                    .build();
            Response response = mHttpClient.newCall(request).execute();
            Log.d(LOG_TAG, response.toString());
            ResponseBody body = response.body();
            if (body == null)
                return;
            String json = body.string();
            Log.d(LOG_TAG, "body = " + json);
            Categories categories = new Gson().fromJson(json, new TypeToken<Categories>() {
            }.getType());
            mCategories = categories.getCategoryItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        setContentView(R.layout.activity_category_list);
        setUpToolbarWithDrawerLayout();
        setUpTabLayout();
        setUpCategoryList();
    }

    private void setUpCategoryList() {
        RecyclerView categoryListView = (RecyclerView) findViewById(R.id.category_list);
        categoryListView.setLayoutManager(new LinearLayoutManager(this));
        categoryListView.setAdapter(mCategoryListAdapter);
    }

    private void setUpTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("版块"));
        mTabLayout.addTab(mTabLayout.newTab().setText("最新"));
        mTabLayout.addTab(mTabLayout.newTab().setText("热门"));
    }

    private void setUpToolbarWithDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }


    private class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView name, description, topic_count, post_count;
        TextView icon;
        GradientDrawable iconBackground;

        CategoryViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            topic_count = (TextView) itemView.findViewById(R.id.topic_count);
            post_count = (TextView) itemView.findViewById(R.id.post_count);
            icon = (TextView) itemView.findViewById(R.id.icon);
            icon.setTypeface(TYPEFACE_ICON);
            iconBackground = (GradientDrawable) icon.getBackground();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // FIXME: 2017/9/16 cid可不一定等于pos + 1哦
                    int pos = getAdapterPosition();
                    Categories.CategoryItem item = mCategories.get(pos);
                    startActivity(new Intent(CategoryListActivity.this, PostListActivity.class)
                            .putExtra(CATEGORY_ID, pos + 1)
                            .putExtra(CATEGORY_NAME, item.name));
                }
            });
        }
    }

    private class CategoryListAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_category, parent, false));
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder holder, int position) {
            Categories.CategoryItem category = mCategories.get(position);
            holder.name.setText(category.name);
            holder.description.setText(category.description);
            holder.topic_count.setText(String.valueOf(category.topic_count));
            holder.post_count.setText(String.valueOf(category.post_count));
            holder.iconBackground.setColor(Color.parseColor(category.bgColor));
            holder.icon.setText(getCharForFontAwesome(category.icon));
        }

        @Override
        public int getItemCount() {
            return mCategories == null ? 0 : mCategories.size();
        }
    }

    private String getCharForFontAwesome(String icon) {
        int resId = getResources().getIdentifier(icon.replace('-', '_'), "string", getPackageName());
        if (resId == 0)
            return "";
        return getString(resId);
    }

}
