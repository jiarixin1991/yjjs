package com.tdts.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @program: yjjs
 * @author: JRX
 * @create: 2018-08-17 09:39
 **/
public class test {
    public static String getOrderIdByUUId() {
        int first = new Random(10).nextInt(8) + 1;
        System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }
    public static String getAccountIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


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

        public static void main(String[] args) {
            Map<String, Object> a = new HashMap<>();
            int num = 0;
            long startTime = System.currentTimeMillis();    //获取开始时间
            for (int i = 0; i <100000; i++) {
                String orderingID= generateShortUuid();
                for (String s : a.keySet()) {
                    String s1 = a.get(s).toString();
                    if(s1.equals(orderingID)){
                        System.err.println(s1.equals(orderingID));
                        System.err.println("重复");
                        System.err.println(orderingID);
                        num++;
                    }else{
                    }
                }
                //System.out.println(orderingID);
                String key = orderingID + i;
                a.put(key, orderingID);
            }
            System.err.println(num);
            long endTime = System.currentTimeMillis();    //获取结束时间
            System.err.println(endTime - startTime);
            System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "ms");    //输出程序运行时间
            System.out.println("程序运行时间：" + (endTime - startTime)/60000 + "ms");    //输出程序运行时间


    }
}
