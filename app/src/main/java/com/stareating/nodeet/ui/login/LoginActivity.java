package com.stareating.nodeet.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.stareating.nodeet.R;
import com.stareating.nodeet.network.UserService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

/**
 * Created by 婷 on 2017/11/8.
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {


    @ViewById(R.id.account_inputlayout)
    TextInputLayout accountTextInputLayout;

    @ViewById(R.id.password_inputlayout)
    TextInputLayout passwordTextInputLayout;


    @Click(R.id.login_btn)
    void login() {
        if (!checkIsOK()) {
            return;
        }
        UserService.getInstance(getApplication())
                .login(accountTextInputLayout.getEditText().getText().toString(),
                        passwordTextInputLayout.getEditText().getText().toString(),
                        new UserService.LoginCallback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(getApplicationContext(), R.string.login_success, Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onError(String message) {
                                if (message == null)
                                    message = getString(R.string.login_fail);
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                            }
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
