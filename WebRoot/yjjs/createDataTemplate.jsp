<%--
  Created by IntelliJ IDEA.
  User: JRX
  Date: 2018/8/22
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>编辑模板</title>
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">


    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/aliconfont.css"/>
    <!--公共css-->
    <link href="<%=basePath%>css/common/common.css" rel="stylesheet">
    <!--导航条css-->
    <link href="<%=basePath%>css/nav_search.css" rel="stylesheet">
    <!--模板css-->
    <link href="<%=basePath%>css/template.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/edit_template.js"></script>
    <style type="text/css">
        html, body {
            min-width: 1366px;
            /*overflow: hidden;*/
        }
    </style>
    <script>
        $(function () {
            //进入页面查询所有固定模板字段 FIELDSTATE为S 系统字段,TemplateId为0的
            var url = "findFixedFieldAction.action";
            $.ajax({
                url: url,
                type: "post",
                datatype: "json",
                data: {
                    radom: Math.random(),
                },
                success: function (data) {
                    var jsonObj = JSON.parse(decodeURIComponent(data));
                    // var jsonstr = eval("(" + data + ")");
                    // layer.msg(jsonstr);
                    console.log(jsonstr);
                    if (jsonObj["code"] == 0) {
                        $("#labelCount").html(jsonObj["count"]);
                    }else{

                    }

                    // alert(JSON.stringify(jsonObj));
                    var num = 1;1
                    $("#add-create").before(' <tr  class="data-num layui-form " id="data-save' + num + '" lay-filter="test' + num + '">\n' +
                        '        <td>\n' +
                        '          <input type="text"  class="layui-input layui-table-edit input-text" name="cn-fieldname' + num + '" value=""/>' +
                        '          <input type="hidden" name="fieldState' + num + '" value="u"/>\n' +
                        '        </td>\n' +
                        '        <td >\n' +
                        '          <select class="create-type input-text" id="type-name' + num + '" lay-filter="test" name="type-name' + num + '" lay-verify="required" >\n' +
                        '            <option value="请选择">请选择</option>\n' +
                        '            <option value="i">整型</option>\n' +
                        '            <option value="v">字符型</option>\n' +
                        '            <option value="d">时间</option>\n' +
                        '\n' +
                        '          </select>\n' +
                        '        </td>\n' +
                        '        <td>\n' +
                        '          <input type="text" class="layui-input layui-table-edit input-text"name="fieldnum' + num + '" value=""/>\n' +
                        '        </td>\n' +
                        '        <td class="create-checkbox"><input class="input-text" type="checkbox" name="shemi' + num + '" value="0" lay-skin="switch"></td>\n' +
                        '        <td class="create-checkbox"><input type="checkbox" name="chachong' + num + '" value="0" lay-skin="switch"></td>\n' +
                        '        <td class="create-checkbox"><input type="checkbox" name="required' + num + '" value="0" lay-skin="switch"></td>\n' +
                        '        <td class="create-checkbox td-temp-del"> <i class="aliconfont temp-del">&#xe6b9;</i> </td>\n' +
                        '      </tr>');


                },
                error: function (textStatus) {//请求失败后调用的函数
                    console.log(textStatus.status + "\r\n" + textStatus.statusText);
                }


            });
        });
    </script>
</head>
<body>
<div id="top-anchors" style="border: 1px indigo solid;display: none;">锚点行</div>
<div id="top-bg"
     style=" width:100%;height: 45px; position: fixed; z-index: 99999; background-color: white; display: none; "></div>
<div class="common_header2">
    <!--<div id="top-bg" style="border: 1px indigo solid; width:100%;height: 45px;margin-top: -45px; z-index: 99999; background-color: white "></div>-->

    <ul class="tab-nav" style="border:  1px red solid">
        <li>
            <div class="layui-form-item sys-form-item">
                <label class="layui-form-label sys-form-label" style="text-align: center">
                    <i class="aliconfont al-color-blue al-size-28">&#xe677;</i>&nbsp;&nbsp;系统名称:
                </label>
                <div class="layui-input-block sys-input-block">
                    <input type="text" name="title" id="tempName" required lay-verify="required" placeholder=""
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </li>

        <li class="nav-input-css nav-li-left" style="">

            <a href="javascript:;" id="create-add" class="layui-btn layui-btn-normal" style="width: 100px;"><i
                    class="aliconfont">&#xe62a;</i>&nbsp;新增</a>
        </li>
        <li class="nav-input-css">
            <a href="javascript:;" class="layui-btn layui-btn-primary" style="width: 100px;">保存</a>
        </li>


    </ul>

</div>
<div class=" common_display" style="border: 1px red solid; height: 100px; display: none;"></div>
<div class="create-moban layui-form " style="margin: 0 auto;">
    <form id="formadd">
        <table id="data-create" class="layui-table" style="width: 66%;margin: 0 19%;z-index: 800; "><!--lay-even隔行背景-->
            <colgroup>
                <!--<col width="150">-->
                <!--<col width="100">-->
                <col width="35%">
                <col width="10%">
                <col>
                <col>
                <col>
                <col>
                <col>
                <!--<col width="80">-->
                <!--<col width="100">-->
                <!--<col width="100">-->
                <!--<col width="100">-->
                <!--<col width="35">-->
            </colgroup>
            <thead>
            <tr>
                <th>字段名</th>
                <th>类型</th>
                <th>长度</th>
                <th>是否涉密</th>
                <th>是否查重</th>
                <th>必填</th>
                <!--<th>删除</th>-->
            </tr>
            </thead>
            <tbody>

            <tr class="data-num " id="data-save1" rel="1">
                <td>
                    <input type="text" class="layui-input layui-table-edit input-text" name="cn-fieldname1" value=""/>
                    <input type="hidden" name="fieldState1" value="s"/><%--s:系统创建字段--%>
                    <%--<input type="hidden" name="fieldState" value="u"/>--%><%--u:用户自定义字段--%>
                </td>
                <td>
                    <select id="type-name1" class="create-type" name="type-name1" lay-verify="required"
                            lay-filter="test">
                        <option value="请选择">请选择</option>
                        <%--<option value="i">int</option>
                        <option value="v">varchar</option>
                        <option value="c">datetime</option>--%>
                        <option value="i">整型</option>
                        <option value="v">字符型</option>
                        <option value="c">时间</option>

                    </select>
                </td>

                <td>
                    <input type="text" class="layui-input layui-table-edit input-text" name="fieldnum1" value=""/>
                </td>
                <td class="create-checkbox"><input type="checkbox" name="shemi1" value="0" lay-skin="switch"></td>
                <td class="create-checkbox"><input type="checkbox" name="chachong1" value="0" lay-skin="switch"></td>
                <td class="create-checkbox"><input type="checkbox" name="required1" value="0" lay-skin="switch"></td>
                <td class="create-checkbox td-temp-del"><i class="aliconfont temp-del" title="删除">&#xe6b9;</i></td>
            </tr>
            <tr class="data-num " id="data-save2" rel="2">
                <td>
                    <input type="text" class="layui-input layui-table-edit input-text" name="cn-fieldname2" value=""/>
                    <input type="hidden" name="fieldState2" value="s"/>
                </td>
                <td>
                    <select id="type-name2" class="create-type" name="type-name2" lay-verify="required"
                            lay-filter="test">
                        <option value="请选择">请选择</option>
                        <option value="i">整型</option>
                        <option value="v">字符型</option>
                        <option value="c">时间</option>
                    </select>
                </td>

                <td>
                    <input type="text" class="layui-input layui-table-edit input-text" name="fieldnum2" value=""/>
                </td>
                <td class="create-checkbox"><input type="checkbox" name="shemi2" value="0" lay-skin="switch"></td>
                <td class="create-checkbox"><input type="checkbox" name="chachong2" value="0" lay-skin="switch"></td>
                <td class="create-checkbox"><input type="checkbox" name="required2" value="0" lay-skin="switch"></td>
                <td class="create-checkbox td-temp-del"><i class="aliconfont temp-del" title="删除">&#xe6b9;</i></td>
            </tr>
            <tr class="data-num " id="data-save3" rel="3">
                <td>
                    <input type="text" class="layui-input layui-table-edit input-text" name="cn-fieldname3" value=""/>
                    <input type="hidden" name="fieldState3" value="s"/>
                </td>
                <td>
                    <select id="type-name3" class="create-type" name="type-name3" lay-verify="required"
                            lay-filter="test">
                        <option value="请选择">请选择</option>
                        <option value="i">整型</option>
                        <option value="v">字符型</option>
                        <option value="c">时间</option>
                    </select>
                </td>

                <td>
                    <input type="text" class="layui-input layui-table-edit input-text" name="fieldnum3" value=""/>
                </td>
                <td class="create-checkbox"><input type="checkbox" name="shemi3" value="0" lay-skin="switch"></td>
                <td class="create-checkbox"><input type="checkbox" name="chachong3" value="0" lay-skin="switch"></td>
                <td class="create-checkbox"><input type="checkbox" name="required3" value="0" lay-skin="switch"></td>
                <td class="create-checkbox td-temp-del"><i class="aliconfont temp-del" title="删除">&#xe6b9;</i></td>
            </tr>

            <tr id="add-create">
                <td id="create-one" colspan="6" style="text-align: center;">
                    <input type="hidden" id="num" value="3"/>
                    新增<i class="aliconfont" style="font-size: 14px">&#xe60a;</i>
                </td>
            </tr>
            <tr>
                <td colspan="7" style="text-align: center; height: 100px ;border: 0">
                    <!-- <button>
                       <i class="aliconfont" style="font-size: 14px">&#xe60a;</i>
                       保存
                     </button>
                     <button>
                       <i class="aliconfont" style="font-size: 14px">&#xe60a;</i>
                       重置
                     </button>-->
                    <div class="btnDiv">
                        <button type="submit" class="layui-btn layui-btn-normal add-btn  ">
                            保存
                        </button>
                        <button class="layui-btn layui-btn-normal add-btn  " type="reset">
                            重置
                        </button>
                        <a id="cs" class="layui-btn layui-btn-normal add-btn  ">
                            测试
                        </a>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <!--<div  style="width: 100%; height: 50px ; border: 1px solid rebeccapurple;position: fixed; bottom: 0;left: 0;  ">

    </div>-->
</div>
<div class="anchors" style=" width: 50px; position: fixed; right: 50px; bottom: 50px; opacity: 0.7; cursor: pointer"
     title="顶部">
    <!--<i class="aliconfont"></i>-->
    <img src="img/anchors.png">
</div>

<script>
    var form, element;
    var params = [];
    var values, a = "data-save", n = a.length, rel, param;

    layui.use(['element', 'layer', 'form'], function () {
        element = layui.element;
        form = layui.form;
        layer = layui.layer;
    });
    //添加一行
    // $("#create-one").on("click", function () {
    $(document).on("click", "#create-one,#create-add", function () {

        //添加一行添加一个对象
        // values = {};
        // params.push(values);
        var num = $("#num").val();
        num = 0;
        num = parseInt(num) + 1;
        $("#add-create").before(' <tr  class="data-num layui-form " id="data-save' + num + '" lay-filter="test' + num + '">\n' +
            '        <td>\n' +
            '          <input type="text"  class="layui-input layui-table-edit input-text" name="cn-fieldname' + num + '" value=""/>' +
            '          <input type="hidden" name="fieldState' + num + '" value="u"/>\n' +
            '        </td>\n' +
            '        <td >\n' +
            '          <select class="create-type input-text" id="type-name' + num + '" lay-filter="test" name="type-name' + num + '" lay-verify="required" >\n' +
            '            <option value="请选择">请选择</option>\n' +
            '            <option value="i">整型</option>\n' +
            '            <option value="v">字符型</option>\n' +
            '            <option value="d">时间</option>\n' +
            '\n' +
            '          </select>\n' +
            '        </td>\n' +
            '        <td>\n' +
            '          <input type="text" class="layui-input layui-table-edit input-text"name="fieldnum' + num + '" value=""/>\n' +
            '        </td>\n' +
            '        <td class="create-checkbox"><input class="input-text" type="checkbox" name="shemi' + num + '" value="0" lay-skin="switch"></td>\n' +
            '        <td class="create-checkbox"><input type="checkbox" name="chachong' + num + '" value="0" lay-skin="switch"></td>\n' +
            '        <td class="create-checkbox"><input type="checkbox" name="required' + num + '" value="0" lay-skin="switch"></td>\n' +
            '        <td class="create-checkbox td-temp-del"> <i class="aliconfont temp-del">&#xe6b9;</i> </td>\n' +
            '      </tr>')
        $("#num").val(num);
        var name = 'test' + num;
        form.render('checkbox');
        form.render('select', name);
        /*新增一行后自动跳到当前行*/
        $("html,body").animate({scrollTop: $("#data-save" + num).offset().top}, 1000);
        /*现实删除*/
        $('.data-num .td-temp-del .temp-del').hide();
        $("#data-save" + num).find('.td-temp-del').find('.temp-del').show();
    });
    //删除一行
    $(document).on("click", ".temp-del", function () {
        /*  rel = $(this).parent().parent().attr("id").substring(n);
          // params.splice(rel, 1);
          values = params[rel - 1]
          params.remove()
          console.log(params);*/

        $(this).parent().parent().remove();


    });
    /*//保存数据时
    $(document).on("blur", ".input-text", function () {
      // console.log("触发");
      // console.log($(this).attr("name"));
      var name = $(this).attr("name");
      // console.log($(this).parent().parent().attr("id").substring(n));
      rel = $(this).parent().parent().attr("id").substring(n);
      // console.log($(this).val())
      values = params[rel-1]
      switch (name) {
        case "cn-fieldname" + rel:
          values["cn-fieldname"] = $(this).val();
          break;
        case "en-fieldname" + rel:
          values["en-fieldname"] = $(this).val();
          break;
        case "fieldnum" + rel:
          values["fieldnum"] = $(this).val();
          break;
      }
      console.log(JSON.stringify(params));

    })*/
    /* var json = [];
     var values = {};*/
    $(function () {
        $("#cs").on("click", function () {
            params = [];
            console.log("长度：" + $("#data-create .data-num").length);
            rel = $("#data-create .data-num").length;
            /*values = {};
            for (var i = 0; i < rel; i++) {
              params.push(values);
            }*/
            $("#data-create .data-num").each(function (index, element) {
                var id = $(this).attr('id');
                var idNum = id.substring(n);
                console.log("第" + idNum + "行");

                values = {};
                params.push(values);
                param = params[index];
                //中文名
                $(this).find("td input[name='cn-fieldname" + idNum + "']").each(function (tdindex, element) {

                    param["cn_fieldName"] = $(element).val();

                });
                //系统字段，用户字段判断
                $(this).find("td input[name='fieldState" + idNum + "']").each(function (tdindex, element) {

                    param["fieldState"] = $(element).val();

                });
                /* $(this).find("td input[name='en-fieldname" + idNum + "']").each(function (tdindex, element) {

                     param["en-fieldname"] = $(element).val();
                 });*/
                //字段长度判断
                $(this).find("td input[name='fieldnum" + idNum + "']").each(function (tdindex, element) {

                    param["fieldSize"] = $(element).val();
                });
                //字段类型
                // console.log($(this).find("td #type-name" + idNum + " option:selected").val());
                $(this).find("td .create-type  option:selected").each(function (tdindex, element) {
                    param["fieldType"] = $(element).val();
                });
                //字段是否涉密
                $(this).find("td input[name='shemi" + idNum + "']").each(function (tdindex, element) {
                    if ($(this).is(':checked')) {
                        console.log("shemi" + idNum + "选中:" + $(this).is(':checked'));
                        param["isSecret"] = $(this).val();
                    } else {
                        console.log("shemi" + idNum + "未选中:" + $(this).is(':checked'));
                        param["isSecret"] = "1";
                    }
                });
                //字段是否查重
                $(this).find("td input[name='chachong" + idNum + "']").each(function (tdindex, element) {
                    if ($(this).is(':checked')) {
                        console.log("chachong" + idNum + "选中:" + $(this).is(':checked'));
                        param["isRepeat"] = $(this).val();
                    } else {
                        console.log("chachong" + idNum + "未选中:" + $(this).is(':checked'));
                        param["isRepeat"] = "1";
                    }
                });
                //字段是否必填
                $(this).find("td input[name='required" + idNum + "']").each(function (tdindex, element) {
                    if ($(this).is(':checked')) {
                        console.log("required" + idNum + "选中:" + $(this).is(':checked'));
                        param["isFill"] = $(this).val();
                    } else {
                        console.log("required" + idNum + "未选中:" + $(this).is(':checked'));
                        param["isFill"] = "1";
                    }
                });


            });

            // console.log(JSON.stringify(params));
            var url = "createTemplateFieldAction.action";
            var tempName = $("#tempName").val();
            var jsonString =
                {
                    "command": "createTemplateField",
                    "userid": "USERS000000000000000",
                    "roleid": "",
                    "params": params,
                    "TempName": tempName,
                };
            console.log(jsonString)
            $.ajax({
                url: url,
                type: "post",
                datatype: "json",
                data: {
                    jsonReceive: JSON.stringify(jsonString),
                },
                success: function (data) {
                    var jsonObj = JSON.parse(decodeURIComponent(data));
                    if (jsonObj["code"] == 0) {
                        $("#labelCount").html(jsonObj["count"]);
                    }
                    alert(JSON.stringify(jsonObj));

                },
                error: function (textStatus) {//请求失败后调用的函数
                    console.log(textStatus.status + "\r\n" + textStatus.statusText);
                }


            });
        });


    });

</script>
</body>
</html>

