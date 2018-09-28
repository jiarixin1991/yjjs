package com.tdts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	/**
	 * 获取一个文件的md5值(可处理大文件)
	 * @return md5 value
	 */
	public static String getMD5(File file) {
		FileInputStream fileInputStream = null;
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024 * 10];
			int length;
			while ((length = fileInputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(MD5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	/**
	 * 求一个字符串的md5值
	 * @param target 字符串
	 * @return md5 value
	 */
	public static String MD5(String target) {
		return DigestUtils.md5Hex(target);
	}
	
	
	
 
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		File file = new File("F:/Download/test.rar");    //1G  3.8s
		String md5 = getMD5(file);
		long endTime = System.currentTimeMillis();
		System.out.println("MD5:" + md5 + "\n 耗时:" + (endTime - beginTime) + "ms");
		
		String str = "测试md5";
		String md5Str = MD5(str);
		System.out.println(md5Str);
	}
}
