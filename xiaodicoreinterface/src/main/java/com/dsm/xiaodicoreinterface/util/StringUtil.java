package com.dsm.xiaodicoreinterface.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

/**
 * Created by dessmann on 16/7/11.
 * 字符串通用工具类
 */
public class StringUtil {
    public static boolean isNotEmpty(String str) {
        return (str != null) && (!"null".equalsIgnoreCase(str)) && (str.length() > 0);
    }

    public static boolean isEmpty(String str) {
        return (str == null) || ("null".equalsIgnoreCase(str)) || (str.length() == 0);
    }

    /**
     * 新建一个可以添加属性的文本对象
     * @param text
     * @param size
     * @return
     */
    public static SpannableString buildSpannableString(String text, int size){
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(text);

        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size,true);

        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }

    /**
     * 长字符串转换
     * @param str
     * @param maxLength
     * @return
     */
    public static String changeLongStr(String str, int maxLength){
        if(str.length()>maxLength&&str.length()>2){
            str = str.substring(0,maxLength-2)+"..."+str.substring(str.length()-1, str.length());
        }
        return str;
    }

    /**
     * 将一个用":"连接的字符串以":"为分隔反转
     * @param st
     * @param toUpperCase
     * @return
     */
    public static String reserveString(String st, boolean toUpperCase) {
        String[] starr = st.split(":");
        StringBuilder sBuffer = new StringBuilder();
        for (int i = starr.length - 1; i >= 0; i--) {
            sBuffer.append(starr[i] + ":");
        }
        String reserveString = sBuffer.toString().substring(0, sBuffer.toString().lastIndexOf(":"));
        if(toUpperCase){
            reserveString = reserveString.toUpperCase(Locale.US);
        }
        return reserveString;
    }

    /**
     * 验证设备mac地址
     * @param address
     * @return
     */
    public static boolean checkAddress(String address){
        if(isEmpty(address)){
            return false;
        }
        if(address.split(":").length != 6){
            return false;
        }
        char[] macChars = address.replace(":", "").toCharArray();
        String regexChars = "0123456789ABCDEF";
        for(char c : macChars){
            if(!regexChars.contains(c + "")){
                return false;
            }
        }
        return true;
    }

    /**
     * 验证设备mac地址列表
     * @param targetAddressList 目标设备mac地址列表
     * @return
     */
    public static boolean checkTargetAddressList(List<String> targetAddressList){
        if(targetAddressList == null || targetAddressList.size() == 0){
            return false;
        }
        for(String mac : targetAddressList){
            if(!checkAddress(mac)){
                return false;
            }
        }
        return true;
    }

    /**
     * 构建通用网络响应数据包
     * @param status    状态
     * @param data  数据
     * @param msg   消息描述
     * @return  数据包
     */
    public static String buildData(boolean status, Object data, String msg){
        JSONObject jsonObject = new JSONObject();
        try {
            if(status){
                jsonObject.put("status", 1);
            }else{
                jsonObject.put("status", 0);
            }
            jsonObject.put("data", data);
            jsonObject.put("msg", msg);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject.toString();
    }

}
