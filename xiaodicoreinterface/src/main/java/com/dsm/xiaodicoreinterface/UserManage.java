package com.dsm.xiaodicoreinterface;

import android.app.Application;

import com.dsm.xiaodicoreinterface.base.OnResponseListener;
import com.dsm.xiaodicoreinterface.base.RequestUtil;
import com.dsm.xiaodicoreinterface.base.ServerUrl;
import com.dsm.xiaodicoreinterface.util.NoHttpUtil;

/**
 * Created by dccjll on 2016/11/14.
 * 小嘀管家用户管理
 */

public class UserManage {

    /**
     * 注册获取验证码
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param onResponseListener    响应监听器
     */
    public static void getRegisterCheckCode(Application application, String mobile, final OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "mobile", "onResponseListener"}, new Object[]{application, mobile, onResponseListener}))){
            return;
        }
        RequestUtil.request(application, ServerUrl.getInstance().url_getcode, ServerUrl.getInstance().url_getcode_what, NoHttpUtil.buildMap(new String[]{"content"}, new String[]{mobile + "|1"}), false, onResponseListener);
    }



    /**
     * 用户登录
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param pwd   用户密码
     * @param onResponseListener    响应监听器
     */
    public static void login(Application application, String mobile, String pwd, final OnResponseListener onResponseListener){
        RequestUtil.request(application, ServerUrl.getInstance().url_login, ServerUrl.getInstance().url_login_what, NoHttpUtil.buildMap(new String[]{"user.item1", "user.password"}, new String[]{mobile, pwd}), false, onResponseListener);
    }
}
