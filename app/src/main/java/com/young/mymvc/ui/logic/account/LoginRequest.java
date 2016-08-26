package com.young.mymvc.ui.logic.account;

import android.util.Base64;

import com.young.mymvc.ui.logic.base.protocol.BaseAppRequest;

import org.json.JSONException;

/**
 * @author: young
 * date:16/8/26  13:32
 */

public class LoginRequest extends BaseAppRequest {

    /**
     * 具体功能则需要自己添加进去
     * @param username
     * @param password
     */
    public LoginRequest(String username, String password) {
        super();
        try
        {
            mJsonParmas.put("username", username);
            mJsonParmas.put("password", Base64.encodeToString(password.getBytes(), Base64.DEFAULT));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 辨识用的Trancode
     * @return
     */
    @Override
    public int getTranCode() {
        return 0;
    }
}
