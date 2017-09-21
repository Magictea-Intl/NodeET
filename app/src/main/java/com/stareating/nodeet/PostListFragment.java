package com.stareating.nodeet;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.PostApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Stardust on 2017/9/16.
 */

public class PostListFragment extends Fragment {

    private static final String LOG_TAG = "PostListActivity";
    private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateTimeInstance();

    private PostListAdapter mPostListAdapter = new PostListAdapter();

    private List<Posts.PostItem> mPosts = new ArrayList<>();
    private String mCid;

    //注意现在我们的PostListFragment只能用于板块的帖子显示，不能再显示最新和最热了。
    //但是如果最新和最热都要重复写一个完整的这个类出来也不现实。一种方案是继承。
    //这样的话就要在这个类留一些方法给他们重写以便可以调用不同的Api显示帖子列表
    public static PostListFragment newInstance(String cid) {
        PostListFragment fragment = new PostListFragment();
        Bundle arg = new Bundle();
        arg.putString("cid", cid);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取~
        Bundle arg = getArguments();
        if (arg == null)
            return;
        mCid = arg.getString("cid");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
        fetchPosts();
    }

    protected void fetchPosts() {
        NodeBBService.getInstance().getRetrofit()
                .create(PostApi.class)
                .getPostsForCategory(mCid)
                .enqueue(new Callback<Posts>() {
                    @Override
                    public void onResponse(Call<Posts> call, retrofit2.Response<Posts> response) {
                        setPosts(response.body().getPostItems());
                    }

                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getContext(), R.string.fetch_failed, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    protected void setPosts(List<Posts.PostItem> posts) {
        mPosts = posts;
        removeDeletedPosts();
        mPostListAdapter.notifyDataSetChanged();
    }

    private void removeDeletedPosts() {
        Iterator<Posts.PostItem> iterator = mPosts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().deleted) {
                iterator.remove();
            }
        }
    }

    private void setUpViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mPostListAdapter);
    }


    private class PostViewHolder extends RecyclerView.ViewHolder {

        TextView title, view_count, post_count, description, iconText;
        RoundedImageView icon;
        GradientDrawable iconTextBackground;


        public PostViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            view_count = (TextView) itemView.findViewById(R.id.view_count);
            post_count = (TextView) itemView.findViewById(R.id.post_count_item_post);
            description = (TextView) itemView.findViewById(R.id.description_item_post);
            iconText = (TextView) itemView.findViewById(R.id.icon_text);
            iconText.setTypeface(CategoryListFragment.TYPEFACE_ICON);
            iconTextBackground = (GradientDrawable) iconText.getBackground();
            icon = (RoundedImageView) itemView.findViewById(R.id.icon);
        }
    }

    private class PostListAdapter extends RecyclerView.Adapter<PostViewHolder> {

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_post, parent, false));
        }

        @Override
        public void onBindViewHolder(final PostViewHolder holder, int position) {
            final Posts.PostItem postItem = mPosts.get(position);
            holder.title.setText(postItem.title);
            holder.description.setText(DATE_FORMAT.format(new Date(postItem.timestamp)));
            holder.view_count.setText(String.valueOf(postItem.viewcount));
            holder.post_count.setText(String.valueOf(postItem.postcount));
            if (TextUtils.isEmpty(postItem.user.picture)) {
                holder.icon.setVisibility(View.GONE);
                holder.iconText.setVisibility(View.VISIBLE);
                holder.iconTextBackground.setColor(Color.parseColor(postItem.user.iconBgColor));
                holder.iconText.setText(postItem.user.iconText);
            } else {
                holder.icon.setVisibility(View.VISIBLE);
                holder.iconText.setVisibility(View.GONE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Drawable d = loadImageFromWebOperations("http://www.autojs.org" + postItem.user.picture);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.icon.setImageDrawable(d);
                            }
                        });
                    }
                }).start();
            }
        }

        @Override
        public int getItemCount() {
            return mPosts == null ? 0 : mPosts.size();
        }
    }

    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
