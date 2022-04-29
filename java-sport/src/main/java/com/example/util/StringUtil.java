package com.example.util;

/**
 * 字符串判断
 * @author chuan
 *
 */
public class StringUtil {

    public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str);
    }
}
