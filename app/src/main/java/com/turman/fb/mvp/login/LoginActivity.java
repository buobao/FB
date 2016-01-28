package com.turman.fb.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.turman.fb.R;
import com.turman.fb.activity.ViewShowActivity;

/**
 * Created by dqf on 2016/1/6.
 *
 * activity实现view接口实现视图操作和activity分离，调用presenter实现业务处理
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        findViewById(R.id.login_loginBtn).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError("username can not be empty!");
    }

    @Override
    public void setPasswordError() {
        password.setError("password can not be empty!");
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, ViewShowActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
