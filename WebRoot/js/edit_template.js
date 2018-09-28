$(function () {
  $('.anchors').hover(function () {
    $(this).css('opacity', '1');

  },function () {
    $(this).css('opacity', '0.7');
  });
  $(document).on('click','.anchors',function () {
    /*跳到顶部锚点*/
    $("html,body").animate({scrollTop: $('#top-anchors').offset().top}, 1000);
  });
  /*显示删除*/
  $(document).on("click", ".data-num", function () {
    $('.data-num .td-temp-del .temp-del').hide();
    $(this).find('.td-temp-del').find('.temp-del').show();
  });
  $(window).scroll(function () {
    // var top1 = $('#data-create').scrollTop();
    var sTop = $(window).scrollTop();
    // alert("top" + top1);
    // var top1 = $(document).scrollTop()
    var sTop = parseInt(sTop);
    // console.log("dom:" + sTop);
    if (sTop >= 80) {
      // console.log("导航栏动画");
      $(".common_header2").stop().animate({
        // top: '0px',
        // left: '0px',
        // right: '0px',
        // paddingLeft: '5%',
        // paddingRight: '5%',
        width: '70%',
        // left: 0,
        // right: 0,
        // top: 0,
        height: '100px',

      }, 'fast');
      $(".common_header2").addClass("nav-animate");
      $(".tab-nav").css("margin-top", "35px");
      $(".nav-input-css").css("margin-top", "-8px");
      $(".nav-li-left").css("margin-left", "350px");
      // $('.common_display').removeClass('common_display');
      $('.common_display').css('display', 'block');
      $('#top-bg').css('display', 'block');
/*.css('height', '100px')*/

    } else {
      $(".common_header2").stop().animate({
        // marginLeft: '2.5%',
        // marginTop: '45px',
        // paddingLeft: 0,
        // paddingRight: 0,
        width: '60%',
        height: '45px',
      }, 'fast');
      $(".common_header2").removeClass("nav-animate");
      $(".tab-nav").css("margin-top", "0");
      $(".nav-input-css").css("margin-top", "0");
      $(".nav-li-left").css("margin-left", "200px");
      // $('.common_display').addClass('common_display');
      $('.common_display').css('display', 'none');
      $('#top-bg').css('display', 'none');
    }
  });
});
