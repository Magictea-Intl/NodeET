package com.stareating.nodeet.network;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.stareating.nodeet.network.api.UserApi;
import com.stareating.nodeet.network.common.CommonResponse;
import com.stareating.nodeet.network.entity.Token;
import com.stareating.nodeet.network.entity.User;
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

    private static final String KEY_USER_ID = UserService.class.getName() + ".user_id";
    private static final String KEY_USER_TOKEN = UserService.class.getName() + ".user_token";

    private static UserService sInstance;
    private String mToken;
    private String mUid;
    private SharedPreferences mSharedPreferences;

    UserService(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mToken = mSharedPreferences.getString(KEY_USER_TOKEN, null);
        mUid = mSharedPreferences.getString(KEY_USER_ID, null);
    }

    public static UserService getInstance() {
        return sInstance;
    }

    public static void init(Context context){
        if(sInstance != null)
            throw new IllegalStateException("has been initialized");
        sInstance = new UserService(context);
    }
    public void login(String userName, final String password, LoginCallback callback) {
        final UserApi userApi = NodeBBService.getInstance().getRetrofit().create(UserApi.class);
        userApi.verify(userName, password)
                .enqueue(new Callback<UserVerifyResult>() {
                    @Override
                    public void onResponse(@NonNull Call<UserVerifyResult> call, @NonNull Response<UserVerifyResult> response) {
                        UserVerifyResult verifyResult = response.body();
                        if (verifyResult == null) {
                            callback.onError(null);
                            return;
                        }
                        mUid = verifyResult.getUid();
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

    public void me(Callback<User> callback){
        NodeBBService.getInstance().getRetrofit().create(UserApi.class)
                .getUser(mUid)
                .enqueue(callback);
    }

    private void onLoginSuccess(Token token) {
        mToken = token.getToken();
        mSharedPreferences.edit()
                .putString(KEY_USER_TOKEN, mToken)
                .putString(KEY_USER_ID, mUid)
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
