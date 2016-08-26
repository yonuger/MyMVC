package com.young.mymvc.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.young.mymvc.R;


/**
 * @author: young
 * email:1160415122@qq.com
 * date:16/8/26  13:18
 */


public class LoadingDialog extends Dialog{

    private static LoadingDialog mDialog;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static LoadingDialog createProgressDialog(Context context)
    {
        mDialog=new LoadingDialog(context, R.style.loading_dialog_style);
        mDialog.setContentView(R.layout.view_custom_dialog);
        mDialog.getWindow().getAttributes().gravity= Gravity.CENTER;
        return mDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mDialog==null)
            return;
        ImageView imageView= (ImageView) mDialog.findViewById(R.id.iv_progress_dialog);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    public LoadingDialog setMessage(String s)
    {
        TextView textView= (TextView) mDialog.findViewById(R.id.tv_progress_dialog_text);
        if (!TextUtils.isEmpty(s))
        {
            textView.setText(s);
        }else {
            textView.setText("");
        }
        return mDialog;
    }
}
