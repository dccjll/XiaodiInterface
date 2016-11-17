package com.dsm.xiaodicoreinterface;

import android.app.Application;
import android.graphics.Bitmap;

import com.dsm.xiaodicoreinterface.base.OnResponseListener;
import com.dsm.xiaodicoreinterface.base.RequestUtil;
import com.dsm.xiaodicoreinterface.base.ServerUrl;
import com.dsm.xiaodicoreinterface.util.Base64Util;
import com.dsm.xiaodicoreinterface.util.NoHttpUtil;

/**
 * Created by dccjll on 2016/11/14.
 * 小嘀管家用户管理
 */

public class UserManage {

    /**
     * 获取验证码
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param  checkCodeType    验证码类型 1 注册时获取验证码 2 忘记密码时获取验证码
     * @param onResponseListener    响应监听器
     */
    public static void getCheckCode(Application application, String mobile, String checkCodeType, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "mobile", "checkCodeType", "onResponseListener"}, new Object[]{application, mobile, checkCodeType, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_getcode,
                ServerUrl.getInstance().url_getcode_what,
                NoHttpUtil.buildMap(new String[]{"content"}, new String[]{mobile + "|" + checkCodeType}),
                false,
                onResponseListener
        );
    }

    /**
     * 用户注册
     * @param application   应用程序上下文
     * @param account   用户手机号码
     * @param password  用户密码
     * @param shortNum  注册获取到的验证码
     * @param onResponseListener    响应监听器
     */
    public static void register(Application application, String account, String password, String shortNum, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_register,
                ServerUrl.getInstance().url_register_what,
                NoHttpUtil.buildMap(new String[]{"account", "password", "shortNum"}, new String[]{account, password, shortNum}),
                false,
                onResponseListener
        );
    }

    /**
     * 完善用户信息
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param nickName  用户昵称 可空
     * @param userAddress   用户地址 可空
     * @param cardType  证件类型 目前只支持身份证 类型为1
     * @param cardNum   证件号码 目前只支持身份证
     * @param onResponseListener    响应监听器
     */
    public static void updateUserInfo(Application application, String mobile, String nickName, String userAddress, String cardType, String cardNum, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_full_userinfo,
                ServerUrl.getInstance().url_full_userinfo_what,
                NoHttpUtil.buildMap(new String[]{"user.account", "user.nickname", "user.useraddress", "user.cardtype", "user.cardnum"}, new String[]{mobile, nickName, userAddress, cardType, cardNum}),
                false,
                onResponseListener
        );
    }

    /**
     * 上传用户图像
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param bitmap    用户图像
     * @param onResponseListener    响应监听器
     */
    public static void updateUserPhoto(Application application, String mobile, Bitmap bitmap, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_upload_user_phone_base64,
                ServerUrl.getInstance().url_upload_user_phone_base64_what,
                NoHttpUtil.buildMap(new String[]{"account", "imageDates", "fileContentType", "fileFileName1"}, new String[]{mobile, Base64Util.bitmapToBase64(bitmap), "jpg", System.currentTimeMillis() + ".jpg"}),
                false,
                onResponseListener
        );
    }

    /**
     * 更改用户密码
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param password  用户新密码
     * @param shortNum  忘记密码获取到的验证码
     * @param onResponseListener    响应监听器
     */
    public static void updateUserPassword(Application application, String mobile, String password, String shortNum, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_forget_pwd,
                ServerUrl.getInstance().url_forget_pwd_what,
                NoHttpUtil.buildMap(new String[]{"mobile", "password", "shortNum"}, new String[]{mobile, password, shortNum}),
                false,
                onResponseListener
        );
    }

    /**
     * 用户登录
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param pwd   用户密码
     * @param onResponseListener    响应监听器
     */
    public static void login(Application application, String mobile, String pwd, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_login,
                ServerUrl.getInstance().url_login_what,
                NoHttpUtil.buildMap(new String[]{"user.item1", "user.password"}, new String[]{mobile, pwd}),
                false,
                onResponseListener
        );
    }
}
