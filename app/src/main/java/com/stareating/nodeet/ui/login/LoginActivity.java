package com.stareating.nodeet.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.stareating.nodeet.R;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Click(R.id.login_btn)
    void Login(){
        if(checkIsOK()){
            // TODO: 2017/11/8 登陆成功
            Toast.makeText(LoginActivity.this, "LOGIN SUCCEED", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkIsOK() {

        String ac = accountTextInputLayout.getEditText().getText().toString();
        String pw = passwordTextInputLayout.getEditText().getText().toString();

        if(ac.length() == 0){
            accountTextInputLayout.setError("账号不能为空");
            return false;
        }
        else if(pw.length() == 0){
            passwordTextInputLayout.setError("密码不能为空");
            return false;
        }
        else{
            accountTextInputLayout.setErrorEnabled(false);
            passwordTextInputLayout.setErrorEnabled(false);
            return true;
        }
    }


}
