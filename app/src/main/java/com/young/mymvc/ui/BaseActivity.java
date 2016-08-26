package com.young.mymvc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.young.mymvc.ui.widget.LoadingDialog;

/**
 * @author: young
 * date:16/8/26  13:15
 */


public abstract class BaseActivity extends AppCompatActivity {

    protected LoadingDialog loadingDialog;

    public void showProgress()
    {
        showProgress(null);
    }

    public void showProgress(String text)
    {
        if (loadingDialog==null)
        {
            loadingDialog = LoadingDialog.createProgressDialog(this);
        }
        if (loadingDialog!=null)
        {
            loadingDialog.setMessage(text);
            loadingDialog.show();
            loadingDialog.setCancelable(false);
        }

    }

    public void hideProgress()
    {
        if (loadingDialog != null && loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
            loadingDialog=null;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    public abstract int getLayoutId();
}
