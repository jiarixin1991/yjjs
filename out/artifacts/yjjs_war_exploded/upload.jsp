<%--
  Created by IntelliJ IDEA.
  User: JRX
  Date: 2018/6/21
  Time: 10:52
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

    <!--引入JS-->
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>webuploader-0.1.5/webuploader.js"></script>
    <script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.percentageloader-0.1.min.js"></script>

    <!--SWF在初始化的时候指定，在后面将展示-->
</head>
<body>
<div id="uploader" class="wu-example">

    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
        <button id="btnSync" type="button" class="btn btn-warning">开始同步</button>
    </div>

    <!--用来存放文件信息-->
    <div id="thelist" style="width: 500px; height: 500px; background: yellow;" class="uploader-list"></div>
    <div class="row" >
        <div id="loader" STYLE="border: 1px red solid"></div>
    </div>
    <%--<<img src="" alt="">--%>
</div>
</body>

<script>
    var $loader;
    var totalKb = 748;
    var kb = 0;
    $(document).ready(function () {

        $loader = $("#loader").percentageLoader({
            width: 50,
            height: 50,
            progress: 0,
            value:12,
            /*controllable : true,
            onProgressUpdate : function (value) {
                loader.setProgress(value * 100.0);
            }*/
        });
       /* var animateFunc = function () {
            kb += 17;
            if (kb > totalKb) {
                kb = totalKb;
            }
            $loader.setProgress(kb / totalKb);
            $loader.setValue(kb.toString() + 'kb');
            if (kb < totalKb) {
                setTimeout(animateFunc, 25);
            }
        }

        setTimeout(animateFunc, 25);*/
    });
    var layelement, ilen = 1;
    layui.use('element', function () {
        layelement = layui.element;

    });

    var uploader = WebUploader.create({
        auto: false, //是否自动上传
        // swf文件路径
        swf: 'webuploader-0.1.5/Uploader.swf',

        // 文件接收服务端。
        server: '<%=basePath%>ts_fileUploadAction.action',
        chunked: true,  //分片处理
        chunkSize: 10 * 1024 * 1024, //每片5M
        threads: 3,//上传并发数。允许同时最大上传进程数
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        /* formData: {
             "status":"multi",
             "contentsDto.contentsId":"0000004730",
             "uploadNum":"0000004730",
             "existFlg":'false'
         },*/
    });
    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {
        $('#thelist').append('<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>');
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');
        // console.log("$percent" + $percent);
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress layui-progress progress-striped active">' +
                '<div class="progress-bar layui-progress-bar"  lay-percent="10%" role="progressbar" style="width: 20%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');
        console.log("进度条长度:" + percentage * 100 + "%");
        $percent.css('width', percentage * 100 + '%');
        $loader.setProgress(percentage);
    });
    uploader.on('uploadSuccess', function (file) {
        console.log("成功上:《" + file.name + "》文件" + "分片:" + Math.ceil(file.size / (10 * 1024 * 1024)));
        console.log("上传几次:" + ilen);
        ilen++;
        $.post("UploadSuccessAction.action", {
                "guid": uploader.options.formData.guid,
                chunks: Math.ceil(file.size / (10 * 1024 * 1024)),
                fileName: file.name
            },
            function (data) {
                $('#' + file.id).find('p.state').text('已上传');
            }, "json");
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        // $('#' + file.id).find('.progress').fadeOut();
    });
    $("#btnSync").on('click', function () {
        if ($(this).hasClass('disabled')) {
            return false;
        }
        uploader.options.formData.guid = Math.random();
        uploader.upload();

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
