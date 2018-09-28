$(function () {
    //doubleClick
	playerCount(1,2);
	setCurrentPlayer('1');
});

var oldVideoId;
var token;

function playerCount(firstPlayerID, lastPlayerID) {
	for (var i = firstPlayerID; i < lastPlayerID; i++) {
		//视频窗口的初始化
		init("videoPlayer" + i);
	}
}
function init(playerName) {
	videojs(playerName, {
        notSupportedMessage: '您的浏览器没有安装或开启Flash,戳我开启!',
        controls: false,// 控制条
        techOrder: ["flash"],// 兼容顺序
        errorDisplay: false,// flash 取出视频中显示的报错信息
        autoplay: true,
        controlBar: {
            //竖着的音量条
        	volumeMenuButton: {
                inline: false,
                vertical: true,
            },
            //全屏切换
            fullscreenToggle: true,
        }
    }, function () {
        console.log("--------------监听模块--------------");
        this.ready(function () {
            console.log("添加按钮");
            this.hotkeys({
            	volumnStep: 0.1,
            	seekStep: 5,
            	enableVolumeScroll: false,
            	enableModifiersForNumbers: false
            });
        });
        //监听全屏事件并修改监听title名称为中文
        this.on("fullscreenchange", function () {
            console.log("触发进出全屏事件");
        })
        this.on("playing", function () { // 鐩戝惉鎾斁
        	log("info", "正在播放！！！", true);
        });
        player.on("error", function (error) {
            console.log("有错误");
            //var $e = $("#player"
            //    + num
            //    + ".vjs-error .vjs-error-display .vjs-modal-dialog-content");
            //var $a = $(
            //    "<a href='http://www.adobe.com/go/getflashplayer' target='_blank'></a>")
            //    .text($e.text());
            //$e.empty().append($a);
            $(".vjs-error-display").remove();
            // console.error("监听到异常，错误信息：%o", error);
        });
        player.on("loadend", function () {
            log("info", "加载完成", true);
        });
        player.on("canplay", function () {
            log("info", "视频源有效", true);
        });
        player.on("canplaythrough", function () {
            log("info", "无缓冲直播", false);
        });
        player.on("durationchange", function () {
            log("info", "允许回放", true);
        });
        player.on("loadeddata", function () {
            log("info", "加载数据", true);
        });
        player.on("loadedmetadata", function () {
            log("info", "正在读取媒体数据", true);
        });
        player.on("emptied", function () {
            console.log("emptied");
        });
        player.on("ended", function () {
            console.log("ended");
        });
        player.on("suspend", function () {
            console.log("suspend");
        });
        player.on("play", function () {
            log("info", "开始播放", true);
        });
        player.on("waiting", function () {
            log("info", "请稍候", true);
        });
        player.on("abort", function () {
            console.log("abort");
        });
        player.on("loadend", function () {
            console.log("loadend");
        });
    });
}

function send(videoid) {
	var jsonStr = {"method": "play", "token":token, "oldvideoid": oldVideoId, "videoid": videoid};
	$.ajax({
		type: "post",
		url: "PlayVideoAction",
		async: true,
		cache: false,
		dataType: "json",
		data:{
			"jsondata" : JSON.stringify(jsonStr)
		},
		success: function (data, textstatus){
			if (data.method == "error") {
				oldVideoId = "";
				Alert(data.message);
			} else {
				playVideo(data.rtmpurl);
				oldVideoId = data.videoid;
			}
		},
	});
}

function setCurrentPlayer(index) {
	player = videojs("videoPlayer"+index);
}

function playVideo(videoUrl) {
   console.log(videoUrl);
   player.src(videoUrl);
   player.load(videoUrl);
   player.play();
};

function log(type, data, isDisplay) {
   var log = $("#log");
   console.log(data);
   if (isDisplay == true) {
	   switch (type) {
	   case "info":
		   log.html(data);
		   break;
	   case "error":
		   log.html("<font color='#ff0000'>" + data + "</font>");
		   break;
	   }
   }
};
