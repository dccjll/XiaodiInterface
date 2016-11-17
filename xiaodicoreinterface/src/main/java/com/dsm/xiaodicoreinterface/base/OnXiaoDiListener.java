package com.dsm.xiaodicoreinterface.base;

import com.dsm.xiaodicoreinterface.util.LogUtil;

/**
 * Created by dccjll on 2016/11/17.
 * 小嘀管家抽象响应类
 */

public abstract class OnXiaodiListener {

    private final static String TAG = OnXiaodiListener.class.getSimpleName();

    /**
     * 操作进度
     * @param tag   总体操作描述
     * @param msg   分操作操作结果描述
     * @param progress  总体操作进度描述
     */
    public void onProgress(String tag, String msg, Integer progress){
        LogUtil.i(TAG, "tag=" + tag + "\nmsg=" + msg + "\nprogress=" + progress);
    }

    public void onFinish(String tag, boolean status, Object data){
        LogUtil.i(TAG, "tag=" + tag + "\nstatus=" + (status ? "success" : "failure") + "\ndata=" + data.toString());
        if(status){
            onSuccess(data);
        }else{
            onFailure(data);
        }
    }

    abstract  void onSuccess(Object data);

    abstract  void onFailure(Object data);
}
