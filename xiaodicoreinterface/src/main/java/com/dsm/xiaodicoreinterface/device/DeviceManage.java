package com.dsm.xiaodicoreinterface.device;

import android.app.Activity;
import android.app.Application;

import com.dsm.xiaodicoreinterface.base.OnResponseListener;
import com.dsm.xiaodicoreinterface.base.RequestUtil;
import com.dsm.xiaodicoreinterface.base.ServerUrl;
import com.dsm.xiaodicoreinterface.util.NoHttpUtil;

/**
 * Created by dccjll on 2016/11/14.
 * 设备管理
 */

public class DeviceManage {

    /**
     * 获取用户设备列表
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param onResponseListener    响应监听器
     */
    public static void getUserDeviceList(Application application, String mobile, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_device_getlist,
                ServerUrl.getInstance().url_device_getlist_what,
                NoHttpUtil.buildMap(new String[]{"mobile"}, new String[]{mobile}),
                false,
                onResponseListener
        );
    }

    /**
     * 获取用户菜单列表
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param userType  用户类型
     * @param onResponseListener    响应监听器
     */
    public static void getUserMenuList(Application application, String mobile, String userType, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "mobile", "userType", "onResponseListener"}, new Object[]{application, mobile, userType, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_get_menu_list,
                ServerUrl.getInstance().url_get_menu_list_what,
                NoHttpUtil.buildMap(new String[]{"mobile", "type"}, new String[]{mobile, userType}),
                false,
                onResponseListener
        );
    }

    /**
     * 获取广告列表
     * @param application   应用程序上下文
     * @param onResponseListener    响应监听器
     */
    public static void getAdvertiseList(Application application, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_get_bar_list,
                ServerUrl.getInstance().url_get_bar_list_what,
                null,
                false,
                onResponseListener
        );
    }

    /**
     * 检测软件是否需要更新
     * @param application   应用程序上下文
     * @param appVersion    当前app软件版本号
     * @param htmlVersion   当前app离线html包版本号
     * @param onResponseListener    响应监听器
     */
    public static void checkAppVersion(Application application, String appVersion, String htmlVersion, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_getAppVersion,
                ServerUrl.getInstance().url_getAppVersion_what,
                NoHttpUtil.buildMap(new String[]{"appVersion", "htmlVersion"}, new String[]{appVersion, htmlVersion}),
                true,
                onResponseListener
        );
    }

    /**
     * 上传手机基本信息到服务器
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param phoneSerialNum    手机序列号
     * @param phoneBrand    手机品牌
     * @param phoneModel    手机型号
     * @param phoneSdkVersion   手机系统版本号
     * @param appVersionDesc    当前应用程序的版本号描述
     * @param phoneBluetoothAdapterAddress  手机的蓝牙适配器mac地址
     * @param onResponseListener    响应监听器
     */
    public static void updateClientInfo(Application application, String mobile, String phoneSerialNum, String phoneBrand, String phoneModel, String phoneSdkVersion, String appVersionDesc, String phoneBluetoothAdapterAddress, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_addLockUserPhoneInfo,
                ServerUrl.getInstance().url_addLockUserPhoneInfo_what,
                NoHttpUtil.buildMap(new String[]{"phone", "phoneSnnum", "phoneName", "phoneType", "systemVersion", "phoneSoftversion", "phoneBlueaddress"}, new String[]{mobile, phoneSerialNum, phoneBrand, phoneModel, phoneSdkVersion, appVersionDesc, phoneBluetoothAdapterAddress}),
                false,
                onResponseListener
        );
    }

    /**
     * 获取物业通知列表
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param onResponseListener    响应监听器
     */
    public static void getPropertyNoticeList(Application application, String mobile, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_queryNewNotice,
                ServerUrl.getInstance().url_queryNewNotice_what,
                NoHttpUtil.buildMap(new String[]{"account"}, new String[]{mobile}),
                true,
                onResponseListener
        );
    }

    /**
     * 获取物业欠费列表
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param onResponseListener    响应监听器
     */
    public static void getPropertyOwnList(Application application, String mobile, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_property_own,
                ServerUrl.getInstance().url_property_own_what,
                NoHttpUtil.buildMap(new String[]{"userCode"}, new String[]{mobile}),
                true,
                onResponseListener
        );
    }

    /**
     * 更新设备名称
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param deviceNewName 设备新名称
     * @param onResponseListener    响应监听器
     */
    public static void updateDeviceName(Application application, String deviceMac, String deviceNewName, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "deviceMac", "deviceNewName", "onResponseListener"}, new Object[]{application, deviceMac, deviceNewName, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_update_lock_name,
                ServerUrl.getInstance().url_update_lock_name_what,
                NoHttpUtil.buildMap(new String[]{"content"}, new String[]{deviceMac + "|" + deviceNewName}),
                false,
                onResponseListener
        );
    }

    /**
     * 更改用户手机个性化设置开门提示音
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param mobile    用户手机号码
     * @param music 用户个性化设置开门提示音 0到6之间
     * @param onResponseListener    响应监听器
     */
    public static void updateDiyOpenMusic(Application application, String deviceMac, String mobile, String music, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_update_open_music,
                ServerUrl.getInstance().url_update_open_music_what,
                NoHttpUtil.buildMap(new String[]{"account", "lockseq", "music"}, new String[]{mobile, deviceMac, music}),
                false,
                onResponseListener
        );
    }

    /**
     * 更改用户手机个性化设置开门方式
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param mobile    用户手机号码
     * @param diyOpenType   用户个性化设置开门方式 0 不用手机 1 密码开门 2 摇一摇开门 3 手势开门 4 点一点开门
     * @param onResponseListener    响应监听器
     */
    public static void updateDiyOpenType(Application application, String deviceMac, String mobile, String diyOpenType, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "deviceMac", "mobile", "diyOpenType", "onResponseListener"}, new Object[]{application, deviceMac, mobile, diyOpenType, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_update_user_open_type,
                ServerUrl.getInstance().url_update_user_open_type_what,
                NoHttpUtil.buildMap(new String[]{"account", "lockseq", "opentype"}, new String[]{mobile, deviceMac, diyOpenType}),
                false,
                onResponseListener
        );
    }

    /**
     * 更改用户手机个性化设置开门数字密码
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param mobile    用户手机号码
     * @param diyNumOpenPwd    用户个性化设置开门数字密码
     * @param onResponseListener    响应监听器
     */
    public static void updateDiyOpenNumPassword(Application application, String deviceMac, String mobile, String diyNumOpenPwd, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "deviceMac", "mobile", "diyNumOpenPwd", "onResponseListener"}, new Object[]{application, deviceMac, mobile, diyNumOpenPwd, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_update_lock_pwd,
                ServerUrl.getInstance().url_update_lock_pwd_what,
                NoHttpUtil.buildMap(new String[]{"account", "lockseq", "openpwd"}, new String[]{mobile, deviceMac, diyNumOpenPwd}),
                false,
                onResponseListener
        );
    }

    /**
     * 更改用户手机个性化设置开门手势密码
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param mobile    用户手机号码
     * @param diyGestureOpenPwd 用户个性化设置开门手势密码
     * @param onResponseListener    响应监听器
     */
    public static void updateDiyOpenGesturePassword(Application application, String deviceMac, String mobile, String diyGestureOpenPwd, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "deviceMac", "mobile", "diyGestureOpenPwd", "onResponseListener"}, new Object[]{application, deviceMac, mobile, diyGestureOpenPwd, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_set_gesture,
                ServerUrl.getInstance().url_set_gesture_what,
                NoHttpUtil.buildMap(new String[]{"account", "lockseq", "openpwd"}, new String[]{mobile, deviceMac, diyGestureOpenPwd}),
                false,
                onResponseListener
        );
    }

    /**
     * 进入高级设置验证
     * @param application   应用程序上下文
     * @param mobile    用户手机号码
     * @param password  用户登录密码
     * @param onResponseListener    响应监听器
     */
    public static void seniorCheck(Application application, String mobile, String password, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_check_senior,
                ServerUrl.getInstance().url_check_senior_what,
                NoHttpUtil.buildMap(new String[]{"useraccout", "lockseq"}, new String[]{mobile, password}),
                false,
                onResponseListener
        );
    }

    /**
     * 临时秘钥短信授权
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param mobile    登录用户手机号码
     * @param userMobile   被授权用户手机号码
     * @param onResponseListener    响应监听器
     */
    public static void tempSecretKeySmsAuthorise(Application application, String deviceMac, String mobile, String userMobile, OnResponseListener onResponseListener){
        if(!RequestUtil.requestCheck(NoHttpUtil.buildObjectMap(new String[]{"application", "deviceMac", "mobile", "userMobile", "onResponseListener"}, new Object[]{application, deviceMac, mobile, userMobile, onResponseListener}))){
            return;
        }
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_insertLockTempUserItem,
                ServerUrl.getInstance().url_insertLockTempUserItem_what,
                NoHttpUtil.buildMap(new String[]{"content"}, new String[]{mobile + "|" + userMobile + "|" + deviceMac}),
                true,
                onResponseListener
        );
    }

    /**
     * 临时密码短信授权
     * @param application   应用程序上下文
     * @param deviceMac 设备mac地址
     * @param mobile    登录用户手机号码
     * @param userMobile    被授权用户手机号码
     * @param onResponseListener    响应监听器
     */
    public static void tempPasswordAuthorise(Application application, String deviceMac, String mobile, String userMobile, OnResponseListener onResponseListener){
        RequestUtil.request(
                application,
                ServerUrl.getInstance().url_distributeAuth,
                ServerUrl.getInstance().url_distributeAuth_what,
                NoHttpUtil.buildMap(new String[]{"deviceMac", "mobile", "userMobile"}, new String[]{deviceMac, mobile, userMobile}),
                false,
                onResponseListener
        );
    }
}
