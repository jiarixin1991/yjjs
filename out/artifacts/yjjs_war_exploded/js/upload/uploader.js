var fileMd5;//md5值
var fileSuffix;//文件后缀格式
var $list = $(".pages .files");// 显示上传div
var state = 'pending';//初始按钮状态 挂起
var $btn = $("#btn");
var count = 0;
var map = new HashMap();
//监听分块上传过程中的三个时间点
WebUploader.Uploader.register({
    "before-send-file": "beforeSendFile",
    "before-send": "beforeSend",
    "after-send-file": "afterSendFile",
}, {
    //时间点1：所有分块进行上传之前调用此函数
    beforeSendFile: function (file) {
        var deferred = WebUploader.Deferred();
        //1、计算文件的唯一标记，用于断点续传
        (new WebUploader.Uploader()).md5File(file, 0, 10 * 1024 * 1024).progress(function (percentage) {
            // $('#' + file.id).find("p.state").text("正在读取文件信息...");
        }).then(function (val) {
            fileMd5 = val;
            console.log(fileMd5);
            // $('#' + file.id).find("p.state").text("成功获取文件信息...");
            var md5Name = "fileMd5" + file.id;
            map.put(md5Name, fileMd5);
            console.log("26行");
            console.log(map);
            //获取文件信息后进入下一步
            deferred.resolve();
        });
        return deferred.promise();
    },
    //时间点2：如果有分块上传，则每个分块上传之前调用此函数
    beforeSend: function (block) {
        var deferred = WebUploader.Deferred();
        // console.log(block);
        $.ajax({
            type: "POST",
            url: "Md5CheckAction.action",
            data: {
                //文件唯一标记
                fileMd5: fileMd5,
                //当前分块下标
                chunk: block.chunk,
                //当前分块大小
                chunkSize: block.end - block.start,
                guid: uploader.options.formData.guid,
                // fileName:block.file.name,
            },
            dataType: "json",
            success: function (jsonStr) {
                var s = JSON.parse(jsonStr);
                // console.log(s.ifExist);
                if (jsonStr.ifExist == 0) {
                    //分块存在，跳过
                    console.log("分块存在");
                    deferred.reject();
                } else {
                    //分块不存在或不完整，重新发送该分块内容
                    console.log("分块不存在");
                    deferred.resolve();
                }
            }
        });

        this.owner.options.formData.fileMd5 = fileMd5;

        deferred.resolve();
        return deferred.promise();
    },
    //时间点3：所有分块上传成功后调用此函数
    afterSendFile: function (file) {
        //如果分块上传成功，则通知后台合并分块
        var md5Name = "fileMd5" + file.id;
        var Md5Value = map.get(md5Name);
        console.log( "70行");
        console.log( Md5Value);
        $.ajax({
            type: "POST",
            url: "mergeFilesAction.action",
            data: {
                fileMd5: Md5Value,
                fileSuffix: file.ext,
                guid: uploader.options.formData.guid,
            },
            success: function (jsonStr) {
                console.log("上传成功" + jsonStr);
                console.log(jsonStr);


            }
        });
    }
});

var uploader = WebUploader.create({
    auto: false, //是否自动上传
    swf: 'webuploader-0.1.5/Uploader.swf',// swf文件路径
    server: 'md5FileUploadAction.action',// 文件接收服务端。
    chunked: true,  //分片处理
    chunkSize: 10 * 1024 * 1024, //每片5M
    threads: 3,//上传并发数。允许同时最大上传进程数
    chunkRetry: 3,//[默认值：2] 如果某个分片由于网络问题出错，允许自动重传多少次？
    // 选择文件的按钮。可选。
    pick: '#picker', // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    resize: false,// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
    //duplicate: false,//去重（尝试）根据文件名字、文件大小和最后修改时间来生成hash Key
    // prepareNextFile: true, //在上传当前文件时，准备好下一个文件
    accept: {
        extensions: "txt,jpg,jpeg,bmp,png,zip,rar,war,pdf,cebx,doc,docx,ppt,pptx,xls,xlsx,iso",
        mimeTypes: '.txt,.jpg,.jpeg,.bmp,.png,.zip,.rar,.war,.pdf,.cebx,.doc,.docx,.ppt,.pptx,.xls,.xlsx,.iso'
    }
});
// 当有文件被添加进队列的时候
uploader.on('fileQueued', function (file) {

    // $('#fileNum').html(uploader.getFiles().length); //上传多少个文件

    //保存文件扩展名
    fileSuffix = file.ext;
    var fileSize = file.size;
    var fileSizeStr = "";
    fileSizeStr = WebUploader.Base.formatSize(fileSize);
    var fileType = file.type;
    var fileName = file.name;
    var fileDate = new Date(file.lastModified).Format("yyyy-MM-dd hh:mm:ss");

    console.log("fileType:" + fileType + "-----fileDate:" + fileDate);
    count++;
    /* $list.append(
         '<tr id="' + file.id + '" class="item file-height" flag=0>' +
         '<td class="index">' + count + '</td>' +
         '<td class="info">' + file.name + '</td>' +
         '<td class="size">' + fileSizeStr + '</td>' +
         '<td class="state">等待上传...</td>' +
         '<td class="percentage">0%</td>' +
         // '<td><button name="upload" class="btn btn-warning">暂停</button></td>' +
         '<td class="operate"><i class="aliconfont">&#xe621;</i></td></tr>' +
         '<tr class="progress-height"><td colspan="6" class="percentage ' + file.id + '"> <div class="progress">\n' +
         '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 0%">\n' +
         '<span class="sr-only">45% Complete</span>\n' +
         '</div>\n' +
         '</div></td></tr>');*/
    //文件信息
    var str = '<li class="poster-item zturn-item del-li">\n' +
        '                <div class="delFile">\n' +
        '                    <i id="' + file.id + '" class="iconfont icon-guanbi newIcon3"></i>\n' +
        '                </div>\n' +
        '                <div class="fileType">\n';

    //文件上传进度
    var strPro = '<div class="eachFile" id="table'+file.id+'">\n' +
        '                    <i class="iconfont icon-guanbi newIcon4 close-icon"></i>\n' +
        '                <div class="fileInfoPro">\n' +
        '                    <div class="fileIcon">\n';


    if (fileType == 'application/msword' || fileType == 'application/vnd.openxmlformats-officedocument.wordprocessingml.document') {    //word
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-word1"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-word1"></use>\n' +
            '                    </svg>\n';
    } else if (fileType == 'application/vnd.ms-excel' || fileType == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {   //excel
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-excel"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-excel"></use>\n' +
            '                    </svg>\n';
    } else if (fileType == 'application/pdf') {   //pdf
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-pdf3"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-pdf3"></use>\n' +
            '                    </svg>\n';
    } else if (fileType == 'text/plain') {   //txt
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-txt"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-txt"></use>\n' +
            '                    </svg>\n';
    } else if (fileType == 'image/jpeg') {   //jpg
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-jpg"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-jpg"></use>\n' +
            '                    </svg>\n';
    } else if (fileType == 'image/png') {   //png
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-png"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-png"></use>\n' +
            '                    </svg>\n';
    } else if (fileType == 'application/x-zip-compressed') {
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-ZIP"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-ZIP"></use>\n' +
            '                    </svg>\n';
    } else {
        str += '<svg class="icon newIcon2" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-qita1"></use>\n' +
            '                    </svg>\n';
        strPro += '<svg class="icon newIconPro" aria-hidden="true">\n' +
            '                        <use xlink:href="#icon-qita1"></use>\n' +
            '                    </svg>\n';
    }

    str += '                </div>\n' +
        '                <div class="fileInfo">\n' +
        '                    <div class="textInfo">\n' +
        '                        <span class="fileName" title="' + fileName + '">文件名：' + fileName + '</span>\n' +
        '                        <span>文件大小：' + fileSize + '</span>\n' +
        '                        <span>最后修改时间：' + fileDate + '</span>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </li>';

    strPro += '                    </div>\n' +
        '                    <div class="progress">' +
        '                        <span class="proNum '+file.id+'">0%</span>\n' +
        '                        <span class="fileNamePro" title="' + fileName + '">' + fileName + '</span>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>';

    $("#zturn").append(str);
    $list.append(strPro);
    map.put(file.id, file);
    console.log(map);
    // map.put(file.id+"",file);
    scroll();


});
// 文件上传过程中创建进度条实时显示。
uploader.on('uploadProgress', function (file, percentage) {
    var $span = $('.' + file.id);
    percentage = Math.round(percentage * 100);
    // console.log("进度条长度:" + percentage + "%");
    $span.text( percentage + '%');
});
uploader.on('uploadSuccess', function (file, type) {
    console.log("成功上:《" + file.name + "》文件" + "分片:" + Math.ceil(file.size / (10 * 1024 * 1024)));
    console.log(type);
    console.log("文件上传完毕时状态：" + type);
    // console.log("上传几次:" + ilen);
    // ilen++;
    var $span = $('.' + file.id);
    $span.text( '上传成功');
    $('#' + file.id).find('p.state').text('已上传');
});

uploader.on('uploadError', function (file) {
    console.log("uploadError:" + file);
    var $span = $('.' + file.id);
    $span.text( '上传出错');
});

uploader.on('uploadComplete', function (file) {
    // $('#' + file.id).find('.progress').fadeOut();
});
/*$("#btnSync").on('click', function () {
    var len = uploader.getFiles().length
    if (len != 0) {
        $(".btns").fadeOut("slow");
        $('#thelist').show();
        if ($(this).hasClass('disabled')) {
            return false;
        }
        var timestamp1 = Date.parse(new Date());
        // uploader.options.formData.guid = Math.random();
        // console.log("上传时间戳：" + timestamp1);
        uploader.options.formData.guid = timestamp1;
        uploader.upload();
    }
});*/
//当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
uploader.on("uploadAccept", function (object, ret) {
    //服务器响应了
    //ret._raw  类似于 data
    console.log("uploadAccept");
    // console.log(ret);

})
/*$btn.on('click', function () {
    //state 自己设定的状态 只有状态为上传uploading时才可以暂停当前上传
    console.log("state:" + state);
    if (state === 'uploading') {
        console.log("上传停止！！！");
        uploader.stop(true);
    } else {
        console.log("上传开始！！！");
        uploader.upload();
    }

});*/


//通过all监听所有uploader的时间 用type判断当前上传状态 是在上传，还是停止上传，还是上传完成
uploader.on("all", function (type) {
    // console.log("type:" + type);
    if (type === 'startUpload') {
        state = 'uploading';
    } else if (type === 'stopUpload') {
        state = 'paused';
    } else if (type === 'uploadFinished') {
        state = 'done';
    }
    if (state === 'uploading') {
        $btn.text('暂停上传');
    } else {
        $btn.text('开始上传');
    }
    // console.log("state:" + state);

});
uploader.on("error", function (type) {
    console.log("文件error时状态：" + type);
    if (type == "Q_EXCEED_NUM_LIMIT") {
        console.log("您已经设置过上传的文件");
    } else if (type == "Q_EXCEED_SIZE_LIMIT") {
        console.log("文件大小不能超过最大值！！！");
    } else if (type == "Q_TYPE_DENIED ") {
        console.log("改文件格式不能被上传！！！");
    } else {
        console.log("上传出错！请检查后重新上传！错误代码" + type);
    }
});
$("body").on("click", "#uploadTable button[name='upload']", function () {
    //异位运算 相同即为0 不同即为1  0^0 =0 1^1 = 0 , 1^0 =1 0^1 = 1
    var a = $(this).parents("tr.item").attr("flag");
    flag = $(this).parents("tr.item").attr("flag") ^ 1;
    $(this).parents("tr.item").attr("flag", flag);
    var id = $(this).parents("tr.item").attr("id");

    if (flag == 1) {//暂停
        $(this).text("继续");//按钮变成继续执行暂停上传
        uploader.stop(uploader.getFile(id, true));


    } else {//继续
        $(this).text("暂停");//按钮变成暂停执行继续上传
        console.log("重新上传开始");
        uploader.retry(uploader.getFile(id));
        console.log("重新上传结束");

    }
});

$("body").on("click", ".close-icon", function () {
    var id = $(this).parents("div .eachFile").attr("id");
    id = id.substring(5);
    console.log(id);
    $(this).parents("div .eachFile").remove();
    $('#' + id).parents('li .poster-item').remove();
    scroll();
    uploader.removeFile(uploader.getFile(id, true));
    map.remove(id);
});