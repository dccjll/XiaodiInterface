package com.dsm.xiaodicoreinterface.base;

import android.app.Application;

import com.dsm.xiaodicoreinterface.util.LogUtil;
import com.dsm.xiaodicoreinterface.util.NoHttpUtil;
import com.dsm.xiaodicoreinterface.util.RegexUtil;
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
            if("checkCodeType".equalsIgnoreCase(key) && (!"1".equalsIgnoreCase(value.toString()) && !"2".equalsIgnoreCase(value.toString()))){
                LogUtil.e("error " + key);
                return false;
            }
            if(("mobile".equalsIgnoreCase(key) || "account".equalsIgnoreCase(key) || "user.account".equalsIgnoreCase(key) || "user.item1".equalsIgnoreCase(key) || "userCode".equalsIgnoreCase(key) || "useraccout".equalsIgnoreCase(key) || "userMobile".equalsIgnoreCase(key)) && (StringUtil.isEmpty(value.toString()) || value.toString().length() != 11)){
                LogUtil.e("error " + key);
                return false;
            }
            if(("password".equalsIgnoreCase(key) || "user.password".equalsIgnoreCase(key)) && (StringUtil.isEmpty(value.toString()) || !RegexUtil.regexChars(value.toString(), 8, 20))){
                LogUtil.e("error " + key);
                return false;
            }
            if("shortNum".equalsIgnoreCase(key) && StringUtil.isEmpty(String.valueOf(value))){
                LogUtil.e("error " + key);
                return false;
            }
            if("user.nickname".equalsIgnoreCase(key) && (StringUtil.isNotEmpty(value.toString()) && value.toString().length() > 20)){
                LogUtil.e("error " + key);
                return false;
            }
            if("user.cardtype".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || !"1".equalsIgnoreCase(value.toString()))){
                LogUtil.e("error " + key);
                return false;
            }
            if("user.cardnum".equalsIgnoreCase(key) && (StringUtil.isNotEmpty(value.toString()) && !RegexUtil.regexIDCard(value.toString()))){
                LogUtil.e("error " + key);
                return false;
            }
            if("user.useraddress".equalsIgnoreCase(key) && (StringUtil.isNotEmpty(value.toString()) && value.toString().length() > 80)){
                LogUtil.e("error " + key);
                return false;
            }
            if("imageDates".equalsIgnoreCase(key) && value == null){
                LogUtil.e("error " + key);
                return false;
            }
            if("phoneSnnum".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("phoneName".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("phoneType".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("systemVersion".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("phoneSoftversion".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("phoneBlueaddress".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("userType".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("appVersion".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("htmlVersion".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if(("deviceMac".equalsIgnoreCase(key) || "lockseq".equalsIgnoreCase(key)) && !StringUtil.checkAddress(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("deviceNewName".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            try {
                if("diyOpenType".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || Integer.valueOf(value.toString()) < 0 || Integer.valueOf(value.toString()) > 4)){
                    LogUtil.e("error " + key);
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
            try {
                if("music".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || Integer.valueOf(value.toString()) < 0 || Integer.valueOf(value.toString()) > 6)){
                    LogUtil.e("error " + key);
                    return false;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
            if("diyNumOpenPwd".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("diyGestureOpenPwd".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
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
