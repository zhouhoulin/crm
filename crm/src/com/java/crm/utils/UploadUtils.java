package com.java.crm.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 */
public class UploadUtils {
    public static String getUuidFileName(String fileName){
        int index = fileName.lastIndexOf(".");//aa.txt
        String extions = fileName.substring(index);
        return UUID.randomUUID().toString().replace("-","")+extions;

    }
    public static String getPath(String uuidFileName){
        int code1 = uuidFileName.hashCode();
        int dir1 = code1 & 0xf;//作为一级目录 0xf16进制中的15
        int code2 = code1 >>> 4;
        int dir2 = code2 & 0xf;//作为二级目录
        return "/"+dir1+"/"+dir2;
    }
}

