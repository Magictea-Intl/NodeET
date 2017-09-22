package com.stareating.nodeet.network.api;

import com.stareating.nodeet.Posts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 婷 on 2017/9/21.
 */

public interface PostApi {

    @GET("api/recent")
    Call<Posts> getRecents();

    @GET("api/popular")
    Call<Posts> getPopular();

    //获取到某个目录下的帖子列表时，路径的最后是一个cid，用{}表示，并在函数参数中用注解
    //@Path("cid")表示这个参数,会填充到路径{cid}的位置
    @GET("api/category/{cid}")
    Call<Posts> getPostsForCategory(@Path("cid") String cid);

}
