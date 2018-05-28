package com.three.pay.paymentcommon.utils;

/**
 * @Author:luiz
 * @Date: 2018/1/23 11:45
 * @Descripton:
 * @Modify :
 **/
public class IDUtils {
    private static   IDWork idWorker = new IDWork(0, 0);
    public static long nextId(){
        return idWorker.nextId();
    }
    public static String nextIdStr(){
        return String.valueOf(idWorker.nextId());
    }

    public static String genIdStr(String idType){
        return idType+String.valueOf(idWorker.nextId());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            String id = IDUtils.genIdStr("S");
            System.out.println(id);
        }
    }
}
