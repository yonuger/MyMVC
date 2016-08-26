package com.young.mymvc.ui.logic.base;

import java.lang.ref.WeakReference;

/**
 * @author: young
 * date:16/8/26  14:58
 */


public class DataObserver {

    private WeakReference<Object> mReferenceObserver = null;

    public DataObserver(Object observer)
    {
        mReferenceObserver = new WeakReference<Object>(observer);
    }


    public Object getObserver()
    {
        if (mReferenceObserver != null)
        {
            return mReferenceObserver.get();
        }
        return null;
    }
}
