$(function () {
   //弹出框封装
    $.fn.TipsWindown =  {
        openWin:function (url,name,width,height,close) {
            layer.open({
                type: 2,
                title :  [name, 'font-size:15px; background-color: #F8F8F8;'],//名称 背景颜色 字体大小
                closeBtn: close, //不显示关闭按钮
                shadeClose: false,//开启遮罩关闭
                maxmin: true,//开启最大化最小化按钮
                shade:[0.5],
                //area: ['380px', '90%'],//宽高
                area: [width, height],//宽高
                content: url,


            });

        }
    }


});
