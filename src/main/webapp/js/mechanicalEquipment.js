/*
 * 描述：该js页面控制的是机械设备管理系统所有页面（mechanicalEquipment.html）
 * */

/******* 控制左边导航点击时显示、隐藏子菜单、更换右边的图片 *******/
/** 自动加载时读取事假 **/
for (var i=0;i<$(".ME_nav_ul2").length;i++) {
	if($(".ME_nav_ul2:eq("+i+")").is(":visible")) {
		//改变右边的小图标
		$(".ME_nav_ul2:eq("+i+")").prev(".ME_nav_ul2_sh").children(".more").css({
			"background-image": "url(../images/more_2.png)"
		});
		//设置li下面的距离
		$(".ME_nav_ul2:eq("+i+")").parent(".ME_nav_li1").css("padding-bottom", "10px");
		//console.info(i+":"+$(".ME_nav_ul2:eq("+i+")").is(":visible"));
	} else {
		//改变右边的小图标
		$(".ME_nav_ul2:eq("+i+")").prev(".ME_nav_ul2_sh").children(".more").css({
			"background-image": "url(../images/more_1.png)"
		});
		//设置li下面的距离
		$(".ME_nav_ul2:eq("+i+")").parent(".ME_nav_li1").css("padding-bottom", "0px");
	}
	//console.info($(".ME_nav_ul2").length);
}

/** 点击事假 **/
$(".ME_nav_ul2_sh").click(function() {
	$(this).next(".ME_nav_ul2").slideToggle(400, function() {
		if($(this).is(":visible")) {
			//改变右边的小图标
			$(this).prev(".ME_nav_ul2_sh").children(".more").css({
				"background-image": "url(../images/more_2.png)"
			});
			//设置li下面的距离
			$(this).parent(".ME_nav_li1").css("padding-bottom", "10px");
		} else {
			//改变右边的小图标
			$(this).prev(".ME_nav_ul2_sh").children(".more").css({
				"background-image": "url(../images/more_1.png)"
			});
			//设置li下面的距离
			$(this).parent(".ME_nav_li1").css("padding-bottom", "0px");
		}
		//console.info($(this).parent(".ME_nav_li1"));
	});
});
/******* 控制左边导航点击时显示、隐藏子菜单、更换右边的图片  结束 *******/




/******************* 控制奇数行的背景颜色 **********************/
function odd_bg(bg_class){
	$("."+bg_class+":odd").css("background-color","#f4fbf2");
	console.log(bg_class);
};

/******************* 控制奇数行的背景颜色 **********************/

