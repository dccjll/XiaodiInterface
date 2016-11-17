package com.dsm.xiaodicoreinterface.base;

import com.dsm.xiaodicoreinterface.util.LogUtil;
import com.dsm.xiaodicoreinterface.util.StringUtil;

import java.util.Map;

/**
 * Created by dccjll on 2016/11/17.
 * 小嘀管家数据简单校验工具
 */

public class CheckUtil {

    public static boolean checkParams(Map<String, Object> data){
        if(data == null){
            LogUtil.e("null data");
            return false;
        }
        if(data.get("contentActivity") == null || data.get("onXiaodiListener") == null){
            LogUtil.e("null contentActivity or onXiaodiListener");
            return false;
        }
        for(Map.Entry<String, Object> entry : data.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if("locknameString".equalsIgnoreCase(key) && StringUtil.isEmpty(value.toString())){
                LogUtil.e("error " + key);
                return false;
            }
            if("lockmacString".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || StringUtil.checkAddress(value.toString()))){
                LogUtil.e("error " + key);
                return false;
            }
            if("metertype".equalsIgnoreCase(key) && (StringUtil.isEmpty(value.toString()) || !value.toString().contains("_"))){
                LogUtil.e("error " + key);
                return false;
            }
        }
        return true;
    }
}
