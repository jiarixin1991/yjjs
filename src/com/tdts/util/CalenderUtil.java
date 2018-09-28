package com.tdts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalenderUtil {

	//生成临时通行证加入数据库
	Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
	int year = c.get(Calendar.YEAR); 
	int month = c.get(Calendar.MONTH); 
	int date = c.get(Calendar.DATE); 
	int hour = c.get(Calendar.HOUR_OF_DAY); 
	int minute = c.get(Calendar.MINUTE); 
	int second = c.get(Calendar.SECOND); 
	SimpleDateFormat sdfDateTime =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
	SimpleDateFormat sdfDate =   new SimpleDateFormat( "yyyy-MM-dd" ); 
	//当前时间
	public String getDatetime() {
		return  year + "-" + month + "-" + date + " " +hour + ":" +minute + ":" + second;
	}
	//当前日期
	public String getDate() {
		return year + "-" + month + "-" + date;
	}
	//几天后的日期
	public String getDateAfter(int day){  
		Calendar now =Calendar.getInstance();  
		try {
			now.setTime(sdfDateTime.parse(getDatetime()));
			now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		} catch (ParseException e) {
		}  
		return sdfDateTime.format(now.getTime());  
	}  
}
