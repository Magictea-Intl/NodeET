package com.stareating.nodeet.network.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 婷 on 2017/9/21.
 */

public interface CategoryApi {

    @GET("api/categories")
    Observable<Categories> getCategories();
}
