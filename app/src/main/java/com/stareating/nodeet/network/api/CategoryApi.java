package com.stareating.nodeet.network.api;

import com.stareating.nodeet.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 婷 on 2017/9/21.
 */

public interface CategoryApi {

    @GET("api/categories")
    Call<Categories> getCategories();
}
