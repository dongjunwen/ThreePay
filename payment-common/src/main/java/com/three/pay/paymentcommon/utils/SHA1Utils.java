package com.three.pay.paymentcommon.utils;

import java.security.MessageDigest;

/**
 * @Author:luiz
 * @Date: 2018/3/19 17:31
 * @Descripton:SHA1加密算法
 * @Modify :
 **/
public class SHA1Utils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
       /* for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }*/

        for (int i = 0; i < bytes.length; i++) {
            if (Integer.toHexString(0xFF & bytes[i]).length() == 1) {
                buf.append("0").append(Integer.toHexString(0xFF & bytes[i]));
            } else {
                buf.append(Integer.toHexString(0xFF & bytes[i]));
            }
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String str="certId=68759529225&issCode=90880020&qrCode=444444444&reqType=0120000903&version=1.0.0";
        str="certId=68759529225&issCode=90880020&qrCode=444444444&reqType=0120000903&version=1.0.0";
        System.err.println(SHA1Utils.encode(str));
    }
}
