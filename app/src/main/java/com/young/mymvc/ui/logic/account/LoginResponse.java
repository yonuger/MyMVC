package com.young.mymvc.ui.logic.account;

import com.young.mymvc.ui.logic.base.protocol.BaseAppResponse;
import com.young.mymvc.ui.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author: young
 * date:16/8/26  16:22
 */


public class LoginResponse extends BaseAppResponse {

    @Override
    public void parseJsonData(JSONObject jsonData) {

        JSONArray jsonArray = JsonUtil.getJsonArray(jsonData, "");

        JSONObject backLogJsonArray = JsonUtil.getJsonObject(jsonData, "backlogs");

    }
}
