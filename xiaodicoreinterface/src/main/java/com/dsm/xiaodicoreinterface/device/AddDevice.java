//package com.dsm.xiaodicoreinterface.device;
//
//import android.app.Activity;
//import android.app.Application;
//import android.bluetooth.BluetoothGatt;
//import android.bluetooth.BluetoothGattCharacteristic;
//
//import com.dsm.xiaodicoreinterface.base.CheckUtil;
//import com.dsm.xiaodicoreinterface.base.OnXiaodiListener;
//import com.dsm.xiaodicoreinterface.util.LogUtil;
//import com.dsm.xiaodicoreinterface.util.NoHttpUtil;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
///**
// * Created by dccjll on 2016/11/14.
// * 小嘀管家添加设备
// */
//
//public class AddDevice {
//
//    private Application application;
//    private String locknameString;
//    private String lockmacString;
//    private String metertype;
//    private OnXiaodiListener onXiaodiListener;
//
//    private final static String tag = AddDevice.class.getSimpleName();
//    private String lockchannelpwd;
//    private String locksn;
//    private boolean online = false;
//
//    public AddDevice(Activity contentActivity, String locknameString, String lockmacString, String metertype, OnXiaodiListener onXiaodiListener) {
//        this.contentActivity = contentActivity;
//        this.locknameString = locknameString;
//        this.lockmacString = lockmacString;
//        this.metertype = metertype;
//        this.onXiaodiListener = onXiaodiListener;
//    }
//
//    public void walk() {
//        if(!CheckUtil.checkParams(NoHttpUtil.buildObjectMap(new String[]{"contentActivity", "locknameString", "lockmacString", "metertype", "onXiaodiListener"}, new Object[]{contentActivity, locknameString, lockmacString, metertype, onXiaodiListener}))){
//            return;
//        }
//        online = NoHttpUtil.checkNetworkAvailable(contentActivity);
//        checkBLEManagePassword();
//    }
//
//    private void checkBLEManagePassword() {
//        XIAODISend.send(lockmacString,
//                XIAODIBLECMDType.BLE_CMDTYPE_CHECKBLELOCKMANAGEPASSWORD,
//                new XIAODIData().setManagepwd("88888888".getBytes()),
//                false,
//                new OnBLEWriteDataListener() {
//                    @Override
//                    public void onWriteDataFinish() {
//                        LogUtil.i(tag + ",onWriteDataFinish");
//                    }
//
//                    @Override
//                    public void onWriteDataSuccess(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
//                        LogUtil.i(tag + ",onWriteDataSuccess");
//                    }
//
//                    @Override
//                    public void onWriteDataFail(Integer integer) {
//
//                    }
//                },
//                new XIAODIDataReceived(
//                        new byte[]{0x36},
//                        new OnXIAODIBLEListener.OnCommonListener() {
//                            @Override
//                            public void success(XIAODIDataReceivedAnalyzer xiaodiDataReceivedAnalyzer) {
//
//                            }
//
//                            @Override
//                            public void failure(Integer integer, XIAODIDataReceivedAnalyzer xiaodiDataReceivedAnalyzer) {
//
//                            }
//                        }
//                )
//        );
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().registerXIAODIBLEReceiver();
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().setResponseObj(
//                new XIAODIBLEHandler(
//                        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService(),
//                        new XIAODIDataSend().setCmd(new byte[]{0x36}),
//                        new OnXIAODIBLEListener.XIAODIBLECheckManagePasswordRetrunDataListener() {
//
//                            @Override
//                            public void checkBLEManagePasswordSuccess() {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "验证锁上管理面密码返回02，表示锁已被硬清空，将请求服务器清空该锁的所有信息");
//                                if (online) {
//                                    logoutLockOnServer();
//                                } else {
//                                    addLockDeviceForBLE();
//                                }
//                            }
//
//                            @Override
//                            public void checkBLEManagePasswordReturnSuccess() {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "验证锁上管理面密码返回00,验证通过，将执行蓝牙注册锁具指令");
//                                if (online) {
//                                    checkLockExistOrNotExist();
//                                } else {
////                                    addLockDeviceForBLE();
//                                    ToastUtil.showToast("设备已注册");
//                                }
//                            }
//
//                            @Override
//                            public void checkBLEManagePasswordReturnFailure(String error) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "验证锁上管理密码," + error);
//                                onResponse(false, englishTip, "", error_);
//                            }
//                        }));
//
//        XIAODIBLE.send(
//                online,
//                XIAODIBLEProtocolFactory.BLE_CMDTYPE_CHECKBLELOCKMANAGEPASSWORD,
//                lockmacString,
//                new XIAODIDataSendCenter().setManagepwd("88888888"),
//                true);
//    }
//
//    //服务器注销锁具
//    private void logoutLockOnServer() {
//        Map<String, String> postMap = new HashMap<String, String>();
//        postMap.put("content", lockmacString);
//        NoHttpUtil.getInstance(contentActivity.getApplication()).asyncPostStringEncryptRequest(
//                ServerUrl.getInstance().url_cleanLockInfoFromDateBase,
//                ServerUrl.getInstance().url_cleanLockInfoFromDateBase_what,
//                postMap,
//                new NoHttpUtil.CommonResponseListener(null) {
//                    @Override
//                    public void requestSuccess(Object dataList,String resultMessage) {
//                        LogUtil.d(tag, "服务器注销锁具成功");
//                        AppUtil.deleteLocalDeviceBySN(lockmacString);
//                        AppUtil.deleteRamDeviceBySN(lockmacString);
//                        addLockDeviceForBLE();
//                    }
//
//                    @Override
//                    public void requestFailed(Exception exception, boolean show) {
//                        LogUtil.d(tag, chineseTip + ",服务器注销锁具失败," + exception.getMessage());
//                        AppBLERunningConfig.isAddingDevice = false;
//                        disconect(contentActivity.getString(R.string.addDeviceFail));
//                    }
//                });
//
//    }
//
//    //验证当前注册锁具在服务器是否已注册
//    private void checkLockExistOrNotExist() {
//        final String tips = "验证当前注册锁具在服务器是否已注册";
//        Map<String, String> postMap = new HashMap<String, String>();
//        postMap.put("content", lockmacString);
//        NoHttpUtil.getInstance(contentActivity.getApplication()).asyncPostStringEncryptRequest(
//                ServerUrl.getInstance().url_checkLockExistOrNotExist,
//                ServerUrl.getInstance().url_checkLockExistOrNotExist_what,
//                postMap,
//                new NoHttpUtil.CommonResponseListener(null) {
//                    @Override
//                    public void requestSuccess(Object dataList,String resultMessage) {
//                        LogUtil.d(tag, tips + ",验证成功，锁具未注册，可以注册");
//                        addLockDeviceForBLE();
//                    }
//
//                    @Override
//                    public void requestFailed(Exception exception, boolean show) {
//                        LogUtil.d(tag, tips + ",验证失败," + exception.getMessage());
//                        AppBLERunningConfig.isAddingDevice = false;
//                        String msg = show ? exception.getMessage() : existDevice;
//                        disconect(msg);
//                    }
//                });
//    }
//
//    private void addLockDeviceForBLE() {
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().registerXIAODIBLEReceiver();
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().setResponseObj(
//                new XIAODIBLEHandler(
//                        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService(),
//                        new XIAODIDataSend().setCmd(new byte[]{0x34}),
//                        new OnXIAODIBLEListener.XIAODIBLEListener() {
//
//                            @Override
//                            public void bleOperateSuccess(XIAODIDataReceived mDataReceived) {
//                                try {
//                                    XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                    locksn = new String(ByteUtil.getSubbytes(mDataReceived.getDataArea(), 0, 15));
//                                    byte[] lockmacbytes = ByteUtil.getSubbytes(mDataReceived.getDataArea(), 15, 6);
//                                    lockmacString = ByteUtil.bytesToHexString(lockmacbytes).replace(" ", ":");
//                                    if (mDataReceived.getDataArea().length > 16) {
//                                        byte[] lockchannelpwdbytes = ByteUtil.getSubbytes(mDataReceived.getDataArea(), 21, 4);
//                                        lockchannelpwd = ByteUtil.bytesToHexString(lockchannelpwdbytes).replace(" ", "");
//                                    } else {
//                                        lockchannelpwd = "00000000";
//                                    }
//                                    LogUtil.d(tag, "0x34,蓝牙操作成功, locksn=" + locksn + ",lockmacString=" + lockmacString + ",lockchannelpwd=" + lockchannelpwd);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    AppBLERunningConfig.isAddingDevice = false;
//                                    disconect(error_);
//                                    return;
//                                }
//                                addUserMobileForBLE();
//                            }
//
//                            @Override
//                            public void bleOperateFailure(String error) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, chineseTip + ",蓝牙上执行注册锁具指令失败," + error);
//                                AppBLERunningConfig.isAddingDevice = false;
//                                disconect(error_);
//                            }
//                        }));
//
//        XIAODIBLE.send(
//                online,
//                XIAODIBLEProtocolFactory.BLE_CMDTYPE_REGISTERBLELOCKDEVICE,
//                lockmacString,
//                null,
//                true);
//    }
//
//    private void addUserMobileForBLE() {
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().registerXIAODIBLEReceiver();
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().setResponseObj(
//                new XIAODIBLEHandler(
//                        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService(),
//                        new XIAODIDataSend().setCmd(new byte[]{0x25}),
//                        new OnXIAODIBLEListener.XIAODIBLEListener() {
//
//                            @Override
//                            public void bleOperateSuccess(XIAODIDataReceived mDataReceived) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "0x25,蓝牙添加用户成功");
//                                if (online) {
//                                    addLockDeviceForServer();
//                                } else {
//                                    addLockDeviceForLocal();
//                                }
//                            }
//
//                            @Override
//                            public void bleOperateFailure(String error) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, chineseTip + ",蓝牙上执行添加用户指令失败," + error);
//                                AppBLERunningConfig.isAddingDevice = false;
//                                disconect(error_);
//                            }
//                        }));
//        XIAODIBLE.send(
//                online,
//                XIAODIBLEProtocolFactory.BLE_CMDTYPE_ADDBLELOCKMOBILEACCOUNT,
//                lockmacString,
//                new XIAODIDataSendCenter().setMobileaccount(UserManageCache.getmUser().getAccount().getBytes()),
//                true);
//    }
//
//    private void addLockDeviceForServer() {
//        Map<String, String> dataMap = new HashMap<String, String>();
//        dataMap.put("lockType", Statics.APP_LOCK_UPERCASE);
//        dataMap.put("sequnces", locksn);
//        dataMap.put("lockmac", lockmacString);
//        dataMap.put("managepwd", managepwdString);
//        dataMap.put("channelpwd", lockchannelpwd);
//        dataMap.put("lockname", locknameString);
//        dataMap.put("account", UserManageCache.getmUser().getAccount());
//        dataMap.put("accountName", UserManageCache.mUser.getNickname());
//        dataMap.put("metertype", metertype);
//        dataMap.put("lockLatitude", String.valueOf(Variables.getmLocation().getLatitude()));
//        dataMap.put("lockLongitude", String.valueOf(Variables.getmLocation().getLongitude()));
//        NoHttpUtil.getInstance(contentActivity.getApplication()).asyncPostStringEncryptRequest(
//                ServerUrl.getInstance().url_registerLockInfo,
//                ServerUrl.getInstance().url_registerLockInfo_what,
//                dataMap,
//                new NoHttpUtil.CommonResponseListener(null) {
//                    @Override
//                    public void requestSuccess(Object dataList,String resultMessage) {
//                        LogUtil.d(tag, "设备添加成功");
//                        Device device = buildAddedDevice();
//                        Variables.finalDB.save(device);
//                        Variables.mCurrentUserAllDevice = Variables.finalDB.findAllByWhere(Device.class, "mobile='" + device.getMobile() + "'");
//                        device = Variables.mCurrentUserAllDevice.get(Variables.mCurrentUserAllDevice.size() - 1);
//                        Variables.mCurrentUserAllDevice = AppUtil.sortDeviceList(Variables.mCurrentUserAllDevice);
//                        Variables.mDevice = device;
//                        Variables.mLockInfo = null;
//                        SharedPreferencesUtil.putString(contentActivity, DataPathConfig.CURRENT_DEVICE_MAC,device.getDeviceMac());
//                        syncTimeToBLE();
//                    }
//
//                    @Override
//                    public void requestFailed(Exception exception, boolean show) {
//                        LogUtil.d(tag, chineseTip + ",服务器设备添加失败," + exception.getMessage());
//                        deleteUserOnBLE();
//                    }
//                });
//    }
//
//    private void addLockDeviceForLocal() {
//        Device device = buildAddedDevice();
//        //删除原有设备
//        Variables.finalDB.deleteByWhere(Device.class, "mobile='" + device.getMobile() + "' and deviceMac='" + device.getDeviceMac() + "'");
//        Variables.finalDB.save(device);
//        Variables.mCurrentUserAllDevice = Variables.finalDB.findAllByWhere(Device.class, "mobile='" + device.getMobile() + "'");
//        device = Variables.mCurrentUserAllDevice.get(Variables.mCurrentUserAllDevice.size() - 1);
//        Variables.mCurrentUserAllDevice = AppUtil.sortDeviceList(Variables.mCurrentUserAllDevice);
//        Variables.mLockInfo = null;
//        Variables.mDevice = device;
//        SharedPreferencesUtil.putString(contentActivity, DataPathConfig.CURRENT_DEVICE_MAC,device.getDeviceMac());
//        syncTimeToBLE();
//    }
//
//    private void syncTimeToBLE() {
//        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));//2016-02-25 03:41:50
//        LogUtil.d(tag, "本地时间,time=" + time);
//        Calendar calendar = Calendar.getInstance();
//        try {
//            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(time));
//        } catch (ParseException e) {
//            e.printStackTrace();
//            LogUtil.d(tag, "设备添加成功，同步时间操作时日期格式解析失败");
//            onResponse(true, englishTip, "", info_);
//            return;
//        }
//        byte[] timebytes = new byte[7];
//        String[] timearray = new String[7];
//        timearray[0] = (Calendar.getInstance().get(Calendar.YEAR) + "").substring(2);
//        timearray[1] = calendar.get(Calendar.MONTH) + 1 + "";
//        timearray[2] = calendar.get(Calendar.DAY_OF_MONTH) + "";
//        timearray[3] = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? "7" : (calendar.get(Calendar.DAY_OF_WEEK) - 1 + "");
//        timearray[4] = calendar.get(Calendar.HOUR_OF_DAY) + "";
//        timearray[5] = calendar.get(Calendar.MINUTE) + "";
//        timearray[6] = calendar.get(Calendar.SECOND) + "";
//
//        try {
//            for (int i = 0; i < timearray.length; i++) {
//                if (timearray[i].length() == 1) {
//                    timearray[i] = "0" + timearray[i];
//                }
//                LogUtil.d(tag, "timearray[" + i + "]=" + timearray[i]);
//            }
//            for (int i = 0; i < timebytes.length; i++) {
//                timebytes[i] = ByteUtil.parseTenDescToDescByte(timearray[i]);
//            }
//            LogUtil.d(tag, "time from server:" + time);
//            LogUtil.d(tag, "time to   ble:" + ByteUtil.bytesToHexString(timebytes));
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtil.d(tag, "设备添加成功，同步时间操作时时间转换失败");
//            onResponse(true, englishTip, "", info_);
//            return;
//        }
//
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().registerXIAODIBLEReceiver();
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().setResponseObj(
//                new XIAODIBLEHandler(
//                        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService(),
//                        new XIAODIDataSend().setCmd(new byte[]{0x23}),
//                        new OnXIAODIBLEListener.XIAODIBLEListener() {
//
//                            @Override
//                            public void bleOperateSuccess(XIAODIDataReceived mDataReceived) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "蓝牙同步时间成功");
//                                baseProgressImageDialog.setCurrentProgress(100);
//                                /**
//                                 * 设备添加成功后，从锁上获取固件版本号
//                                 */
//                                getNearestSoftwareVersionFromBLE();
////                                DialogUtil.showAlertAndHide(contentActivity, "添加设备提示", "添加成功", "确认", null, true, 2);
////                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().sendBroadcast(new Intent(BluetoothLeManage.ACTION_AUTO_DISCONNECT));
//                                /**
//                                 * 不需要更新锁上用户数量缓存，暂无当前锁具信息
//                                 */
//                            }
//
//                            @Override
//                            public void bleOperateFailure(String error) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "设备添加成功，蓝牙同步时间失败," + error);
//                                baseProgressImageDialog.setCurrentProgress(100);
//                                onResponse(true, englishTip, "", info_);
////                                DialogUtil.showAlertAndHide(contentActivity, "添加设备提示", "添加成功", "确认", null, true, 2);
////                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().sendBroadcast(new Intent(BluetoothLeManage.ACTION_AUTO_DISCONNECT));
//                                /**
//                                 * 不需要更新锁上用户数量缓存，暂无当前锁具信息
//                                 */
//                            }
//                        }));
//
//        XIAODIBLE.send(
//                false,
//                XIAODIBLEProtocolFactory.BLE_CMDTYPE_SYNCBLELOCKTIME,
//                lockmacString,
//                new XIAODIDataSendCenter().setTime(timebytes),
//                true);
//    }
//
//    private Device buildAddedDevice() {
//        Device device = new Device();
//        device.setOpentype("2");
//        device.setUseropentype("3");
//        device.setMobile(UserManageCache.getmUser().getMobile());
//        device.setDeviceName(locknameString);
//        device.setScanType(Statics.APP_LOCK_UPERCASE);
//        device.setMetertype(metertype);
//        device.setDeviceType(Statics.DEVICE_TYPE_LOCK + "");
//        device.setDeviceMac(lockmacString);
//        device.setChannelPassword(lockchannelpwd);
//        device.setDeviceSeq(locksn);
//        device.setCount(0);
//        device.setIsOffline(online ? "0" : "1");
//        return device;
//    }
//
//    private void deleteUserOnBLE() {
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().registerXIAODIBLEReceiver();
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().setResponseObj(
//                new XIAODIBLEHandler(
//                        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService(),
//                        new XIAODIDataSend().setCmd(new byte[]{0x29}),
//                        new OnXIAODIBLEListener.XIAODIBLEListener() {
//
//                            @Override
//                            public void bleOperateSuccess(XIAODIDataReceived mDataReceived) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "蓝牙上删除用户成功");
//                                onResponse(false, englishTip, "", error_);
//                            }
//
//                            @Override
//                            public void bleOperateFailure(String error) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "蓝牙上删除用户失败," + error);
//                                onResponse(false, englishTip, "", error_);
//                            }
//                        }));
//        XIAODIBLE.send(online, XIAODIBLEProtocolFactory.BLE_CMDTYPE_DELETEBLELOCKMOBILEACCOUNT, lockmacString, new XIAODIDataSendCenter().setMobileaccount(UserManageCache.getmUser().getAccount().getBytes()), true);
//    }
//
//    /**
//     * 从蓝牙获取最新固件版本号
//     */
//    private void getNearestSoftwareVersionFromBLE() {
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().registerXIAODIBLEReceiver();
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().setResponseObj(
//                new XIAODIBLEHandler(
//                        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService(),
//                        new XIAODIDataSend().setCmd(new byte[] { (byte) 0x37 }),
//                        new OnXIAODIBLEListener.XIAODIBLEListener() {
//
//                            @Override
//                            public void bleOperateSuccess(XIAODIDataReceived mDataReceived) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag,"蓝牙获取软件版本号成功，mDataReceived.getDataArea()=" + ByteUtil.bytesToHexString(mDataReceived.getDataArea()));
//                                try {
//                                    // A0.0.001_20160318��
//                                    String lockSoftwareVersionLoad = new String(mDataReceived.getDataArea(),"utf-8");
//                                    LogUtil.d(tag, "mDataReceived.getDataArea()=" + lockSoftwareVersionLoad);
//                                    String lockSoftwareVersionString = lockSoftwareVersionLoad.substring(0, 17);
//                                    LogUtil.d(tag, "lockSoftwareVersionString=" + lockSoftwareVersionString);
//                                    /**
//                                     * 将获取到的固件版本号回写到服务器
//                                     */
//                                    updateLockSoftwareVersion(lockSoftwareVersionString);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    LogUtil.d(tag, "设备添加成功，获取版本号蓝牙返回数据解析失败");
//                                    onResponse(true, englishTip, "", info_);
//                                }
//                            }
//
//                            @Override
//                            public void bleOperateFailure(String error) {
//                                XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().unRegisterXIAODIBLEReceiver();
//                                LogUtil.d(tag, "设备添加成功，获取版本号失败," + error);
//                                onResponse(true, englishTip, "", info_);
//                            }
//                        }));
//        XIAODIBLE.send(
//                false,
//                XIAODIBLEProtocolFactory.BLE_CMDTYPE_GETBLELOCKSOFTWAREVERSION,
//                lockmacString,
//                null,
//                true);
//    }
//
//    /**
//     * 更新锁具软件版本号到服务器
//     * @param lockSoftwareVersionLoad
//     */
//    public void updateLockSoftwareVersion(String lockSoftwareVersionLoad){
//        Map<String,String> postMap=new HashMap<String,String>();
//        postMap.put("content",String.format("%1$s|%2$s", lockmacString, lockSoftwareVersionLoad));
//        NoHttpUtil.getInstance(contentActivity.getApplication()).asyncPostStringEncryptRequest(
//                ServerUrl.getInstance().url_updateLockSoftVersion,
//                ServerUrl.getInstance().url_updateLockSoftVersion_what,
//                postMap,
//                new NoHttpUtil.CommonResponseListener(null) {
//                    @Override
//                    public void requestSuccess(Object dataList,String resultMessage) {
//                        LogUtil.d(tag, "更新锁具软件版本号成功");
//                        onResponse(true, englishTip, "", info_);
//                    }
//
//                    @Override
//                    public void requestFailed(Exception exception, boolean show) {
//                        LogUtil.d(tag, "更新锁具软件版本号失败");
//                        onResponse(true, englishTip, "", info_);
//                    }
//                }
//        );
//
//    }
//
//    /**
//     * 发送命令断开蓝牙连接，在添加设备过程中，只有已连接上蓝牙然后后续操作失败的情况下，才需要该操作
//     */
//    private void disconect(final String message){
//        XiaodiOpenControlShakeService.getXiaodiOpenControlShakeService().requestDisconnectBLE(
//                new XiaodiOpenControlShakeService.OnDisconnectBLEListener() {
//                    @Override
//                    public void disconnectFinish(boolean status, String msg) {
//                        onResponse(false, englishTip, "", message);
//                    }
//                }
//        );
//    }
//}
