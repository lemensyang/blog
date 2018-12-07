package com.airfans.blog.commom.util;

public class StringUtils {
    /**
     * <p>
     * 判断字符串是否为空。
     * </p>
     * 
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isEmptyOrNull(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * <p>
     * 判断对象是否为空字符串。
     * </p>
     * 
     * @param obj 字符串对象
     * @return 判断结果
     */
    public static boolean isEmptyOrNull(Object obj) {
        return obj == null || String.valueOf(obj).trim().length() == 0;
    }
}
