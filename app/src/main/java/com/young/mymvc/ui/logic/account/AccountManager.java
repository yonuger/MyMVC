package com.young.mymvc.ui.logic.account;

import com.young.mymvc.ui.logic.base.BaseLogicManager;
import com.young.mymvc.ui.logic.base.RequestDataCallback;
import com.young.mymvc.ui.logic.base.protocol.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * date:16/8/26  13:28
 */


public class AccountManager extends BaseLogicManager implements IAccountManager{

    protected List<String> mAuthCodeList = new ArrayList<>();

    protected static IAccountManager gShareInstance;

    public static IAccountManager getInstance() {
        if (gShareInstance == null) {
            gShareInstance = new AccountManager();
        }
        return gShareInstance;
    }

    @Override
    public void login(String account, String password, final RequestDataCallback callback) {
        LoginRequest request = new LoginRequest(account, password);
        //发送请求
        sendRequest(request, new SendRequestCallBack() {
            @Override
            public void onRequestSuccess(BaseResponse response) {
                LoginResponse loginResponse = (LoginResponse) response;

                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onRequestFailure(String errMsg) {
                if (callback != null) {
                    callback.onFailure(errMsg);
                }
            }
        });
    }
}
