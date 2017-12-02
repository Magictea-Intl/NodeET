package com.stareating.nodeet.ui.topic;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.entity.Post;
import com.stareating.nodeet.ui.common.AvatarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stardust on 2017/12/2.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateTimeInstance();

    private List<Post> mPosts = new ArrayList<>();

    public void setPosts(List<Post> posts) {
        mPosts.clear();
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = mPosts.get(position);
        holder.content.setText(Html.fromHtml(post.getContent()));
        holder.time.setText(post.getTimestamp());
        holder.time.setText(DATE_FORMAT.format(new Date(Long.parseLong(post.getTimestamp()))));
        holder.upvote_count.setText(String.valueOf(post.getUpvotes()));
        holder.upvote.setColorFilter(post.getUpvoted() ? 0xfff44336 : 0xffa7a7a7);
        holder.downvote.setColorFilter(post.getDownvoted() ? 0xfff44336 : 0xffa7a7a7);
        holder.downvote_count.setText(String.valueOf(post.getDownvotes()));
        holder.avatarView.setAvatarOfUser(post.getUser());
        holder.userName.setText(post.getUser().getUsername());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content)
        TextView content;

        @BindView(R.id.upvote_count)
        TextView upvote_count;

        @BindView(R.id.upvote)
        ImageView upvote;

        @BindView(R.id.downvote_count)
        TextView downvote_count;

        @BindView(R.id.downvote)
        ImageView downvote;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.username)
        TextView userName;

        @BindView(R.id.avatar)
        AvatarView avatarView;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
