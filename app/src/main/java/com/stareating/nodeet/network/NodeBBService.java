package com.stareating.nodeet.network;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 婷 on 2017/9/21.
 */


//NodeBBService 网络层的对其他层的暴露的入口。主要是管理各种网络层的调用。
public class NodeBBService {


    private static  NodeBBService sInstance;
    private Retrofit mRetrofit;
    private UserService mUserService;

    private NodeBBService(Context context){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.autojs.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String token = mUserService.getToken();
                    if(token == null){
                        return chain.proceed(chain.request());
                    }
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + token);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build())
                .build();
        UserService.init(context);
        mUserService = UserService.getInstance();
    }

    public static NodeBBService getInstance() {
        return sInstance;
    }

    public static void init(Context context){
        if(sInstance != null)
            throw new IllegalStateException("has been initialized");
        sInstance = new NodeBBService(context);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public UserService getUserService() {
        return mUserService;
    }
}
