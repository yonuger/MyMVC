package com.young.mymvc.ui.logic.base;

import android.util.Log;

import com.young.mymvc.ui.logic.base.protocol.BaseRequest;
import com.young.mymvc.ui.logic.base.protocol.BaseResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author: young
 * date:16/8/26  14:41
 */


public class BaseLogicManager {

    protected DataObserverPool mObserverPool;

    private static final String TAG = "BaseLogicManager";
    private static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");
    private static final MediaType IMAGE = MediaType.parse("image/*");

    public final static int CONNECT_TIMEOUT =60;
    public final static int READ_TIMEOUT=100;
    public final static int WRITE_TIMEOUT=60;

    protected String requestString = null;

    public interface SendRequestCallBack {
        void onRequestSuccess(BaseResponse response);

        void onRequestFailure(String errMsg);
    }

    //发送请求（request， callback）
    public void sendRequest(final BaseRequest request, final SendRequestCallBack callBack) {
        Log.d(TAG, request.getRequestName() + " send, " + request.getJsonParam().toString());
        String url = request.getUrl();  //获取网址

        String requestStr = request.getJsonParam().toString();

        JSONObject json = new JSONObject();

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间
                .build();

        MultipartBody.Builder build = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("reqStr", requestStr);

        if (request.getFileParam().size() > 0)
            for (File file : request.getFileParam()) {
                MultipartBody.Builder build1 = new MultipartBody.Builder()
                        .addPart(Headers.of("Content-Disposition", "form-data; filename=+"+ file.getPath() +"+"),
                                RequestBody.create(IMAGE, file));
                build.addFormDataPart("imgs", null, build1.build());
            }

        MultipartBody multipartBody = build.build();

        //发送请求
        Request req = new Request.Builder()
                .url(request.getUrl())
                .post(multipartBody)
                .build();

        //发送请求获取响应
        try {
            Response response=client.newCall(req).execute();
            //判断请求是否成功
            if(response.isSuccessful()){
                //打印服务端返回结果
                Log.i(TAG,response.body().string());
                ByteBuffer byteBuffer = ByteBuffer.wrap(response.body().bytes());
                String resultString = new String(byteBuffer.array());
                Log.d(TAG, request.getRequestName() + " success, result data : " + resultString);

                BaseResponse baseResponse = buildResponse(request);
                baseResponse.setmRequest(request);
                baseResponse.setmResultString(resultString);

                if (callBack != null) {
                    if (baseResponse.getmMsgCode() == 0) {
                        callBack.onRequestSuccess(baseResponse);
                    } else {
                        callBack.onRequestFailure(baseResponse.getmMsg());
                    }
                }
            }else{
                Log.e(TAG, request.getRequestName() + " failure, error msg : " + response.message());

                if (callBack != null) {
                    callBack.onRequestFailure(response.message());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static BaseResponse buildResponse(BaseRequest request) {
        if (request == null) {
            Log.e(TAG, "getResponse error, request null.");
            return null;
        }

        String responseName = request.getResponseName();

        if (responseName == null) {
            Log.e(TAG, "getResponse error, responseName null.");
            return null;
        }

        try {
            Class<BaseResponse> responseClass = (Class<BaseResponse>) Class.forName(responseName);
            BaseResponse response = responseClass.newInstance();
            return response;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "getResponse error, response class not found.");
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param key      要监听的key
     * @param observer 要被通知的observer
     */
    protected void addObserver(String key, Object observer) {
        if (mObserverPool == null) {
            mObserverPool = new DataObserverPool();
        }
        DataObserver logicManagerObserver = new DataObserver(observer);
        mObserverPool.addObserver(key, logicManagerObserver);
    }

    /**
     * @param key      要移除监听的key
     * @param observer 要被通知的observer
     */
    protected void removeObserver(String key, Object observer) {
        if (mObserverPool == null) return;

        List<DataObserver> logicManagerObservers = mObserverPool.getObservers(key);

        if (logicManagerObservers == null) return;

        DataObserver removeLogicManagerObserver = null;

        for (DataObserver logicManagerObserver : logicManagerObservers) {
            if (observer == logicManagerObserver.getObserver()) {
                removeLogicManagerObserver = logicManagerObserver;
                break;
            }
        }

        if (removeLogicManagerObserver != null) {
            mObserverPool.removeObserver(key,
                    removeLogicManagerObserver);
        }
    }

    /**
     * @param key
     */
    protected List<Object> getObservers(String key) {
        List<Object> observerObjectList = new ArrayList<Object>();

        if (mObserverPool == null)
            return observerObjectList;

        List<DataObserver> observers = mObserverPool.getObservers(key);
        if (observers != null) {
            for (DataObserver observer : observers) {
                Object observerObject = observer.getObserver();
                if (observerObject != null) {
                    observerObjectList.add(observerObject);
                }
            }
        }

        return observerObjectList;
    }

    /**
     * @param key
     */
    protected void removeObservers(String key) {
        if (mObserverPool == null)
            return;

        mObserverPool.removeObservers(key);
    }

    /**
     * @param observer
     */
    protected void removeObserver(Object observer) {
        if (mObserverPool == null) return;

        Set<String> keySet = mObserverPool.getAllKey();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            removeObserver(key, observer);
        }
    }
}
