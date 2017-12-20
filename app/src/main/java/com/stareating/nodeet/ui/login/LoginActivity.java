package com.stareating.nodeet.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.widget.EditText;
import android.widget.Toast;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.UserService;
import com.stareating.nodeet.network.common.CommonResponse;
import com.stareating.nodeet.network.entity.Token;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import io.reactivex.functions.Consumer;

/**
 * Created by 婷 on 2017/11/8.
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {


    @ViewById(R.id.account_inputlayout)
    TextInputLayout accountTextInputLayout;

    @ViewById(R.id.password_inputlayout)
    TextInputLayout passwordTextInputLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Click(R.id.login_btn)
    void login() {
        if (!checkIsOK()) {
            return;
        }
        UserService.getInstance()
                .login(accountTextInputLayout.getEditText().getText().toString(),
                        passwordTextInputLayout.getEditText().getText().toString())
                    .doOnError(error -> {
                        String message = error.getMessage();
                        if (message == null)
                            message = getString(R.string.login_fail);
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    })
                    .subscribe(tokenCommonResponse -> {
                        Toast.makeText(getApplicationContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
                        finish();
                    });

    }

    private boolean checkIsOK() {

        String ac = accountTextInputLayout.getEditText().getText().toString();
        String pw = passwordTextInputLayout.getEditText().getText().toString();

        if (ac.length() == 0) {
            accountTextInputLayout.setError("账号不能为空");
            return false;
        } else if (pw.length() == 0) {
            passwordTextInputLayout.setError("密码不能为空");
            return false;
        } else {
            accountTextInputLayout.setErrorEnabled(false);
            passwordTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }


}
