<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
  	<link rel="stylesheet" href="<%=basePath%>css/mainPage.css">
  	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/aliconfont.css">
  	<link rel="stylesheet" href="<%=basePath%>css/common/layuiUpdate.css">
  	<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
  	<script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
  	<script type="text/javascript" src="<%=basePath%>js/common/project_tools.js"></script>
  <script>
    $(function () {
      $(".moduleHover1").hover(function () {
        $(this).addClass("hoverStyle1");
        $(this).find(".module_name").css('display', 'none');
        $(this).find(".hoverInfo").css('display', 'block');
        $(this).find(".al-bianji").css('color', 'white');
      }, function () {
        $(this).removeClass("hoverStyle1");
        $(this).find(".module_name").css('display', 'block');
        $(this).find(".hoverInfo").css('display', 'none');
        $(this).find(".al-bianji").css('color', '#2293fd');
      });

      $(".moduleHover2").hover(function () {
        $(".moduleHover2").addClass("hover_border");
      }, function () {
        $(".moduleHover2").removeClass("hover_border");
      });
      
      $('.right-secretWord').on('click', function () {
        $.fn.TipsWindown.openWin('secretWord2.html', '涉密', '1000px', '600px');

      });
      
      $(".right-secretWord").hover(function () {
        $(".right-secretWord").css("color",'#2293fd');
      }, function () {
        $(".right-secretWord").css("color", 'black');
      });
    });

	$.ajax({
      	type: "post",
      	url: "struts2/getSystemAction.action",
      	data: {},
      	dataType: 'json',
      	success: function(data, textStatus){
      		data = JSON.parse(data);
      		console.log(data.result);
      	}
    });
	
  </script>

  </head>
  
  <body>
<div class="layui-form">
  <div class="right-secretWord" style="position: absolute; right: 50px ; top: 50px; color: black; z-index: 9999; cursor: pointer;">
    <i class="aliconfont">&#xe61e;</i> 涉密设置
  </div>
  <div class="mainBgDiv">

    <!--<div class="mainTitle">
        <img src="img/logo_white.png" class="mainLogo">
        <div class="logout">
            <i class="layui-icon">&#xe623;</i><span>退出登录</span>
        </div>
    </div>-->

    <div class="mainBody">
      <div class="search_div">
        <div class="layui-input-inline newInput">
          <input type="text" name="username" lay-verify="required" placeholder="请输入系统名" autocomplete="off"
                 class="layui-input searchInput">
        </div>
        <div class="searchIcon">
          <i class="layui-icon">&#xe615;</i>
        </div>
      </div>
      <div class="main_module">
        <div class=" module module_big moduleHover1">
          <span class="module_name">天深科技档案系统A00</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'><a href="homePage2.html">进入系统</a></span>
            <div class="show_data">
              <span class="data_text"><a href="details.html">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_small moduleHover1">
          <span class="module_name">档案系统A01</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'>进入系统</span>
            <div class="show_data">
              <span class="data_text"><a href="javascript:;">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_small moduleHover1">
          <span class="module_name">档案系统A02</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'>进入系统</span>
            <div class="show_data">
              <span class="data_text"><a href="javascript:;">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_small moduleHover1">
          <span class="module_name">档案系统A03</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'>进入系统</span>
            <div class="show_data">
              <span class="data_text"><a href="javascript:;">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_small moduleHover1">
          <span class="module_name">档案系统A04</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'>进入系统</span>
            <div class="show_data">
              <span class="data_text"><a href="javascript:;">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_small moduleHover1">
          <span class="module_name">档案系统A05</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'>进入系统</span>
            <div class="show_data">
              <span class="data_text"><a href="javascript:;">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_small moduleHover1">
          <span class="module_name">档案系统A06</span>
          <i class="aliconfont al-bianji" title="编辑系统模板">&#xe61b;</i>
          <div class="hoverInfo">
            <span class='hoverSpan'>进入系统</span>
            <div class="show_data">
              <span class="data_text"><a href="javascript:;">查看大数据</a></span>
            </div>
          </div>
        </div>
        <div class=" module module_big moduleHover2" id="sysCreate" style="cursor: pointer;">
          <span class="module_name2">自定义添加系统</span>
        </div>
      </div>

      <div class="footDiv">
        <div class="footer1">
          <span>相关链接</span>
          <span>联系我们</span>
          <span>法律声明</span>
        </div>
        <div class="footer2">业务电话：022-23042881&nbsp;&nbsp;022-23042883&nbsp;&nbsp;&nbsp;Copyright &copy; 2015 &nbsp;&nbsp;网站建设/全程设计：&nbsp;天津天深科技有限公司</div>
      </div>
    </div>

  </div>
</div>
<script>
  layui.use(['element', 'layer'], function () {
    var element = layui.element;

  });
  $(function () {
    $("#sysCreate").on("click", function () {
      $.fn.TipsWindown.openWin("sysTempNav.html", "创建系统", "1000px", "600px");

    });

    $(document).on("click", ".fanhui", function () {
      var id = $('.layui-layer-iframe').attr("id");
      console.log(id);
      id = id.replace("layui-layer", "");

      document.getElementById("layui-layer-iframe" + id).contentWindow.go();
      this.remove();
    });
    $(document).on("click", ".al-bianji", function () {
      $.fn.TipsWindown.openWin("sysTempNav.html", "编辑模板", "1000px", "600px");
    });

  });
</script>
</body>
</html>
