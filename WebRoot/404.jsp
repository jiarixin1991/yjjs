<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<HTML>
<HEAD>
<TITLE>404错误页面</TITLE>
<!--STATUS OK-->
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT>
	window.wpo = {
		start : new Date * 1,
		pid : 109,
		page : 'qing'
	}
</SCRIPT>
<!--[if IE]>
<SCRIPT type=text/javascript>(function(){var a="abbr,article,aside,audio,canvas,datalist,details,dialog,eventsource,figure,footer,header,hgroup,mark,menu,meter,nav,output,progress,section,time,video".split(","),b=a.length;while(b--)document.createElement(a[b])})();</SCRIPT>
<![endif]-->
<!--[if (lt IE 8.0)]><LINK href="<%=basePath%>404/qbase.css" type=text/css 
rel=stylesheet><![endif]-->
<!--[if (!IE)|(gte IE 8.0)]><!-->
<link href="<%=basePath%>404/qbase_datauri.css" type=text/css rel=stylesheet>
<!--<![endif]-->

<STYLE type=text/css>
.mod-notfound {
	BORDER-RIGHT: #e1e1e1 1px solid;
	BORDER-TOP: #e1e1e1 1px solid;
	MARGIN-TOP: 10px;
	BACKGROUND: #fff;
	BORDER-LEFT: #e1e1e1 1px solid;
	BORDER-BOTTOM: #e1e1e1 1px solid;
	HEIGHT: 585px;
	TEXT-ALIGN: center;
	border-radius: 10px
}
</STYLE>

<META content="MSHTML 6.00.6000.17117" name=GENERATOR>
</HEAD>
<BODY>
	<SECTION class=mod-page-body>
	<DIV class="mod-page-main wordwrap clearfix">
		<DIV class=x-page-container>
			<DIV class="mod-notfound grid-98">
				<IMG class=img-notfound height=320 src="<%=basePath%>404/notfound.gif" width=520>

				<P style="FONT-SIZE: 24px; LINE-HEIGHT: 70px">啊~哦~ 您要查看的页面或文件不存在或已删除！</P>
				<P style="MARGIN-BOTTOM: 30px">请检查您输入的网址是否正确，或者点击链接继续浏览空间</P>
				<P style="FONT-SIZE: 14px; LINE-HEIGHT: 20px">
					您可以回到
					<A href="<%=basePath%>homePageAction.action">网站首页</A>
					或
					<A href="javascript:void(0);" onClick="history.go(-1);">返回</A>
			</DIV>
		</DIV>
	</DIV>
	</SECTION>
	<FOOTER class="mod-footer mod-cs-footer">
	<DIV class="clearfix hidden-box"></DIV>
	<DIV class=footer-box>
		<DIV class=copy-box>©2018&nbsp;中新生态城货车通行证审批备案系统</DIV>
	</DIV>
	</FOOTER>

</BODY>
</HTML>

