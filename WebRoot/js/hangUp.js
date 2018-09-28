var currentPage = 1,
    totalLines = 0,
    pageSize = 6,
    keyWord = null;

/*点击结果模块挂接*/
$(function(){

    /**文件挂接**/
    $(".files").on("click", ".hangFile", function () {       //点击文件挂接
        var fileName = $(this).find(".files_name")[0].innerText;    //文件名
        //console.log(fileName);
        var _index = $(this).parent().index();
        $(this).parent().html("").css("background-color", "#11CA5D").html("<span class=\"hang_wancheng\">\n" +
            "<span class=\"hangFile\">已挂接</span>&nbsp;<i class=\"iconfont icon-wancheng icon_hanged\"></i>\n" +
            "</span>");

        $(".hangedFiles").prepend("<li>\n" +
            "                    <span class=\"hangedIcon1\">123</span>\n" +
            "                    <span class=\"fileName\" title="+fileName+">原件名："+fileName+"</span>\n" +
            "                    <span class=\"hangedIcon2\">\n" +
            "                        <i class=\"iconfont icon-ai67\"></i>\n" +
            "                    </span>\n" +
            "                </li>");

        setTimeout(function timer(){
            //console.log(_index);
            $(".files").eq(_index).remove();

        },1000)

    });

    /*$(".hangedFiles").on('click', ".hangedIcon2 i", function(){      //静态点击  点击减号删除
        //$(this).parent().parent("li").remove();
    });*/

    /*$(".search_result").on("click", ".files .check_file i", function(){    //放大镜查看原件
        //console.log("111");
    });*/


    /**未挂接分页**/
    getPageInfo(keyWord);
    toPage(keyWord);
    $(".search_btn").click(function(){
    	keyWord = $(".search_text").val();
        currentPage = 1; //当点击搜索的时候，应该回到第一页
        getPageInfo(keyWord);
        toPage(keyWord);//然后进行分页的初始化
    });
    
    /**已挂接文件**/
    getHangedFile();
		
});

function getHangedFile(){
	$.ajax({
		type: 'post',
		url: 'struts2/FileHasHangedAction.action',
		data: {
			sysID: "1",     //系统ID
			docID: "001"     //条目ID
		},
		success: function(data, textStatus){
			data = JSON.parse(data);
			var result = data.result;
			var count = data.count;
			//console.log("已挂接："+data.result[0].FILENAME);
			//console.log(data.result.length);
			$(".hangedFiles").html("");
			for(var i=0; i<result.length; i++){
			    var fileName = result[i].FILENAME;
			    var fileID = "'"+result[i].FILEID+"'";
				var str= '<li>\n' +
                    '                    <span class="hangedIcon1"></span>\n' +
                    '                    <span class="fileName" title="原件名：'+fileName+'" onclick="showFileInfo('+fileID+')">原件名：'+fileName+'</span>\n' +
                    '                    <span class="hangedIcon2" onclick="cancelHanged('+fileID+')">\n' +
                    '                        <i class="iconfont icon-ai67"></i>\n' +
                    '                    </span>\n' +
                    '                </li>'
                $(".hangedFiles").append(str);
			}
			$(".footInfo").html("已挂接文件"+count+"个");
		}
	});
}

//取消挂接
function cancelHanged(fileID){
	$.ajax({
		type: 'post',
		url: 'struts2/CancleHangedFileAction.action',
		data: {
			fileID: fileID
		},
		success: function(data, textStatus){
			data = JSON.parse(data);
			console.log(data.result);   //0成功；  1失败
		}
	})
}

//获取数据  
function getPageInfo(keyWord){
    $.ajax({
        type:"post",
        async:false,
        url:"struts2/GetFileNotHangedAction.action",     //获取分页信息
        data:{
            page : currentPage,
            limit : pageSize,
            sysID : '1',     //系统ID
            keyWord: keyWord
        },
        success:function(data,status){
        	data = JSON.parse(data);
        	console.log("未挂接数据："+data.result);
            showData(data.result);
            //currentPage = data.currentPage;
            totalLines = data.count;
        }
    });

}

//展示分页数据
function showData(data){
    $(".search_result").html("");
    
    for(var i=0; i<data.length; i++){
        var fileName = data[i].FILENAME;
        var fileID = "'"+data[i].FILEID+"'";
        console.log();
        var str = '<div class="files">' +
            '<span class="check_file" onclick="showFileInfo('+fileID+')">' +
            '<i class="iconfont icon-sousuo"></i>' +
            '</span>' +
            '<div class="hangFile">' +
            '<span class="files_title">' +
            '文件名：' +
            '</span>' +
            '<span class="files_name" title="'+fileName+'">' +
            ''+fileName+'' +
            '</span>' +
            '</div>' +
            '</div>';
        $(".search_result").append(str);
    }
}

//分页
function toPage(keyWord){
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage,
            layer = layui.layer;

        laypage.render({
            elem: 'pages',
            count: totalLines,
            groups: 4,
            limit: pageSize,
            prev: '上一页',
            next: '下一页',
            theme: '#1E9FFF',
            jump: function(obj, first){ //触发分页后的回调
                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                    $("#searchResult").text('');//先清空原先内容
                    currentPage = obj.curr;
                    getPageInfo(keyWord);
                }
            }
        });
    });
}

//根据文件ID获取文件内容
function showFileInfo(fileID){
    $.ajax({
        type: 'post',
        url: 'struts2/GetFileByIDAction.action',
        data: {
            fileID: fileID
        },
        success: function(data, textStatus){
        	data = JSON.parse(data);
        	console.log("文件信息："+data.FILEID);
        }
    })
}




