package com.stareating.nodeet.ui.topic;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.entity.Teaser;
import com.stareating.nodeet.network.entity.Topic;
import com.stareating.nodeet.network.entity.Topics;
import com.stareating.nodeet.ui.common.AvatarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Stardust on 2017/11/7.
 */

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.TopicViewHolder> {

    class TopicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.content)
        TextView content;

        @BindView(R.id.view_count)
        TextView view_count;

        @BindView(R.id.post_count)
        TextView post_count;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.username)
        TextView userName;

        @BindView(R.id.avatar)
        AvatarView avatarView;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateTimeInstance();

    private List<Topic> mTopics = new ArrayList<>();


    public void setTopics(List<Topic> topics) {
        mTopics = topics;
        notifyDataSetChanged();
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, int position) {
        final Topic topic = mTopics.get(position);
        holder.title.setText(topic.getTitle());
        holder.time.setText(DATE_FORMAT.format(new Date(Long.parseLong(topic.getTimestamp()))));
        holder.view_count.setText(String.valueOf(topic.getViewcount()));
        holder.post_count.setText(String.valueOf(topic.getPostcount()));
        holder.avatarView.setAvatarOfUser(topic.getUser());
        holder.userName.setText(topic.getUser().getUsername());
        Teaser teaser = topic.getTeaser();
        if (teaser != null && teaser.getContent() != null)
            holder.content.setText(Html.fromHtml(teaser.getContent()));
    }

    @Override
    public int getItemCount() {
        return mTopics == null ? 0 : mTopics.size();
    }
}
