package com.tdts.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ClassCallUtil {
	/**
	 * 
	 * @param request
	 * @param response
	 * @param classPath  要调用的文件的地址（http://127.0.0.1:80/ReseverRoom/TestServlrt）
	 * @param msg 要发送的信息（username=123&password=456多参数）
	 */
	public int sendInfoToOtherClass(HttpServletRequest request, HttpServletResponse response, String classPath, String msg){
		try {
			//设置请求的编码方式  
			request.setCharacterEncoding("utf-8");
			//设置返回时的编码方式  
			response.setContentType("text/html;charset=utf-8");  
			//设置HTTP连接的URL地址，就是第二个应用的URL。如果不在同一个计算机上可以将127.0.0.1变成相应的IP地址
			String http=classPath;  
			URL url=new URL(http);  
			//生成HttpURLConnection连接  
			HttpURLConnection httpurlconnection=(HttpURLConnection) url.openConnection();  
			//httpurlconnection.
			//设置有输出流，默认为false，就是不能传递参数。  
			httpurlconnection.setDoOutput(true);  
			//设置发送请求的方式。注意：一定要大写  
			httpurlconnection.setRequestMethod("POST");  
			//设置连接超时的时间。不过不设置，在网络异常的情况下，可能会造成程序僵死而无法继续向下执行，所以一般设置一个超时时间。单位为毫秒  
			httpurlconnection.setConnectTimeout(30000);  
			//设置输出流。  
			OutputStreamWriter writer=new OutputStreamWriter(httpurlconnection.getOutputStream(), "utf-8");  
			//传递的参数，中间使用&符号分割。  
			writer.write(msg);  
			//用于刷新缓冲流。因为默认她会写入到内存的缓冲流中，到一定的数据量时，才会写入，使用这个命令可以让他立即写入，不然下面就到关闭流了  
			writer.flush();  
			//用于关闭输出流，关闭之后就不可以输出数据了，所以要使用flush刷新缓冲流  
			writer.close();  
			//获得返回的请求吗。  
			int responseCode=httpurlconnection.getResponseCode();  
			//表示请求成功  
			if(responseCode==HttpURLConnection.HTTP_OK){  
	        	System.out.println("OK"+responseCode);  
	        	//获得服务端的输出流。得到返回的数据  
	        	InputStream urlstream=httpurlconnection.getInputStream();  
	        	BufferedReader reader=new BufferedReader(new InputStreamReader(urlstream));  
	        	String line;  
	        	String tline="";  
	        	while((line=reader.readLine())!=null){  
	            	tline+=line;  
	        	}  
	        	return 0; 
	       }else{  
	           	System.out.println("ERR"+responseCode);  
	           	return 1; 
	       }  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return 1;
	}
}
