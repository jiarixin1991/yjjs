<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件挂接</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>css/hangUp.css">
    <link rel="stylesheet" href="<%=basePath%>iconfont/iconfont.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>iconfont/iconfont.js"></script>
    <script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/hangUp.js"></script>
  </head>
  
  <body>
    <div class="main_bg">
        <div class="isHanged">
            <div class="hangedTitle">
                <span>已挂接文件名：</span>
            </div>
            <ul class="hangedFiles">
                <!-- <li>
                    <span class="hangedIcon1"></span>
                    <span class="fileName" title="原件名：***********************">原件名：***********************</span>
                    <span class="hangedIcon2">
                        <i class="iconfont icon-ai67"></i>
                    </span>
                </li> -->

            </ul>
        </div>
        
        <div class="operate_area">
            <div class="search_div">    <!--搜索框-->
                <input class="layui-input search_text" type="text">
                <input class="search_btn" type="button" value="搜索">
            </div>

            <div class="search_result">   <!--待选区-->
                <!-- <div class="files">
                    <span class="check_file">
                        <i class="iconfont icon-sousuo"></i>
                    </span>
                    <div class="hangFile">
                        <span class="files_title">
                            文件名：
                        </span>
                        <span class="files_name" title="12345678901234560">
                            12345678901234560
                        </span>
                    </div>
                </div> -->

            </div>
            <div class="pageDiv" id="pages">

            </div>
        </div>
        <div class="footer">
            <span class="footInfo">已挂接文件**个</span>
        </div>
    </div>
</body>
</html>
