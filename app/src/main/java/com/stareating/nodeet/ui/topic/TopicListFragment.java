package com.stareating.nodeet.ui.topic;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.stareating.nodeet.ui.common.AvatarView;
import com.stareating.nodeet.R;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.PostApi;
import com.stareating.nodeet.network.entity.Topics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Stardust on 2017/9/16.
 */

public class TopicListFragment extends Fragment {

    private static final String LOG_TAG = "TopicListActivity";
    private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateTimeInstance();

    private PostListAdapter mPostListAdapter = new PostListAdapter();

    private List<Topics.Topic> mPosts = new ArrayList<>();
    private String mCid;

    public static TopicListFragment newInstance(String cid) {
        TopicListFragment fragment = new TopicListFragment();
        Bundle arg = new Bundle();
        arg.putString("cid", cid);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                .enqueue(new Callback<Topics>() {
                    @Override
                    public void onResponse(Call<Topics> call, retrofit2.Response<Topics> response) {
                        setPosts(response.body().getTopics());
                    }

                    @Override
                    public void onFailure(Call<Topics> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getContext(), R.string.fetch_failed, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    protected void setPosts(List<Topics.Topic> posts) {
        mPosts = posts;
        removeDeletedPosts();
        mPostListAdapter.notifyDataSetChanged();
    }

    private void removeDeletedPosts() {
        Iterator<Topics.Topic> iterator = mPosts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isDeleted()) {
                iterator.remove();
            }
        }
    }

    private void setUpViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mPostListAdapter);
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.view_count)
        TextView view_count;

        @BindView(R.id.post_count_item_post)
        TextView post_count;

        @BindView(R.id.description_item_post)
        TextView description;

        @BindView(R.id.avatar)
        AvatarView avatarView;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class PostListAdapter extends RecyclerView.Adapter<TopicViewHolder> {

        @Override
        public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TopicViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_post, parent, false));
        }

        @Override
        public void onBindViewHolder(final TopicViewHolder holder, int position) {
            final Topics.Topic topic = mPosts.get(position);
            holder.title.setText(topic.getTitle());
            holder.description.setText(DATE_FORMAT.format(new Date(topic.getTimestamp())));
            holder.view_count.setText(String.valueOf(topic.getViewcount()));
            holder.post_count.setText(String.valueOf(topic.getPostcount()));
            holder.avatarView.setAvatarOfUser(topic.getUser());
        }

        @Override
        public int getItemCount() {
            return mPosts == null ? 0 : mPosts.size();
        }
    }

}
