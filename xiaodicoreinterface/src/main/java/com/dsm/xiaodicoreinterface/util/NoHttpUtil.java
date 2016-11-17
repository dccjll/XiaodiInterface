package com.dsm.xiaodicoreinterface.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created by Tony on 2016/7/11.
*/
public class NoHttpUtil {

    private final static String tag = NoHttpUtil.class.getSimpleName();

    private static RequestQueue mRequestQueue;
    private static List<Boolean> whatList = new ArrayList<Boolean>();
    private static DownloadQueue mDownloadQueue;
    private static NoHttpUtil mNoHttpInstance;

    public final static int HTTP_REQUEST_SUCCESS = 0x0001;
    public final static int HTTP_REQUEST_FAIL = 0x0000;
    public final static int MSG_NOHTTP_REQUEST_SHOW_DIALOG = 0x0001;
    public final static int MSG_NOHTTP_REQUEST_CLOSE_DIALOG = 0x0002;

    private static String url;
    private static Map<String, String> map;
    private static CommonResponseListener listener;

    private static DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    /**
     * 构造器
     *
     * @param application
     */
    public NoHttpUtil(Application application) {
        getNoHttpRequestQueue(application);
    }

    /**
     * 单例
     *
     * @param application
     * @return
     */
    public static NoHttpUtil getInstance(Application application) {
        if (mNoHttpInstance == null) {
            synchronized (NoHttpUtil.class) {
                if (mNoHttpInstance == null) {
                    mNoHttpInstance = new NoHttpUtil(application);
                }
            }
        }
        return mNoHttpInstance;
    }

    /**
     * 获取普通请求队列
     *
     * @param application
     * @return
     */
    private static RequestQueue getNoHttpRequestQueue(Application application) {
        if (mRequestQueue == null) {
            synchronized (NoHttpUtil.class) {
                if (mRequestQueue == null) {
                    NoHttp.initialize(application);
                    mRequestQueue = NoHttp.newRequestQueue();
                }
            }
        }
        return mRequestQueue;
    }

    /**
     * 获取文件下载请求队列
     *
     * @param application
     * @return
     */
    private static DownloadQueue getNoHttpDownloadQueue(Application application) {
        if (mDownloadQueue == null) {
            synchronized (NoHttpUtil.class) {
                if (mDownloadQueue == null) {
                    NoHttp.initialize(application);
                    mDownloadQueue = NoHttp.newDownloadQueue();
                }
            }
        }
        return mDownloadQueue;
    }

    /**
     * 下载图片
     *
     * @param context
     * @param imageUrl
     * @param listener
     */
    public static void downloadImage(Context context, String imageUrl, String path, String name, CommonDownloadImageResponseListener listener) {
        getNoHttpDownloadQueue(((Activity) context).getApplication()).add(0, NoHttp.createDownloadRequest(imageUrl, path, name, true, true), listener);
    }

    public abstract static class CommonDownloadImageResponseListener implements DownloadListener {
        @Override
        public void onDownloadError(int what, Exception exception) {
            downloadFailure(exception.getMessage());
        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

        }

        @Override
        public void onProgress(int what, int progress, long fileCount) {

        }

        @Override
        public void onFinish(int what, String filePath) {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            downloadSuccess(bitmap);
        }

        @Override
        public void onCancel(int what) {

        }

        public abstract void downloadSuccess(Bitmap bitmap);

        public abstract void downloadFailure(String error);
    }

    /**
     * 异步加密请求
     *
     * @param url
     * @param map
     * @param listener
     */
    public void asyncPostStringEncryptRequest(String url, int what, Map<String, String> map, CommonResponseListener listener) {
        asyncPostStringRequest(url, what, map, listener, true);
    }

    /**
     * 异步不加密请求
     *
     * @param url
     * @param map
     * @param listener
     */
    public void asyncPostStringNoEncryptRequest(String url, int what, Map<String, String> map, CommonResponseListener listener) {
        asyncPostStringRequest(url, what, map, listener, false);
    }

    /**
     * 异步请求总入口
     *
     * @param url
     * @param what
     * @param map
     * @param listener
     * @param encryptFlag
     */
    private void asyncPostStringRequest(String url, int what, Map<String, String> map, CommonResponseListener listener, boolean encryptFlag) {
        NoHttpUtil.url = url;
        NoHttpUtil.map = map;
        NoHttpUtil.listener = listener;

        // 取消队列中已开始的请求
        mRequestQueue.cancelBySign(what);

        // 创建请求对象
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);

        // 添加请求参数
        listener.setEncryptFlag(false);
        if(map == null){
            map = new HashMap<>();
        }
        LogUtil.i(tag, "NoHttp request 原始数据 url:"+ url+" ,map:"+map);
        if (encryptFlag) {
            listener.setEncryptFlag(true);
            map = EncryptUtil.encryptMap(map);
            LogUtil.i(tag, "NoHttp request 加密后数据 url:"+ url+" ,map:"+map);
        }
        request.add(map);

        // 设置请求取消标志
        request.setCancelSign(what);
        //设置超时
        request.setConnectTimeout(10000);
        /**
         * 客户端请求使用短连接,尝试解决以下异常
         *  03-14 09:49:18.547: W/System.err(6270): java.io.EOFException
            03-14 09:49:18.547: W/System.err(6270):     at libcore.io.Streams.readAsciiLine(Streams.java:203)
            03-14 09:49:18.547: W/System.err(6270):     at libcore.net.http.HttpEngine.readResponseHeaders(HttpEngine.java:573)
            03-14 09:49:18.547: W/System.err(6270):     at libcore.net.http.HttpEngine.readResponse(HttpEngine.java:821)
            03-14 09:49:18.547: W/System.err(6270):     at libcore.net.http.HttpURLConnectionImpl.getResponse(HttpURLConnectionImpl.java:283)
            03-14 09:49:18.547: W/System.err(6270):     at libcore.net.http.HttpURLConnectionImpl.getResponseCode(HttpURLConnectionImpl.java:495)
            03-14 09:49:18.547: W/System.err(6270):     at libcore.net.http.HttpsURLConnectionImpl.getResponseCode(HttpsURLConnectionImpl.java:134)
         */
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            request.setHeader("Connection", "close");
        }
        //https加密
//        if (url.startsWith("https")) {
//            SSLContextUtil.doHttps(request);
//        }
        LogUtil.i(tag, "NoHttp request Start:" + formatter.format(new Date()) + "," + url);

        //what = changeWhat(-1);
        // 向请求队列中添加请求
        // what: 当多个请求同时使用同一个OnResponseListener时，用来区分请求，类似Handler中的what
        mRequestQueue.add(what, request, listener);
    }

    /**
     * 异步请求响应结果回调对象
     */
    public abstract static class CommonResponseListener implements OnResponseListener<String> {

        boolean encryptFlag;

        public void setEncryptFlag(boolean encryptFlag) {
            this.encryptFlag = encryptFlag;
        }

        @Override
        public void onSucceed(int what, Response<String> response) {
            LogUtil.e(tag, "onSucceed \nurl:"+ response.url()+"\nresponse data:"+response.get());
            int responseCode = response.getHeaders().getResponseCode();
            if (responseCode > 400) {
                requestFailed("server exception");
                return;
            }
            String resultString;
            if (encryptFlag) {
                resultString = EncryptUtil.deEncrypt(response.get());
                LogUtil.i(tag, "response deEncrypt data:"+resultString);
            } else {
                resultString = response.get();
            }
            requestSuccess(resultString);
        }

        @Override
        public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
            requestFailed(exception.getMessage());
        }

        @Override
        public void onStart(int what) {

        }

        @Override
        public void onFinish(int what) {

        }

        public abstract void requestSuccess(Object data);

        public abstract void requestFailed(String error);
    }

    /**
     * 构建请求的post数据
     * @param keys  属性
     * @param values    数据
     * @return  返回数据
     */
    public static Map<String, String> buildMap(String[] keys, String[] values){
        Map<String, String> data = new HashMap<>();
        if(keys == null || values == null  || keys.length != values.length){
            return null;
        }
        for(int index=0;index<keys.length;index++){
            data.put(keys[index], values[index]);
        }
        return data;
    }

    /**
     * 构建请求的post数据2
     * @param keys  属性
     * @param values    数据
     * @return
     */
    public static Map<String, Object> buildObjectMap(String[] keys, Object[] values){
        Map<String, Object> data = new HashMap<>();
        if(keys == null || values == null  || keys.length != values.length){
            return null;
        }
        for(int index=0;index<keys.length;index++){
            data.put(keys[index], values[index]);
        }
        return data;
    }

    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = anInfo;
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            LogUtil.d(tag, "wifi network");
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            LogUtil.d(tag, "3G/4G network");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
