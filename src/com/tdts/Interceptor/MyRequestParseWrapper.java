package com.tdts.Interceptor;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyRequestParseWrapper extends JakartaMultiPartRequest {
 
 @Override
 public void parse(HttpServletRequest servletRequest, String saveDir)
   throws IOException {
  // 什么也不做---
 }
}