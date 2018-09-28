package com.tdts.Interceptor;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 如果要用struts2 方式上传的话就需要重写里做出一些改变增减判断action 如果这个
 * action上传是按照struts2方式的那么需要Struts2处理
 */

/*
 * String url = request.getRequestURI().toString();//取得请求的URL
 * if(url.indexOf("uploadWebApp.action")>0){//调用的是uploadWebApp.action方法
 * //不需要struts2的处理 }else{ //需要struts2的处理,调用回父类的方法 super.parse(request, arg1); }
 */

public class RequestParseWrapper extends JakartaMultiPartRequest {

	@Override
	public void parse(HttpServletRequest servletRequest, String saveDir)
			throws IOException {
		// 什么也不做---
	}
}
