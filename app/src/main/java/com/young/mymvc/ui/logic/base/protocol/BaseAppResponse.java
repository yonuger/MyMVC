package com.young.mymvc.ui.logic.base.protocol;

import android.util.Log;

import com.young.mymvc.ui.util.DesNewUtil;
import com.young.mymvc.ui.util.JsonUtil;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * @author: young
 * date:16/8/26  16:23
 */


public abstract class BaseAppResponse extends BaseResponse{

    private static final String TAG = "BaseAppResponse";
    protected JSONObject mResponseJson;
    protected JSONObject mJsonData;

    @Override
    public void parse()
    {
        JSONObject jsonObject = JsonUtil.getJsonObjectFromString(mResultString);

        if (jsonObject == null)
        {
            Log.e(TAG, "result string is not json object.");
            return;
        }

        mResponseJson = jsonObject;

        mMsgCode = JsonUtil.getInt(jsonObject, "msgCode", 0);
        mMsg = JsonUtil.getString(jsonObject, "msg");
        mTranCode = JsonUtil.getInt(jsonObject, "tranCode");
        isEncryption=JsonUtil.getInt(jsonObject, "isEncryption");
        if (isEncryption==1)
        {
            String temp=JsonUtil.getString(jsonObject,"bizContent");
            try {
                mJsonData=JsonUtil.getJsonObjectFromString(DesNewUtil.decrypt(temp));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else
        {
            mJsonData = JsonUtil.getJsonObject(jsonObject, "bizContent");
        }



        if (mJsonData == null)
        {
            Log.e(TAG, "json data is null.");
            return;
        }
        parseJsonData(mJsonData);

    }

    public abstract void parseJsonData(JSONObject jsonData);
}
