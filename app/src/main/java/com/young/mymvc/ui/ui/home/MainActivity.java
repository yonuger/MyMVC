package com.young.mymvc.ui.ui.home;


import android.os.Bundle;

import com.young.mymvc.R;
import com.young.mymvc.ui.BaseActivity;

/**
 * 基本显示activty
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
