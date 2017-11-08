package com.stareating.nodeet.network;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.stareating.nodeet.network.api.UserApi;
import com.stareating.nodeet.network.common.CommonResponse;
import com.stareating.nodeet.network.entity.Token;
import com.stareating.nodeet.network.entity.UserVerifyResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Stardust on 2017/11/8.
 */

public class UserService {

    public interface LoginCallback {
        void onSuccess();

        void onError(String message);
    }

    private static final String KEY_USER_TOKEN = UserService.class.getName() + ".user_token";

    private static UserService sInstance;
    private final Retrofit mRetrofit;
    private String mToken;
    private SharedPreferences mSharedPreferences;

    UserService(Application application) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        mRetrofit = NodeBBService.getInstance().getRetrofit();
        mToken = mSharedPreferences.getString(KEY_USER_TOKEN, null);
    }

    public static UserService getInstance(Application application) {
        if (sInstance == null) {
            sInstance = new UserService(application);
        }
        return sInstance;
    }

    public void login(String userName, final String password, LoginCallback callback) {
        final UserApi userApi = mRetrofit.create(UserApi.class);
        userApi.verify(userName, password)
                .enqueue(new Callback<UserVerifyResult>() {
                    @Override
                    public void onResponse(@NonNull Call<UserVerifyResult> call, @NonNull Response<UserVerifyResult> response) {
                        UserVerifyResult verifyResult = response.body();
                        System.out.println(verifyResult);
                        if (verifyResult == null) {
                            callback.onError(null);
                            return;
                        }
                        userApi.generateToken(verifyResult.getUid(), password)
                                .enqueue(new Callback<CommonResponse<Token>>() {
                                    @Override
                                    public void onResponse(@NonNull Call<CommonResponse<Token>> call, @NonNull Response<CommonResponse<Token>> response) {
                                        CommonResponse<Token> tokenResponse = response.body();
                                        if (tokenResponse == null) {
                                            callback.onError(null);
                                            return;
                                        }
                                        if (!tokenResponse.getCode().equals("ok")) {
                                            callback.onError(tokenResponse.getCode());
                                            return;
                                        }
                                        callback.onSuccess();
                                        onLoginSuccess(tokenResponse.getPayload());
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<CommonResponse<Token>> call, @NonNull Throwable t) {
                                        t.printStackTrace();
                                        callback.onError(t.getMessage());
                                    }
                                });

                    }

                    @Override
                    public void onFailure(@NonNull Call<UserVerifyResult> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        callback.onError(t.getMessage());
                    }
                });

    }

    private void onLoginSuccess(Token token) {
        mToken = token.getToken();
        mSharedPreferences.edit()
                .putString(KEY_USER_TOKEN, mToken)
                .apply();
    }

    public String getToken() {
        return mToken;
    }

    public boolean isLoggedIn() {
        return mToken != null;
    }


    public void logout() {
        mToken = null;
        mSharedPreferences.edit().remove(KEY_USER_TOKEN).apply();
    }

}
