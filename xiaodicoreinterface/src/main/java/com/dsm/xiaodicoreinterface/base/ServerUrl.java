package com.dsm.xiaodicoreinterface.base;

/**
 * Created by yanfa on 2016/8/10.
 * 客户端与服务器交互访问接口地址
 */
public class ServerUrl {
    private static final String tag = ServerUrl.class.getSimpleName();

    private static ServerUrl serverUrl;

    public static ServerUrl getInstance() {
        if (serverUrl == null) {
            serverUrl = new ServerUrl();
        }
        return serverUrl;
    }

    public void setServer(String _interfaceServer, String _htmlServer) {
        interfaceServer = _interfaceServer;
        htmlServer = _htmlServer;
        serverUrl = new ServerUrl();
    }

    /**
     * 接口服务器地址
     */
//    public static String interfaceServer = "http://dsmzg.com:8080";//正式服务器IP
    public static String interfaceServer = "http://192.168.1.186:8899";//测试服务器IP
//        public static String interfaceServer = "http://115.236.188.90:8899";//测试服务器IP
//    public static String interfaceServer = "http://192.168.1.186:8080";//开发服务器IP
//        public static String interfaceServer = "http://192.168.31.217:8080";//郭小亮IP
//        public static String interfaceServer = "http://192.168.31.108:8080";//沈建林IP
//        public static String interfaceServer = "http://192.168.1.112:8080";//梦瑶IP
//            public static String interfaceServer = "http://192.168.31.206:8080";//梦瑶IP
//        public static String interfaceServer = "http://192.168.31.102:8080";//苗发IP
//        public static String interfaceServer = "http://192.168.1.97:8080";//钱磊IP2
//        public static String interfaceServer = "http://192.168.31.98:8080";//chao
//        public static String interfaceServer = "http://121.43.225.110:8080";//110

    /**
     * 页面服务器地址
     */
//    public static String htmlServer = "http://dsmzg.com:8080";//正式服务器IP
    public static String htmlServer = "http://192.168.1.186:8899";//测试服务器IP
//    public static String htmlServer =  "http://115.236.188.90:8899";//测试服务器IP
//    public static String htmlServer =  "http://192.168.1.186:8080";//开发服务器IP
//    public static String htmlServer = "http://192.168.1.112:8080";//梦瑶IP
//    public static String htmlServer = "http://192.168.31.206:8080";//梦瑶IP
//    public static String htmlServer = "http://192.168.31.108:8080";//沈建林IP
//    public static String htmlServer = "http://192.168.31.98:8080";//chao
//    public static String htmlServer = "http://121.43.225.110:8080";//110

    /**
     * html页面
     */
//    public String add_device = htmlServer + "/lock2/xiaodi/jsp/base/adddevice/addDevice.jsp";//添加设备
//    public String device_setting = htmlServer + "/lock2/xiaodi/jsp/senior/lockinfo/lockSetting.jsp";//设置
    /**
     * html页面
     */
    public String add_device = htmlServer + "/lock2/xiaodi/html/base/adddevice/addDevice.html";//添加设备
    public String device_setting = htmlServer + "/lock2/xiaodi/html/senior/lockinfo/lockSetting.html";//设置
    public String pay = htmlServer + "/lock2/xiaodi/html/pay/prepay.html";//支付

    /**
     * 用户模块 0x4000开始
     */
    //安装人员相关
    public String url_getCommunityList = interfaceServer + "/greentown/re/getCommunityList.action";
    public int url_getCommunityList_what = 0x4000;
    public String url_addGreenTownDevice = interfaceServer + "/greentown/re/addGreenTownDevice.action";
    public int url_addGreenTownDevice_what = 0x4001;
    public String url_delGreenTownDevice = interfaceServer + "/greentown/re/deleteDevice.action";
    public int url_delGreenTownDevice_what = 0x4002;
    public String url_addGreenDevice = interfaceServer + "/greentown/re/addDevice.action";

    /**
     * 用户模块 0x1000开始
     */
    //获取广告列表
    public String url_get_bar_list = interfaceServer + "/lock2/app/getLockAdverList.action";
    public int url_get_bar_list_what = 0x1000;
    //获取菜单列表
    public String url_get_menu_list = interfaceServer + "/lock2/app/queryBaseAppMenuList.action";
    public int url_get_menu_list_what = 0x1001;
    //注册
    public String url_register = interfaceServer + "/lock2/app/registerUser.action";
    public int url_register_what = 0x1002;
    //修改密码
    public String url_forget_pwd = interfaceServer + "/lock2/app/updateUserPassword.action";
    public int url_forget_pwd_what = 0x1003;
    //登录
    public String url_login = interfaceServer + "/base/user/login";
    public int url_login_what = 0x1004;
    //获取注册验证码
    public String url_getcode = interfaceServer + "/base/app/insertMessageNum.action";
    public int url_getcode_what = 0x1005;
    //取消验证码（未使用）
    public String url_cancel_code = interfaceServer + "";
    public int url_cancel_code_what = 0x1006;
    //更新用户信息
    public String url_full_userinfo = interfaceServer + "/base/user/user/updateUserFullInfo.action";
    public int url_full_userinfo_what = 0x1007;
    //获取设备列表
    public String url_device_getlist = interfaceServer + "/base/app/showHomePageItem.action";
    public int url_device_getlist_what = 0x1008;
    //获取锁具信息
    public String url_get_lock_info = interfaceServer + "/lock/getLockMsgByMac.action?content=";
    public int url_get_lock_info_what = 0x1009;
    //检查手势密码
    public String url_check_gesture = interfaceServer + "/lock/checkHandOpenPwd.action";
    public int url_check_gesture_what = 0x100A;
    //设置手势密码
    public String url_set_gesture = interfaceServer + "/lock/upUserHandOpenPwd.action";
    public int url_set_gesture_what = 0x100B;
    //更新手机开门密码（h5）
    public String url_update_lock_pwd = interfaceServer + "/lock/upUserOpenPwd.action";
    public int url_update_lock_pwd_what = 0x100C;
    //更新用户个性化开门方式
    public String url_update_user_open_type = interfaceServer + "/lock/upUserOpenType.action";
    public int url_update_user_open_type_what = 0x100D;
    //锁具重命名
    public String url_update_lock_name = interfaceServer + "/lock/resetLockName.action";
    public int url_update_lock_name_what = 0x100E;
    //获取锁上用户列表
    public String url_get_user_list_by_mac = interfaceServer + "/lock/getaccountListBylockseq.action";
    public int url_get_user_list_by_mac_what = 0x100F;
    //获取临时钥匙
    public String url_get_lock_temp_user_list = interfaceServer + "/base/app/queryLockTempUserList.action";
    public int url_get_lock_temp_user_list_what = 0x1010;
    //检验高级设置权限
    public String url_check_senior = interfaceServer + "/lock/getAdvancedSettingFlagByUser.action";
    public int url_check_senior_what = 0x1011;
    //修改开门提示语
    public String url_update_open_music = interfaceServer + "/lock/upUserMusic.action";
    public int url_update_open_music_what = 0x1012;
    //离线数据同步
    public String url_sync_lock = interfaceServer + "/lock/app/offlineLockSync.action";
    public int url_sync_lock_what = 0x1013;
    //APP登录时获得用户手机信息 用户账号 phone 必填,设备序列号 phoneSnnum 必填,手机名称 phoneName 选填,手机型号 phoneType 选填,手机软件版本号 phoneSoftversion  选填,手机蓝牙mac phoneBlueaddress 选填
    public String url_addLockUserPhoneInfo = interfaceServer + "/base/app/addLockUserPhoneInfo.action";
    public int url_addLockUserPhoneInfo_what = 0x1014;
    //更新用户推送信息
    public String url_updateUserPushInfo = interfaceServer + "/base/user/updateUserPushInfo.action?content=";
    public int url_updateUserPushInfo_what = 0x1015;
    //base64头像图片上传
    public String url_upload_user_phone_base64 = interfaceServer + "/base/user/uploadBase64UserPhoto.action";
    public int url_upload_user_phone_base64_what = 0x1016;
    //用户退出
    public String url_user_logout = interfaceServer + "/base/user/logout.action";
    public int url_user_logout_what = 0x1017;
    //分享开门密码
    public String url_distributeAuth = interfaceServer + "/lock2/app/distributeAuthToMate.action";
    public int url_distributeAuth_what = 0x1018;
    //保存临时密钥 authAccount; 生成者的账号，lockseq锁具地址， msgCodeList临时密码列表
    public String url_insertTempLockPwd = interfaceServer + "/lock2/app/insertTempLockPwd.action";
    public int url_insertTempLockPwd_what = 0x101A;
    //获取默认的帮助信息(接口名与url信息不一致，请核实)
    public String url_getDefaultHelpInfo = interfaceServer + "/base/app/showDefaultItem.action";
    public int url_getDefaultHelpInfo_what = 0x101B;
    //获取某个问题
    public String url_getSearchHelpInfo = interfaceServer + "/base/app/getQuestionByItem.action?question=";
    public int url_getSearchHelpInfo_what = 0x101C;
    //保存某个问题
    public String url_insertQuestion = interfaceServer + "/base/app/InsertQuestionItem.action";
    public int url_InsertQuestionItem_what = 0x101D;
    //当分享一次性钥匙成功时调用插入命令
    public String url_insertLockTempUserItem = interfaceServer + "/base/app/insertLockTempUserItem.action?content=";// 当分享一次性钥匙成功时调用插入命令 ( content==分享人的手机号|被分享的手机号|lockmac )
    public int url_insertLockTempUserItem_what = 0x101C;
    //获取活动广告列表
    public String url_get_activity_bar_list = interfaceServer + "/lock2/app/getPopupAds.action";
    public int url_get_activity_bar_list_what = 0x101D;

    /**
     * 锁具高级设置 0x2000起
     */
    //根据锁具序列号获得锁具下的用户列表
    public String url_getAccountListByLockSeq = interfaceServer + "/lock/getaccountListBylockseq.action?content=";//根据锁具序列号获得锁具下的用户列表
    public int url_getAccountListByLockSeq_what = 0x2000;
    //获取亲情账号关联列表 根据锁序列号
    public String url_getLoveAccountRelationByLockSeq = interfaceServer + "/lock/getLoveAccountRelationByLockSeq.action?content=";//获取亲情账号关联列表 根据锁序列号
    public int url_getLoveAccountRelationByLockSeq_what = 0x2001;
    //获取报警账号关联列表 根据所序列号
    public String url_getAlarmAccountRelationByLockSeq = interfaceServer + "/lock/getAlarmAccountRelationByLockSeq.action?content=";//获取报警关联列表 cotent == 锁具序列号
    public int url_getAlarmAccountRelationByLockSeq_what = 0x2002;
    //根据锁具mac地址与锁上指纹ID获取指纹详情
    public String url_getUserFingerInfo = interfaceServer + "/lock/app/getFingerByMacAndFindgerId.action";//根据锁具mac地址与锁上指纹ID获取指纹详情  mac=&fingerLockId=
    public int url_getUserFingerInfo_what = 0x2003;
    //获取指纹列表
    public String url_getUserFingerInfoList = interfaceServer + "/lock/getAccountFinger.action?content=";//获取指纹列表 传入锁序列号或者锁具序列号与用户账号
    public int url_getUserFingerInfoList_what = 0x2004;
    //更新锁具报警密码
    public String url_updateLockAlarmPassword = interfaceServer + "/lock/updateLockAlarmPassword.action?content=";//锁具mac|锁具报警密码
    public int url_updateLockAlarmPassword_what = 0x2005;
    //设置锁具报警密码开关
    public String url_updateLockAlarmPasswordState = interfaceServer + "/lock/updateLockAlarmPasswordState.action";//报警密码状态更新 content ----锁具mac|密码状态 0：无效 1：有效
    public int url_updateLockAlarmPasswordState_what = 0x2006;
    //更新密码报警关联关系
    public String url_insertPasswdAlarmAccountRelationItem = interfaceServer + "/lock/insertPasswdAlarmAccountRelationItem.action";//插入报警关联信息(密码关联) 传入json 数组：alarmLockseq:锁具序列号 alarmAccount：当前登录账号 alarmAccountname ：当前登录账号昵称 alarmAlarmrelation：关联账号(报警密码) 被关联账号 alarmPassiverelationname：被关联账号昵 post方式请求
    public int url_insertPasswdAlarmAccountRelationItem_what = 0x2007;
    //删除报警关联关系
    public String url_delAlarmAccountRelationItem = interfaceServer + "/lock/delAlarmAccountRelationItem.action?content=";//删除报警某条关联账号
    public int url_delAlarmAccountRelationItem_what = 0x2008;
    //添加指纹报警关联关系
    public String url_insertAlarmAccountRelationItem = interfaceServer + "/lock/insertAlarmAccountRelationItem.action";//插入报警关联信息(指纹关联) 传入json 数组：alarmLockseq:锁具序列号 alarmAccount：当前登录账号 alarmAccountname ：当前登录账号昵称 alarmAlarmrelation：关联账号(目前是指纹ID) alarmAlarmrelationname：关联账号昵称 （指纹名称alarmPassiverelation：被关联账号 alarmPassiverelationname：被关联账号昵 post方式请求
    public int url_insertAlarmAccountRelationItem_what = 0x2009;
    //验证锁具在服务器是否已注册
    public String url_checkLockExistOrNotExist = interfaceServer + "/lock/checkLockExistOrNotExist.action?content=";//锁具序列号
    public int url_checkLockExistOrNotExist_what = 0x200A;
    //注册锁具
    public String url_registerLockInfo = interfaceServer + "/lock/registerlockinfo.action";//注册锁具信息 传入json数组：sequnces：锁具序列号 channelpwd：信道密码 managepwd：管理密码 lockname：锁具名称 account：目前登录帐号
    public int url_registerLockInfo_what = 0x200B;
    //添加亲情关联关系
    public String url_insertLoveAccountRelationItem = interfaceServer + "/lock/insertLoveAccountRelationItem.action";//传入json 数组：lvLockseq:锁具序列号 lvAccount：当前登录账号 lvAccountname ：当前登录账号昵称 lvRelationaccount：关联账号 lvRelationaccountname：关联账号昵称  lvLoveaccounttimeset：时效设置 lvPassiverelationaccount：被关联账号 lvPassiverelationaccountname：被关联账号昵称
    public int url_insertLoveAccountRelationItem_what = 0x200C;
    //删除亲情关联关系
    public String url_delLoveAccountRelationItem = interfaceServer + "/lock/delLoveAccountRelationItem.action?content=";//根据亲情关联ID删除亲情关联关系
    public int url_delLoveAccountRelationItem_what = 0x200D;
    //更新锁具开锁方式
    public String url_updateOpenDoorType = interfaceServer + "/lock/updateUserLockOpenType.action?content=";//修改用户开锁方式 用户账号|锁具序列号|开锁方式
    public int url_updateOpenDoorType_what = 0x200E;
    //更新用户时效
    public String url_updateUserTimeSet = interfaceServer + "/lock/updateUserTimeSet.action?content=";//更新用户时效设置 用户账号|锁具序列号|时效设置 时效 ： 时间|星期|日期   全天-|-|-
    public int url_updateUserTimeSet_what = 0x200F;
    //添加指纹
    public String url_addFinger = interfaceServer + "/lock/saveFingerInfo.action";//保存指纹信息到数据库中 账号登录名|锁具序列号 |人员账号|锁具生成指纹ID|指纹名称
    public int url_addFinger_what = 0x2010;
    //更新指纹名称
    public String url_updateFingerName = interfaceServer + "/lock/updateFingerName.action";//更新指纹名称 id |指纹名称
    public int url_updateFingerName_what = 0x2011;
    //删除指纹
    public String url_delFingerInfo = interfaceServer + "/lock/delFingerInfo.action";//删除指纹 id
    public int url_delFingerInfo_what = 0x2012;
    //添加用户
    public String url_addUser = interfaceServer + "/lock/saveUserLockRelation.action";//给锁具中添加新的用户   传入json 数组   lockseq useraccout nickname  manageaccount
    public int url_addUser_what = 0x2013;
    //删除用户
    public String url_delLockUserItem = interfaceServer + "/lock/delLockUserItem.action";//单个账号删除 锁具序列号|人员账号
    public int url_delLockUserItem_what = 0x2014;
    //注销锁具
    public String url_cleanLockInfoFromDateBase = interfaceServer + "/lock/cleanLockInfoFromDateBase.action?content=";//注销锁具
    public int url_cleanLockInfoFromDateBase_what = 0x2015;
    //更新锁具软件版本号信息
    public String url_updateLockSoftVersion = interfaceServer + "/lock/updateLockSoftVersion.action?content=";//更新锁具软件版本号 锁具mac|软件版本号
    public int url_updateLockSoftVersion_what = 0x2016;
    //获取最新固件版本号
    public String url_getLockTwoVersion = interfaceServer + "/base/app/getLockTwoVersion.action";//"http://121.43.225.110:8080/base/app/getLockTwoVersion.action";//
    public int url_getLockTwoVersion_what = 0x2017;
    //配置WIFI
    public String url_updateLockWIFI = interfaceServer + "/lock/updateLockWIFI.action";//content ----锁具mac|SSID|password
    public int url_updateLockWIFI_what = 0x2018;
    //更新锁具开锁密码
    public String url_updateLockPassword = interfaceServer + "/lock/updateLockPassword.action?content=";//更新锁具开门密码 --用于锁具机械按键	content=锁具mac|锁具密码
    public int url_updateLockPassword_what = 0x2019;
    //更新开锁密码状态
    public String url_updateLockPasswordState = interfaceServer + "/lock/updateLockPasswordState.action?content=";//更新锁具开门密码 --用于锁具机械按键	content=锁具mac|密码状态    0：无效 1：有效
    public int url_updateLockPasswordState_what = 0x201A;
    //锁具是否上传开门记录
    public String url_updateLockOpenLogToggle = interfaceServer + "/lock/updateLockOpenRecordType.action?content=";//锁具mac|是否上传开门记录 0：不上传 1：上传
    public int url_updateLockOpenLogToggle_what = 0x201B;
    //上传开门记录
    public String url_insertDoorRecordByLockSeq = interfaceServer + "/lock/insertDoorRecordByLockSeq.action";//上传开门记录 content=锁具mac|account
    public int url_insertDoorRecordByLockSeq_what = 0x201C;
    //查询物业欠费
//    public String url_user_property = interfaceServer + "/base/user/searchPropertyFee";//物业欠费情况
    public String url_user_property = interfaceServer + "/greentown/re/getListReFee.action";//物业欠费情况
    public int url_user_property_what = 0x201D;
    //查询一次性钥匙是否有效
    public String url_queryLockTempUserList = interfaceServer + "/base/app/queryLockTempUserList.action?content=";// 获取设备信息列表  0表示失败 1表示成功  用于用户点击临时钥匙的时候判断是否有权限点击 ( content==account|lockmac )
    public int url_queryLockTempUserList_what = 0x201E;
    //使一次性钥匙时效
    public String url_updateLockTempUserItem = interfaceServer + "/base/app/updateLockTempUserItem.action?content=";// 当用户点击一次性钥匙后提交更新，使一次性钥匙失效  ( content==account|lockmac )
    public int url_updateLockTempUserItem_what = 0x201F;
    //更新WIFI开关状态
    public String url_updateLockWIFIState = interfaceServer + "/lock/updateLockWIFIState.action";
    public int url_updateLockWIFIState_what = 0x2020;
    //锁上用户重命名
    public String url_updateUserLockNickName = interfaceServer + "/lock/updateUserLockNickName.action";
    public int url_updateUserLockNickName_what = 0x2021;
    //查询锁上临时密码
    public String url_getAuthNumberByExample = interfaceServer + "/lock2/app/getAuthNumberByExample.action";
    public int url_getAuthNumberByExample_what = 0x2022;
    //根据报警关联ID查询报警关联详情
    public String url_getAlarmAccountRelationById = interfaceServer + "/lock/app/getAlarmAccountRelationById.action";
    public int url_getAlarmAccountRelationById_what = 0x2023;
    //查询开门记录
    public String url_getOpenDoorRecordList = interfaceServer + "/lock/lock/getOpenDoorRecordList.action";
    public int url_getOpenDoorRecordList_what = 0x2024;
    //最新物业通知查询
    public String url_queryNewNotice = interfaceServer + "/greentown/reApp/queryNewestReNototice.action";
    public int url_queryNewNotice_what = 0x2025;
    //物业欠费新接口
    public String url_property_own = interfaceServer + "/pay/user/queryUserBriefFee.action";
    public int url_property_own_what = 0x2026;
    //单个物业欠费详情
    public String url_property_own_detail = interfaceServer + "/pay/user/queryUserDetailFee.action";
    public int url_property_own_detail_what = 0x2027;
    //生成订单
    public String url_create_order = interfaceServer + "/pay/alipay/alipayParamString.action";
    public int url_create_order_what = 0x2028;

    /**
     * 海南项目请求 0x3000开始
     */
    public static final String url_hn_device_update = interfaceServer + "/hire/upDateHnDeviceItem.action";
    public final static int url_hn_device_update_what = 0x3000;
    public static final String url_hn_auth_cancel = interfaceServer + "/hire/cancelAuthorityToHirerById.action?hireId=";
    public final static int url_hn_auth_cancel_what = 0x3001;
    public static final String url_hn_open_remote = interfaceServer + "/hire/OpenDoorByRemoteOperation.action?macStr=";
    public final static int url_hn_open_remote_what = 0x3002;
    /**
     * 其他 0x4000起
     */
    //上传bug
    public String url_insertBugRecord = interfaceServer + "/base/app/insertBugRecord.action";
    public int url_insertBugRecord_what = 0x4000;
    //检测app版本
    public String url_getAppVersion = interfaceServer + "/base/app/getAppVersion.action";//"http://121.43.225.110:8080/base/app/getAppVersion.action";//
    public int url_getAppVersion_what = 0x4001;
    //下载app
    public String url_appDownload = interfaceServer + "/base/appapk/DsmApp.apk";
    public int url_appDownload_what = 0x4002;
    //下载Html
    public String url_htmlDownload = interfaceServer + "/lock2/xiaodi/html.zip";
    public int url_htmlDownload_what = 0x4003;
    //下载dex
    public String url_updateDexVersion = interfaceServer + "/base/app/getAndroidPudding.action";
    public int url_updateDexVersion_what = 0x4003;

    /**
     * 智能钥匙
     */
    public String url_queryLockSmartkey = interfaceServer + "/lock2/smartkey/queryLockSmartkey.action";//查询智能钥匙列表    锁具mac(lockMac)
    public int url_queryLockSmartkey_what = 0x5001;
    public String url_addLockSmartkey = interfaceServer + "/lock2/smartkey/addLockSmartkey.action";//添加智能钥匙 蓝牙钥匙编号(keyCode) 蓝牙钥匙密码(keyPass) 锁具mac(lockMac)
    public int url_addLockSmartkey_what = 0x5002;
    public String url_delLockSmartkey = interfaceServer + "/lock2/smartkey/delLockSmartkey.action";//删除智能钥匙 蓝牙钥匙编号(keyCode) 蓝牙钥匙密码(keyPass) 锁具mac(lockMac)
    public int url_delLockSmartkey_what = 0x5003;
    public String url_syncLockSmartkey = interfaceServer + "/lock2/smartkey/syncLockSmartkey.action";//同步蓝牙钥匙上的锁具列表 蓝牙钥匙密码(keyPass) 锁具mac(lockMac)  锁具mac地址列表(CE:D2:DD:B6:CE:E1,CO:D2:DY:B6:CP:E2 lockMac)
    public int url_syncLockSmartkey_what = 0x5004;
    public String url_resetSmartkey = interfaceServer + "/lock2/smartkey/resetSmartkey.action";//重置蓝牙钥匙的锁具关联    蓝牙钥匙编号(keyCode) 蓝牙钥匙密码(keyPass)
    public int url_resetSmartkey_what = 0x5005;
    public String url_cleanLockSmartkey = interfaceServer + "/lock2/smartkey/cleanLockSmartkey.action";//重置锁下面的蓝牙钥匙 锁具mac(lockMac)
    public int url_cleanLockSmartkey_what = 0x5006;
    public String url_updateSmartKeyName = interfaceServer + "/lock2/smartkey/updateSmartKeyName.action";//修改蓝牙钥匙别名 蓝牙钥匙编号(keyCode) 蓝牙钥匙密码(keyPass) 锁具mac(lockMac) 蓝牙钥匙新名称(keyName)
    public int url_updateSmartKeyName_what = 0x5007;
    public String url_querySmartkeyRelation = interfaceServer + "/lock2/smartkey/querySmartkeyRelation.action";//查询智能钥匙的设备关联信息  蓝牙钥匙编号(keyCode) 蓝牙钥匙密码(keyPass)
    public int url_querySmartkeyRelation_what = 0x5008;
    public String url_updateSmartkeyState = interfaceServer + "/lock2/smartkey/updateSmartkeyState.action";//修改蓝牙钥匙的状态  蓝牙钥匙编号(keyCode) 蓝牙钥匙密码(keyPass) 锁具mac(lockMac) 蓝牙钥匙状态(state)
    public int url_updateSmartkeyState_what = 0x5009;

}

