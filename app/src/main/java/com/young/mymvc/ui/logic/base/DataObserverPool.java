package com.young.mymvc.ui.logic.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据观察池
 * @author: young
 * date:16/8/26  14:51
 */

public class DataObserverPool {

    /**
     * 整个应用对应的数据观察池
     * 对应一个key,就会有一队观察者
     */
    protected Map<String, List<DataObserver>> mObserverMap;

    public DataObserverPool()
    {
        mObserverMap = new HashMap<String, List<DataObserver>>();
    }

    /**
     * 添加监听者
     * @param key
     * @param observer
     */
    public void addObserver(String key, DataObserver observer)
    {
        List<DataObserver> observerList = getObservers(key);
        //观察池没有这个key，那就先创建一队
        if (observerList == null)
        {
            observerList = new ArrayList<DataObserver>();
            mObserverMap.put(key, observerList);
        }
        //将这个观察者加入到这个队
        observerList.add(observer);
    }

    /**
     * 根据key删除监听
     * @param key
     * @param observer
     */
    public void removeObserver(String key, DataObserver observer)
    {
        List<DataObserver> observerList = getObservers(key);
        if (observerList != null)
        {
            observerList.remove(observer);
        }
    }

    /**
     * 获取所有监听者
     * @param key
     * @return
     */
    public List<DataObserver> getObservers(String key)
    {
        List<DataObserver> observerList = mObserverMap.get(key);
        return observerList;
    }

    /**
     * 删除该key的所有监听
     * @param key
     */
    public void removeObservers(String key)
    {
        mObserverMap.remove(key);
    }


    /**
     * 获取所有监听key
     * @return
     */
    public Set<String> getAllKey()
    {
        return mObserverMap.keySet();
    }

    /**
     * 删除该监听者的所有监听
     * @param observer
     */
    public void removeObserver(DataObserver observer)
    {
        Set<String> keySet = mObserverMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            List<DataObserver> observerList = mObserverMap.get(key);

            observerList.remove(observer);
        }
    }

}
