<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'doc_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath %>layui/css/layui.css"/>
	<link rel="stylesheet" href="<%=basePath %>css/aliconfont.css"/>
	<link href="<%=basePath %>css/common/common.css" rel="stylesheet">
	<link href="<%=basePath %>css/common/searchDiv.css" rel="stylesheet">
	<link href="<%=basePath %>css/nav_search.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath %>js/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/common/echarts.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/common/project_tools.js"></script>

	<script type="text/javascript">
    data = [{
      "id": 10000,
      "USERNAME": "user-0",
      "NAME": "女",
      "TEL": "城市-0",
      "USERPW": "签名-0",
      "experience": 255,
      "logins": 24,
      "wealth": 82830700,
      "classify": "作家",
      "score": 1
    }, {
      "id": 10001,
      "USERNAME": "user-1",
      "NAME": "男",
      "TEL": "城市-1",
      "USERPW": "签名-1",
      "experience": 884,
      "logins": 58,
      "wealth": 64928690,
      "classify": "词人",
      "score": 0
    }, {
      "id": 10002,
      "USERNAME": "user-2",
      "NAME": "女",
      "TEL": "城市-2",
      "USERPW": "签名-2",
      "experience": 650,
      "logins": 77,
      "wealth": 6298078,
      "classify": "酱油",
      "score": 1
    }, {
      "id": 10003,
      "USERNAME": "user-3",
      "NAME": "女",
      "TEL": "城市-3",
      "USERPW": "签名-3",
      "experience": 362,
      "logins": 157,
      "wealth": 37117017,
      "classify": "诗人",
      "score": 0
    }];
    var cols = [
      {
        field: 'USERNAME',
        title: '字段名',
        sort: true
      }, {
        field: 'USERPW',
        title: '类型',
        sort: true
      }, {
        field: 'NAME',
        title: '中文名',
        sort: true,
        templet: function (d) {
          if (d.NAME == null) {
            return "暂无数据";
          } else if (d.NAME == "") {
            return "暂无数据";
          } else {
            return d.NAME;
          }
        }
      }, {
        field: 'TEL',
        title: '长度',
        sort: true,
        templet: function (d) {
          if (d.TEL == null) {
            return "暂无数据";
          } else if (d.TEL == "") {
            return "暂无数据";
          } else {
            return d.TEL;
          }
        }
      }, {
        field: 'USERPW',
        title: '是否涉密',
        sort: true
      }, {
        field: 'USERPW',
        title: '是否查重',
        sort: true
      }, {
        field: 'USERPW',
        title: '必填',
        sort: true
      }, {

        title: '操作',
        toolbar: "#barDemo",
        fixed: 'right',
        align: 'center',
        width: 170
      }];
    layui.use(['laypage', 'layer', 'table', 'element'], function () {
      var table = layui.table;
      var element = layui.element;
      table.render({
        elem: '#LAY_table_user',
        even: true,//开启隔行背景
        cols: [cols],
        data: data,
        initSort: {
          field: 'COMPANYNAME' //排序字段，对应 cols 设定的各字段名
          ,
          type: 'ASC' //排序方式  asc: 升序、desc: 降序、null: 默认排序
        },
        url: '',
        where: {
          radom: Math.random(),
          sort: "USERNAME",
          sortType: "ASC",
          method: "minjing"
        },
        method: 'post',
        id: 'testReload',
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
          //自定义分页布局
          layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
          //,curr: 5 //设定初始在第 5 页
          , prev: '上一页'
          , next: '下一页'
          , first: '首页' //不显示首页
          , last: '尾页' //不显示尾页
          , theme: '#1E9FFF'
        },
        limit: 10,
        limits: [1, 5, 10, 20, 50],
        done: function (res, curr, count) {
          //如果是异步请求数据方式，res即为你接口返回的信息。
          //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
          // console.log("data数据");
          console.log(res);

          if (res == -1) {
            $(".layui-none").html("");
            $(".layui-none").append("暂无数据!!!!!").css("color", "black");
            // $(".layui-table-page").hide();
          }
          var PROP = '<s:property value="#session.user[0].prop"/> ';
          if (PROP == 0) {

          } else {
            // $(".del-danger").addClass("layui-btn-disabled");
            // $(".del-danger").removeAttr("lay-event");
          }
          //得到当前页码
          // console.log("当前页码：" + curr);

          //得到数据总量
          // console.log("总条数：" + count);
        }
      });
      //监听多选框
      table.on('checkbox(tabledemo)', function (obj) {
        console.log(obj.checked); //当前是否选中状态
        console.log(obj); //选中行的相关数据
        console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
      });
      var $ = layui.$, active = {
        getCheckData: function () { //获取选中数据

          var checkStatus = table
            .checkStatus('testReload'), data = checkStatus.data;
          layer.alert(JSON.stringify(data));
        },
        getCheckLength: function () { //获取选中数目
          var checkStatus = table
            .checkStatus('testReload'), data = checkStatus.data;
          layer.msg('选中了：' + data.length + ' 个');
        },
        isAll: function () { //验证是否全选
          var checkStatus = table
            .checkStatus('testReload');
          layer.msg(checkStatus.isAll ? '全选' : '未全选')
        }
      };
      $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        console.log("type:" + type);
        console.log("active:" + active[type]);
        active[type] ? active[type].call(this) : '';
      });
      //排序
      table.on('sort(tabledemo)', function (obj) {
        table.reload('testReload', {
          page: {
            curr: 1
          },
          initSort: obj
          , where: {
            sort: obj.field,//排序字段
            sortType: obj.type,//排序方式
          }

        });
      });

      //监听工具栏
      table.on('tool(tabledemo)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        var data = obj.data;

        if (obj.event === 'detail') {
          layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
          /*layer.alert('编辑行：<br>' + JSON.stringify(data));*/
          var PROP = '<s:property value="#session.user[0].prop"/> ';
          if (PROP == 0) {
            layer.confirm(
              '<p style="color: red; text-align: center" >确定删除该吗？</p><br/>' +
              '<p style="color: red;text-align: center"">用户一旦删除所有信息全部删除!!!</p>',
              {
                icon: 0,
                title: '<span style="color: red">警告</span>',
              }, function (index) {
                var url = "";
                $.ajax({
                  url: url,
                  type: "post",
                  datatype: "json",
                  data: {
                    userId: data.USERID,
                    random: Math.random(),
                  },
                  success: function (data) {
                    var json = JSON.parse(data);
                    if (json.result == "0") {
                      obj.del();
                      table.reload('testReload', {
                        where: { //设定异步数据接口的额外参数，任意设

                        }
                        , page: {
                          curr: 1 //重新从第 1 页开始
                        }
                      });
                    } else {
                      layer.msg('删除失败！！！', {
                        offset: 't',
                        icon: 5
                      });
                      角色
                    }

                  },
                  error: function (textStatus) {//请求失败后调用的函数
                    layer.close(load);
                    alert("error");
                  }


                });
                layer.close(index);
              });
          } else {
            layer.msg('只有超管才能此操作！！！', {
              offset: 't',
              icon: 5
            });
          }

        } else if (obj.event === 'edit') {
          $.fn.TipsWindown.openWin('skip_pageAction.action?method=modiPolice&userId=' + data.USERID, "编辑信息", "850px", "400px");
        }
      });
      $("#search").click(function () {
        var username = $("#username").val();
        table.reload('testReload', {      //上边表格中id
          page: {
            curr: 1 //重新从第 1 页开始
          }
          , where: {
            name: username,
          }
        });
      });
      $("#reset").click(function () {
        var username = "";
        $("#username").val("");
        table.reload('testReload', {      //上边表格中id
          page: {
            curr: 1 //重新从第 1 页开始
          }
          , where: {
            name: username,
          }
        });
      });
    });
  </script>
  <script type="text/html" id="barDemo">
    {{#  if(d.score  == 1){ }}
    <a class="add-btn" href="javascript:;"><i class="aliconfont al-color-blue al-size-28 " style="cursor: pointer;">&#xeac3;</i></a>
    {{#  } }}

    {{#  if(d.score  == 0){ }}
    <a class="add-btn2"><i class="aliconfont al-color-blue  " style="cursor: pointer;">&#xeac3;</i></a>
      {{#  } }}

  </script>
  <script type="text/javascript">
    $(function () {
      $(document).on("click", ".add-btn", function (e) {
        $.fn.TipsWindown.openWin("hangUp.html", "上传文件", "1000px", "550px");
      });
      $(document).on("click", ".add-btn2", function (e) {
        $.fn.TipsWindown.openWin("hangUp.html", "上传文件", "1000px", "600px");
      });

      /*导航栏伸缩*/
      /*$(document).on("click", ".tab-nav li span", function (e) {
        $('.tab-nav li span cite').hide(200);
        $('.tab-nav li span ').css('color', '#999999');
        if ($(this).find('cite').css('display') === 'none') {
          $(this).find('cite').show(200);
          $(this).css('color', '#2293fd');

        }
      });*/
      /*导航栏伸缩*/
      $(document).on("click", ".tab-nav li span", function (e) {

        if ($(this).find('cite').css('display') === 'none') {
          $('.tab-nav li span cite').hide(200);
          $('.tab-nav li span i').css('color', '#999999');
          $(this).find('cite').show(200).css('color', '#2293fd');
          ;
          $(this).find('i').css('color', '#2293fd');

        } else {
          // $(this).find('cite').show(200);
        }
      });
      /*显示导航栏提示*/
      $('.tab-nav li span').hover(function () {
        // var tipText = $(this).find('cite').text();
        // layer.tips(tipText, this, {tips: [1, '#2293fd']});
      }, function () {
        // layer.closeAll('tips');
      });
      
    });
    
    /* function sendArr(){
    	var docIDs = [];
		for(var i=0; i<10; i++){
			docIDs[i] = i;
		}
    	 
		$.ajax({
			type: 'post',
			url: 'struts2/batchDelDocAction.action',
			//contentType:"application/json",
			data: {
				docTable: 'Document123456789',
				docIDs: docIDs
			},
			traditional: true,
			success: function(data, textStatus){
				data = JSON.parse(data);
				console.log(data.msg);
			}
		})
    } */

  </script>
</head>
<body>
<div class="layui-form">
<!-- <input type="button" value="传递list" onclick="sendArr();"> -->
  <div class="common_header">
    <ul class="tab-nav">
      <li>
        <span>  <i class="aliconfont al-size-28 al-color-blue ">&#xe638;</i><cite class="al-color-blue">条目总数：200条</cite></span>
      </li>
      <li>
        <span>  <i class="aliconfont al-size-28 al-color-hui">&#xe64e;</i><cite
          class="tab-nav-display">查重：200件</cite></span>
      </li>
      <li>
        <span>  <i class="aliconfont al-size-28 al-color-hui">&#xe708;</i><cite class="tab-nav-display">含涉密：200件</cite></span>
      </li>
      <li>
        <span>  <i class="aliconfont al-size-28 al-color-hui">&#xeac3;</i><cite class="tab-nav-display">未挂接：200件</cite></span>
      </li>
    </ul>

    <div class="search_div">
      <div class="layui-input-inline newInput">
        <input type="text" name="username" lay-verify="required" placeholder="请输入系统名" autocomplete="off"
               class="layui-input searchInput">
      </div>
      <div class="searchIcon">
        <i class="layui-icon">&#xe615;</i>
      </div>
    </div>
    <script>
      //回车自动提交
      document.onkeydown = function (event) {
        e = event ? event : (window.event ? window.event : null);
        if (e.keyCode == 13) {
          //执行的方法
          $("#search").focus();
        }
      }
    </script>

  </div>
  <div class="common_css" align="center">
    <table class="layui-table" id="LAY_table_user" lay-filter="tabledemo"></table>

  </div>

</div>
</body>
</html>
