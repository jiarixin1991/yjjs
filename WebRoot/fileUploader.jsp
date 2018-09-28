<%--
  Created by IntelliJ IDEA.
  User: JRX
  Date: 2018/6/27
  Time: 14:44
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

<html>

<head>
    <base href="<%=basePath%>">
    <title>百度webUploader上传</title>

    <!--引入CSS-->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>webuploader-0.1.5/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.percentageloader-0.1.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/fileUploader.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/iconfont.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
    <!--引入JS-->
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>webuploader-0.1.5/webuploader.js"></script>
    <script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.percentageloader-0.1.min.js"></script>
    <%--<script type="text/javascript" src="<%=basePath%>js/iconfont.js"></script>--%>

    <!--SWF在初始化的时候指定，在后面将展示-->
    <style>

    </style>
</head>
<body>
<div id="uploader" class="create-fileupload wu-example">
    <!--<div id="uploader" class="wu-example">-->
    <p style="text-align: right; font-size: 15px;color: #2293fd;">
        重置<i class="aliconfont" style="font-size: 18px ;font-weight: bolder; ">&nbsp;&nbsp;&#xe6a4;</i>
    </p>
    <div class="btns">

        <div id="picker" class="btn-style ">
            <p style="text-align: center;padding-top: 40px;">
                <i class="aliconfont" style="color: #2293fd; font-size: 5em">&#xe620;</i>
            </p>
            <br/>


            <p style="text-align: center; color: #2293fd;font-weight: bolder; ">选择文件</p>
        </div>
        <div id="btnSync" class="btn-style">
            <p style="text-align: center;padding-top: 50px;">
                <i class="aliconfont" style="color: #2293fd; font-size: 5em">&#xe640;</i>
            </p>
            <br/>
            <!-- <svg class="icon" aria-hidden="true">
               <use xlink:href="#icon-shangchuan-"></use>
             </svg>-->
            <p style="text-align: center; color: #2293fd;font-weight: bolder;">开始上传</p>
            <!--<button id="btnSync" type="button" class="btn btn-warning">开始同步</button>-->

        </div>

    </div>


    <!--用来存放文件信息-->
    <!--<div id="thelist" style="width: 500px; height: 500px; background: yellow;" class="uploader-list"></div>-->
    <!--<div class="row">
      <div id="loader" STYLE="border: 1px red solid"></div>
    </div>
   <<img src="" alt="">-->
    <!--</div>-->
    <!--用来存放文件信息-->
    <div id="thelist" class=" fileList" style="display: none">
        <table class="layui-table file-table" lay-skin="line">
            <colgroup>
                <col>
                <col>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
            <!--<tr>
              <th>序号</th>
              <th>文件名称</th>
              <th>文件大小</th>
              <th>上传状态</th>
              <th>上传进度</th>
              <th>操作</th>
            </tr>-->
            </thead>
            <tbody>

            </tbody>
        </table>

    </div>
    <p style="text-align: right; font-size: 15px;color: #2293fd; padding-right: 220px;">
        此次共上传<span id="fileNum">0</span>个文件
    </p>
</div>


</body>

<script>
    var fileMd5;
    var fileSuffix;
    var $list = $("#thelist table>tbody");
    var state = 'pending';//初始按钮状态
    var $btn = $("#btn");
    var count = 0;
    // var map=new HashMap();
    var layelement, ilen = 1;
    layui.use('element', function () {
        layelement = layui.element;

    });

    var uploader = WebUploader.create({
        auto: false, //是否自动上传
        // swf文件路径
        swf: 'webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server: 'ts_fileUploadAction.action',//http://localhost:8080/WebUploader/
        chunked: true,  //分片处理
        chunkSize: 10 * 1024 * 1024, //每片5M
        threads: 3,//上传并发数。允许同时最大上传进程数
        chunkRetry:2,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

    });
    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {
        // alert(uploader.getFiles().length);
        $('#fileNum').html(uploader.getFiles().length);
        /*$('#thelist').append('<div id="' + file.id + '" class="item">' +
          '<h4 class="info">' + file.name + '</h4>' +
          '<p class="state">等待上传...</p>' +
          '</div>');*/
        //保存文件扩展名
        fileSuffix = file.ext;
        var fileSize = file.size;
        var fileSizeStr = "";
        fileSizeStr = WebUploader.Base.formatSize(fileSize);
        count++;
        $list.append(
            '<tr id="' + file.id + '" class="item file-height" flag=0>' +
            '<td class="index">' + count + '</td>' +
            '<td class="info">' + file.name + '</td>' +
            '<td class="size">' + fileSizeStr + '</td>' +
            '<td class="state">等待上传...</td>' +
            '<td class="percentage">0%</td>' +
            '<td class="operate"><i class="aliconfont">&#xe621;</i></td></tr>' +
            '<tr class="progress-height"><td colspan="6" class="percentage ' + file.id + '"> <div class="progress">\n' +
            '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 0%">\n' +
            '<span class="sr-only">45% Complete</span>\n' +
            '</div>\n' +
            '</div></td></tr>');

    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id);
        // $percent = $li.find('.progress .progress-bar');
        var $td = $('.' + file.id),
            $percent = $td.find('.progress .progress-bar');
        // console.log("$percent" + $percent);
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress">\n' +
                '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 0%">\n' +
                '<span class="sr-only">45% Complete</span>\n' +
                '</div>\n' +
                '</div>').appendTo($td).find('.progress-bar');
        }


        $li.find('.state').text('上传中');
        percentage = Math.round(percentage * 100);

        $li.find('.percentage').text(percentage + "%");
        console.log("进度条长度:" + percentage + "%");
        $percent.css('width', percentage + '%');


        console.log("进度条长度:" + percentage + "%");
        $percent.css('width', percentage + '%');
    });
    uploader.on('uploadSuccess', function (file) {
        console.log("成功上:《" + file.name + "》文件" + "分片:" + Math.ceil(file.size / (10 * 1024 * 1024)));
        console.log("上传几次:" + ilen);
        ilen++;
        $.post("UploadSuccessAction.action",
            {
                "guid": uploader.options.formData.guid,
                chunks: Math.ceil(file.size / (10 * 1024 * 1024)),
                fileName: file.name
            },
            function (data) {
                // alert("data:"+data);
                $('#' + file.id).find('.state').text('已上传');
            }, "json").fail(function () {
            console.log("分片合并出错请联系管理员");
        });
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        // $('#' + file.id).find('.progress').fadeOut();
    });
    $("#btnSync").on('click', function () {
        var len = uploader.getFiles().length
        if (len != 0) {
            $(".btns").fadeOut("slow");
            $('#thelist').show();
            if ($(this).hasClass('disabled')) {
                return false;
            }
            uploader.options.formData.guid = Math.random();
            uploader.upload();
        }
    });
    //当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
    uploader.on("uploadAccept", function (object, ret) {
        //服务器响应了
        //ret._raw  类似于 data
        console.log("uploadAccept");
        console.log(ret);
        /*var data = JSON.parse(ret._raw);
        if (data.resultCode != "1" && data.resultCode != "3") {
            if (data.resultCode == "9") {
                alert("error");
                uploader.reset();
                return;
            }
        } else {
            uploader.reset();
            alert("error");
        }*/
    })
</script>
</html>

