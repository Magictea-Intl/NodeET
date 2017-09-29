package com.stareating.nodeet.ui.main.page;

import android.widget.Toast;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.PostApi;
import com.stareating.nodeet.network.entity.Topics;
import com.stareating.nodeet.ui.topic.TopicListFragment;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by 婷 on 2017/9/21.
 */

public class PopularFragment extends TopicListFragment {

    protected void fetchPosts() {
        NodeBBService.getInstance().getRetrofit()
                .create(PostApi.class)
                .getPopular()
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
}
