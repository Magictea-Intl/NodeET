package com.stareating.nodeet.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.stareating.nodeet.network.api.UserApi;
import com.stareating.nodeet.network.common.CommonResponse;
import com.stareating.nodeet.network.entity.Token;
import com.stareating.nodeet.network.entity.User;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Stardust on 2017/11/8.
 */

public class UserService {

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
    public Observable<CommonResponse<Token>> login(String userName, final String password) {
        final UserApi userApi = NodeBBService.getInstance().getRetrofit().create(UserApi.class);
        Observable<CommonResponse<Token>> tokenObserale = userApi.verify(userName, password)
                .subscribeOn(Schedulers.io())
                .map(result -> result.getUid())
                .flatMap(uid -> userApi.generateToken(uid, password))
                .observeOn(AndroidSchedulers.mainThread());
        tokenObserale.subscribe(token -> onLoginSuccess(token.getPayload()));
        return tokenObserale;
    }

    public Observable<User> me(){
       return NodeBBService.getInstance().getRetrofit().create(UserApi.class)
                .getUser(mUid);
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
