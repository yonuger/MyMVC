package com.young.mymvc.ui.logic.base.protocol;

/**
 * 对应BaseRequest
 * @author: young
 * date:16/8/26  15:01
 */


public abstract class BaseResponse {

    protected BaseRequest mRequest;
    protected String mResultString;

    protected int mMsgCode = -1;
    protected String mMsg;
    protected int mTranCode;
    protected int isEncryption;//是否加密0否，1是

    public BaseRequest getmRequest()
    {
        return mRequest;
    }

    public void setmRequest(BaseRequest mRequest)
    {
        this.mRequest = mRequest;
    }

    public String getmResultString()
    {
        return mResultString;
    }

    public void setmResultString(String mResultString)
    {
        this.mResultString = mResultString;

        parse();
    }

    /**
     * 由子类来实现处理数据解析
     */
    public abstract void parse();

    public int getmMsgCode()
    {
        return mMsgCode;
    }

    public String getmMsg()
    {
        return mMsg;
    }

    public int getmTranCode()
    {
        return mTranCode;
    }

    public int getIsEncryption() {
        return isEncryption;
    }
}
