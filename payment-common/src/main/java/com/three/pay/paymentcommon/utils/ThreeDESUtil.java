package com.three.pay.paymentcommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @Author:luiz
 * @Date: 2018/4/10 17:45
 * @Descripton:3Des加密
 * @Modify :
 **/
public class ThreeDESUtil {
    private static final Logger logger= LoggerFactory.getLogger(ThreeDESUtil.class);
    //key 根据实际情况对应的修改
    private static final byte[] keybyte="123456788765432112345678".getBytes(); //keybyte为加密密钥，长度为24字节
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
    private static SecretKey deskey=new SecretKeySpec(keybyte, Algorithm);
    //生成密钥
    public ThreeDESUtil(){
        deskey = new SecretKeySpec(keybyte, Algorithm);
    }

    //加密 十六进制显示
    public static String encryptToHex(String msgStr){
        byte[] srcData= new byte[0];
        try {
            srcData = msgStr.toString().getBytes("utf-8");
            return bytetoHexStr(encrypt(srcData));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密 将十六进制解密
    public static String decryptHex(String encrptHex){
        byte[] srcData=decrypt(hexStringToByte(encrptHex));
        try {
           return new String(srcData,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    //加密
    public static byte[] encrypt(byte[] data){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //加密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }
    //解密
    public static byte[] decrypt(byte[] data){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,deskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //解密失败，打日志
            ex.printStackTrace();
        }
        return null;
    }

    //加密 十六进制显示
    public static String encryptToHex(String msgStr,String threeDesKey){
        byte[] srcData= new byte[0];
        try {
            srcData = msgStr.toString().getBytes("utf-8");
            return bytetoHexStr(encrypt(srcData,threeDesKey));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密 将十六进制解密
    public static String decryptHex(String encrptHex,String threeDesKey){
        byte[] srcData=decrypt(hexStringToByte(encrptHex),threeDesKey);
        try {
            return new String(srcData,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    //加密
    public static byte[] encrypt(byte[] data,String threeDesKey){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            SecretKey threedeskey=new SecretKeySpec( threeDesKey.getBytes(), Algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,threedeskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            logger.error("[ThreeDESUtil]对数据进行加密发生异常:{}",ex);
        }
        return null;
    }



    //解密
    public static byte[] decrypt(byte[] data,String threeDesKey){
        try {
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            SecretKey threedeskey=new SecretKeySpec( threeDesKey.getBytes(), Algorithm);
            cipher.init(Cipher.DECRYPT_MODE,threedeskey);
            return cipher.doFinal(data);
        } catch (Exception ex) {
            //解密失败，打日志
            logger.error("[ThreeDESUtil]对数据进行解密发生异常:{}",ex);
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        String req ="cryptology";
        String encryptStr=ThreeDESUtil.encryptToHex(req);
        System.out.println("密文：");
        System.out.println(encryptStr);
        String descData= ThreeDESUtil.decryptHex(encryptStr);
        System.out.println("明文："+descData);
    }

    public static String bytetoHexStr(byte[] encryptData){
        StringBuffer stb=new StringBuffer();
        if(encryptData!=null){
            for(int i=0;i<encryptData.length;i++){
                String hex=Integer.toHexString(encryptData[i]);
                if(hex.length()>1){
                    stb.append(hex.substring(hex.length()-2));
                }
                else{
                    stb.append("0"+hex);
                }

            }
        }
        return stb.toString();
    }

    // 转化字符串为十六进制编码
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    public static byte[] hexStringToByte(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return baKeyword;
    }
}
