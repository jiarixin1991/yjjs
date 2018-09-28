package com.tdts.util;

import java.util.UUID;

/**
 * 字符串工具类
 * @author jrx
 * @date 2018-5-14
 * <pre>
 *  desc:创建
 * </pre>
 */
public class StrUtil {
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 8位UUID
     * @return
     */
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * UUID 生成
     */
    public static String getUUID() {
        // 去掉"-"符号
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }


    /**
     * 字符串工具
     */
    public static String toString(Object obj){
        return (obj == null ? "" : obj.toString());
    }

    /**
     * 给第字符串第一个字母大写
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        return new StringBuilder(strLen)
                .append(Character.toTitleCase(str.charAt(0)))
                .append(str.substring(1))
                .toString();
    }

    /**
     * 使用StringBuilder拼接字符串
     * @param objects
     * @return
     */
    public static String appendSbl(Object... objects) {
        StringBuilder sbl = new StringBuilder();
        for (Object obj : objects) {
            sbl.append(obj);
        }
        return sbl.toString();
    }

    /**
     * 使用StringBuffer拼接字符串
     * @param objects
     * @return
     */
    public static String appendSbf(Object... objects) {
        StringBuffer sbl = new StringBuffer();
        for (Object obj : objects) {
            sbl.append(obj);
        }
        return sbl.toString();
    }

    /**
     * 根据字符串，获取后缀
     * @param str
     *      若获取不到，返回 null
     */
    public static String getSuffix(String str) {
        if(null != str && str.lastIndexOf(".") > 0) {
            str = str.substring(str.lastIndexOf("."), str.length());
        } else {
            str = null;
        }
        return str;
    }

    public static void syse(Object value) {
        System.err.println("输出:"+value);
    }
}
