package com.stareating.nodeet.ui.topic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.TopicApi;
import com.stareating.nodeet.network.entity.Topic;
import com.stareating.nodeet.network.entity.Topics;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Stardust on 2017/9/16.
 */
@EFragment(R.layout.fragment_topic_list)
public class TopicListFragment extends Fragment {

    public static final int TOPIC_SOURCE_CATEGORY = 0;
    public static final int TOPIC_SOURCE_RECENT = 1;
    public static final int TOPIC_SOURCE_POPULAR = 2;

    public static final String ARGUMENT_CID = "cid";
    public static final String ARGUMENT_TOPIC_SOURCE = "topic_source";

    private static final String LOG_TAG = "TopicListActivity";

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.topic_list)
    RecyclerView mTopicListRecyclerView;

    private TopicListAdapter mTopicListAdapter = new TopicListAdapter();
    private String mCid;
    private int mTopicSource = TOPIC_SOURCE_CATEGORY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        if (arg == null)
            return;
        mTopicSource = arg.getInt(ARGUMENT_TOPIC_SOURCE);
        mCid = arg.getString(ARGUMENT_CID);
    }


    @AfterViews
    void setUpViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this::fetchTopics);
        mTopicListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTopicListRecyclerView.setAdapter(mTopicListAdapter);
        mTopicListRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.color_topic_list_divider)
                .sizeResId(R.dimen.size_topic_list_divider)
                .build());
        fetchTopics();
    }

    protected void fetchTopics() {
        mSwipeRefreshLayout.setRefreshing(true);
        requestTopics().enqueue(new Callback<Topics>() {
            @Override
            public void onResponse(@NonNull Call<Topics> call, @NonNull retrofit2.Response<Topics> response) {
                setPosts(response.body().getTopics());
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(@NonNull Call<Topics> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), R.string.fetch_failed, Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private Call<Topics> requestTopics() {
        TopicApi topicApi = NodeBBService.getInstance().getRetrofit()
                .create(TopicApi.class);
        if (mTopicSource == TOPIC_SOURCE_CATEGORY) {
            return topicApi.getTopicsForCategory(mCid);
        } else if (mTopicSource == TOPIC_SOURCE_POPULAR) {
            return topicApi.getPopularTopics();
        } else {
            return topicApi.getRecentTopics();
        }
    }

    protected void setPosts(List<Topic> posts) {
        removeDeletedPosts(posts);
        mTopicListAdapter.setTopics(posts);
        mTopicListAdapter.notifyDataSetChanged();
    }

    private void removeDeletedPosts(List<Topic> posts) {
        Iterator<Topic> iterator = posts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isDeleted()) {
                iterator.remove();
            }
        }
    }


}
