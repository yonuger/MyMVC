package com.young.mymvc.ui.logic.base.protocol;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: young
 * date:16/8/26  13:34
 */

public abstract class BaseRequest {

    /**
     * UDP报文传输一般有三种：
     *      json对象，json数组，文件
     */
    protected JSONObject mJsonParmas = new JSONObject();
    protected List<File> mFileParam = new ArrayList<>();
    protected JSONObject mContentParam=new JSONObject();

    /**
     * 获取服务器请求地址
     * @return
     */
    public String getUrl(){
        return "";
    }

    /**
     * 获取请求类名
     * @return
     */
    public String getRequestName(){
        return getClass().getSimpleName();
    }

    /**
     * 获取响应类名
     * @return
     */
    public String getResponseName(){
        String fullRequestName = getRequestName();
        return fullRequestName.replace("Request", "Response");
    }

    public JSONObject getJsonParam()
    {
        return mContentParam;
    }

    public List<File> getFileParam()
    {
        return mFileParam;
    }


    public abstract int getTranCode();

    /**
     * 是否加密
     * @return
     */
    public int getIsEncryption()
    {
        return 0;
    }

}
