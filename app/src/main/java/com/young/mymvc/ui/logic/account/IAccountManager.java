package com.young.mymvc.ui.logic.account;

import com.young.mymvc.ui.logic.base.RequestDataCallback;

/**
 * @author: young
 * date:16/8/26  13:30
 */
public interface IAccountManager {

    /**
     * 登录
     * @param account
     * @param password
     */
    void login(String account, String password, RequestDataCallback callback);
}
