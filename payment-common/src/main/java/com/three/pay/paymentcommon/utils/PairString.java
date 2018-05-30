package com.three.pay.paymentcommon.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author:luiz
 * @Date: 2018/3/19 17:45
 * @Descripton:
 * @Modify :
 **/
public class PairString {
    /**
     * 将key=value这种形式的字符串，封装成Map返回
     * @param respContent
     * @return
     */
    public static Map<String,String> convertRespToMap(String respContent){
        String[] respArray=respContent.split("&");
        Map<String,String> retMap=new HashMap<String,String>();
        for(int i=0;i<=respArray.length-1;i++){
            String respStr=respArray[i];
            int fistIndex=respStr.indexOf("=");
            String startStr=respStr.substring(0,fistIndex);
            String endStr=respStr.substring(fistIndex+1);
            retMap.put(startStr,endStr);
        }
        return retMap;
    }

    /**
     * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String,String> params){
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if(params.get(key)==null) continue;

            String value = params.get(key);

            if ("".equals(prestr)) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + "&"+key + "=" + value;
            }
        }
        return prestr;
    }

    public static String createLinkString(JSONObject jsonParams){
        String prestr = "";
        for (Map.Entry<String, Object> entry : jsonParams.entrySet()) {
            if(entry.getValue()==null) continue;
            String value = (String) entry.getValue();
            String key=entry.getKey();
            if ("".equals(prestr)) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + "&"+key + "=" + value;
            }
        }
        return prestr;
    }

    /**
     * 功能：除去数组中的空值
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map filterNullParam(Map<String,String> sArray){
        List keys = new ArrayList(sArray.keySet());
        Map sArrayNew = new HashMap();
        for(int i = 0; i < keys.size(); i++){
            String key = (String) keys.get(i);
            if(StringUtils.isBlank(key)) continue;

            String value = sArray.get(key);
            if(StringUtils.isBlank(value)) continue;

            sArrayNew.put(key, value);
        }
        return sArrayNew;
    }

    /**
     * 功能：把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串(参数值是经过encode编码的)
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkStringForEncode(Map params)  {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        StringBuilder stb=new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if(params.get(key)==null) continue;

            String value = (String) params.get(key);

            if(i>=1){
                stb.append("&");
            }
            try {
                stb.append(key).append("=").append(URLEncoder.encode(value,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return stb.toString();
    }



    /**
     * 功能：除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private static Map FilterParam(Map sArray){
        List keys = new ArrayList(sArray.keySet());
        Map sArrayNew = new HashMap();
        for(int i = 0; i < keys.size(); i++){
            String key = (String) keys.get(i);
            String value = (String) sArray.get(key);
            if(value.equals("") || value == null || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")){
                continue;
            }

            sArrayNew.put(key, value);
        }

        return sArrayNew;
    }
}
