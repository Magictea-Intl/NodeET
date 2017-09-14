package com.stareating.nodeet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by Stardust on 2017/9/14.
 */

public class CategoryListActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CategoryListActivity";

    private CategoryListAdapter mCategoryListAdapter = new CategoryListAdapter();
    private List<Categories.CategoryItem> mCategories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
        fetchCategories();
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
                    .url("http://39.108.231.37/api/categories")
                    .build();
            Response response = mHttpClient.newCall(request).execute();
            Log.d(LOG_TAG, response.toString());
            ResponseBody body = response.body();
            if(body == null)
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
        RecyclerView categoryListView = (RecyclerView) findViewById(R.id.category_list);
        categoryListView.setLayoutManager(new LinearLayoutManager(this));
        categoryListView.setAdapter(mCategoryListAdapter);
    }

    private static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        CategoryViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
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
        }

        @Override
        public int getItemCount() {
            return mCategories == null ? 0 : mCategories.size();
        }
    }

}
