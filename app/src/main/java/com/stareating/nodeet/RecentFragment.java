package com.stareating.nodeet;

import android.widget.Toast;

import com.stareating.nodeet.network.NodeBBService;
import com.stareating.nodeet.network.api.PostApi;

import retrofit2.Call;
import retrofit2.Callback;

import static java.security.AccessController.getContext;

/**
 * Created by 婷 on 2017/9/21.
 */

public class RecentFragment extends PostListFragment {

    protected void fetchPosts() {
        NodeBBService.getInstance().getRetrofit()
                .create(PostApi.class)
                .getRecents()
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

}
