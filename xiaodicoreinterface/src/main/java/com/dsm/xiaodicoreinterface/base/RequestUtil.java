package com.dsm.xiaodicoreinterface.base;

import android.app.Application;

import com.dsm.xiaodicoreinterface.util.LogUtil;
import com.dsm.xiaodicoreinterface.util.NoHttpUtil;
import com.dsm.xiaodicoreinterface.util.StringUtil;

import java.util.Map;

/**
 * Created by dccjll on 2016/11/14.
 * 请求工具
 */

public class RequestUtil {

    /**
     * 验证请求参数
     * @param request   请求的参数集合
     * @return  验证成功或失败
     */
    public static boolean requestCheck(final Map<String, Object> request){
        if(request == null){
            LogUtil.e("null request");
            return false;
        }
        if(request.get("application") == null || request.get("onResponseListener") == null){
            LogUtil.e("null application or onResponseListener");
            return false;
        }
        for(Map.Entry<String, Object> entry : request.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if("url".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || !value.toString().startsWith("http://") && !value.toString().startsWith("https://"))){
                LogUtil.e("error " + key);
                return false;
            }
            if("what".equalsIgnoreCase(key) && value == null){
                LogUtil.e("error " + key);
                return false;
            }
            if("mobile".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || value.toString().length() != 11)){
                LogUtil.e("error " + key);
                return false;
            }
            if("password".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
        }
        return true;
    }

    /**
     * 网络请求封装
     * @param application   应用程序上下文
     * @param url   请求的url
     * @param what  请求的what
     * @param map   请求的参数列表
     * @param onResponseListener    请求响应处理器
     * @return  请求成功或失败
     */
    public static boolean request(Application application, String url, Integer what, Map<String, String> map, boolean encrypt, final OnResponseListener onResponseListener){
        Map<String, Object> dataMap = NoHttpUtil.buildObjectMap(new String[]{"application", "url", "what", "onResponseListener"}, new Object[]{application, url, what, map, onResponseListener});
        if(map != null){
            for(Map.Entry<String, String> entry : map.entrySet()){
                dataMap.put(entry.getKey(), entry.getValue());
            }
        }
        if(!requestCheck(dataMap)){
            return false;
        }
        if (encrypt) {
            NoHttpUtil.getInstance(application).asyncPostStringEncryptRequest(
                    url,
                    what,
                    map,
                    new NoHttpUtil.CommonResponseListener() {
                        @Override
                        public void requestSuccess(Object data) {
                            onResponseListener.onResponse(data);
                        }

                        @Override
                        public void requestFailed(String error) {
                            onResponseListener.onResponse(StringUtil.buildData(false, "", error));
                        }
                    });
        }else{
            NoHttpUtil.getInstance(application).asyncPostStringNoEncryptRequest(
                    url,
                    what,
                    map,
                    new NoHttpUtil.CommonResponseListener() {
                        @Override
                        public void requestSuccess(Object data) {
                            onResponseListener.onResponse(data);
                        }

                        @Override
                        public void requestFailed(String error) {
                            onResponseListener.onResponse(StringUtil.buildData(false, "", error));
                        }
                    });
        }
        return true;
    }
}
