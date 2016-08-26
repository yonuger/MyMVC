package com.young.mymvc.ui.logic.base.protocol;

import com.young.mymvc.ui.util.DesNewUtil;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

/**
 * @author: young
 * date:16/8/26  13:34
 */

/**
 * 封装请求报文，里面的报文格式为
 * {
 *      "tranCode":0,
 *      "isEncryption":0,
 *      "bizContent":
 *          {
 *              "key":value,
 *          }
 *      }
 */

public abstract class BaseAppRequest extends BaseRequest{

    public BaseAppRequest()
    {
        try
        {
            mContentParam.put("tranCode", getTranCode());
            mContentParam.put("isEncryption",getIsEncryption());
            if (getIsEncryption()==0)
            {
                mContentParam.put("bizContent",mJsonParmas);
            }else
            {
                //对报文信息进行加密
                mContentParam.put("bizContent", DesNewUtil.encrypt(mJsonParmas.toString()));
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
