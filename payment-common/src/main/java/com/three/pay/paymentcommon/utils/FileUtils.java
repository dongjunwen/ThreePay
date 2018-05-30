package com.three.pay.paymentcommon.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author:luiz
 * @Date: 2018/4/13 13:59
 * @Descripton:
 * @Modify :
 **/
public class FileUtils {
    /**
     * 存储到指定目录
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static byte[] readFile(String savePath, String saveFileName) throws IOException {
        byte[] resultData=null;
        FileInputStream inputStream=new FileInputStream(new File(savePath+saveFileName));
        resultData=IOUtils.toByteArray(inputStream);
        return resultData;
    }



}
