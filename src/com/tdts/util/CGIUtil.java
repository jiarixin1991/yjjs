package com.tdts.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@SuppressWarnings("deprecation")
public class CGIUtil {

	/**
	 * 内容加密/解密
	 * */
	@SuppressWarnings({ "resource" })
	public String cgiEncryption(String url, String param) {
		// get请求
		String result = "";
		JSONObject jsonResult = null;
		if(param == null || "".equals(param.trim())){
			param = "";
		}
		try {
			url += "?STR=" + URLEncoder.encode(URLEncoder.encode(param.trim(), "UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return "";
		}
		HttpClient client = new DefaultHttpClient();
		// 发送get请求
		System.err.println(url);
		HttpGet request = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(request);
			// 请求发送成功，并得到响应 
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据 
				String strResult = EntityUtils.toString(response.getEntity());
				//url = URLDecoder.decode(url, "UTF-8");
				// 把json字符串转换成json对象 
				jsonResult = JSONObject.fromObject(strResult);
				if ("0".equals(jsonResult.getString("result"))) {     //0成功   1失败
					result = URLDecoder.decode(jsonResult.getString("value"), "UTF-8");   //内容URLDecoder解码
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		CGIUtil cgi = new CGIUtil();
		String encryptUrl = "http://localhost:8080/truckPassManager/cgi-bin/EasyStarFile.cgi/EncryStringAction";  //加密URL
		String decryptUrl = "http://localhost:8080/truckPassManager/cgi-bin/EasyStarFile.cgi/DecryStringAction";   //解密URL
		String result = cgi.cgiEncryption(encryptUrl, "15156654646543134651");
		//String result = cgi.cgiEncryption(decryptUrl, "74DF998B7107");
		System.out.println(result);
	}
}
