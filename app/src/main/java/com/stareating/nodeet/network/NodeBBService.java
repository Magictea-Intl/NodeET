package com.stareating.nodeet.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 婷 on 2017/9/21.
 */


//NodeBBService 网络层的对其他层的暴露的入口。主要是管理各种网络层的调用。
public class NodeBBService {


    private static final NodeBBService sInstance = new NodeBBService();
    private Retrofit mRetrofit;

    private NodeBBService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.autojs.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NodeBBService getInstance() {
        return sInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
