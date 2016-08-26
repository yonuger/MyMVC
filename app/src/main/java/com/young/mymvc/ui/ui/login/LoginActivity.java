package com.young.mymvc.ui.ui.login;

import android.os.Bundle;

import com.young.mymvc.R;
import com.young.mymvc.ui.BaseActivity;
import com.young.mymvc.ui.logic.account.AccountManager;
import com.young.mymvc.ui.logic.base.RequestDataCallback;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 点击登录，涉及到了网络
     */
    public void login(){
        //先调用加载框
        showProgress();
        AccountManager.getInstance().login("account", "password", new RequestDataCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
