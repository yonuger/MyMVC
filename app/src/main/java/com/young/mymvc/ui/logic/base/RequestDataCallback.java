package com.young.mymvc.ui.logic.base;

/**
 * @author: young
 * date:16/8/26  13:31
 */

public interface RequestDataCallback
{
    void onSuccess();

    void onFailure(String msg);
}
