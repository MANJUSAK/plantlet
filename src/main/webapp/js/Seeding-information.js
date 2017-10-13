var result = true; //用于判断是否输入相关的值
var btn_judge = false; //判断是否为第一次点击按钮
$(function() {
	//造价信息的标题和内容
	$(".Zt-topbar li").click(function() {
		var z = $(this).index();
		$(this).addClass("Zt-active").siblings().removeClass("Zt-active");
		$(".Zc-item").eq(z).show().siblings().hide();
	});
	//苗圃信息的标题和内容
	$(".miaopu-title li").click(function() {
		var m = $(this).index();
		$(this).addClass("mt-active").siblings().removeClass("mt-active");
		$(".mc-item").eq(m).show().siblings().hide();
	});
	//弹出蒙层
	$("#showLayer").click(function() {
		var lheight = $(document).height();
		$("#SiLayer").show().css("height", lheight);
	});
	//关闭蒙层
	$("#clooseLayer").click(function() {
		$("#SiLayer").hide();
	});
	//全省
	$(".mtp-nav span").click(function(){
		$(".mt-city").slideUp();
		$(this).addClass("ci-active");
		$(".mtp-nav a").removeClass("ci-active");
	});
	//切换市
	$(".mtp-nav a").click(function() {
		$(".mt-city").slideDown();
		var a = $(this).index();
		$(this).addClass("ci-active").siblings().removeClass("ci-active");
		$(".city-item").eq(a).slideDown().siblings().hide();
	});
	
	//贵阳市县
	$(".city-item a").click(function() {
		$(this).addClass("ci-active").siblings().removeClass("ci-active");
	});

	//清空输入痕迹
	$("#clooseLayer").click(function() {
		$(".fi-tip").html("");
		document.getElementById('myform').reset();
		$("#siImg").prop("src", "../images/si-img2.jpg");
		btn_judge = false;
	});

	//控制textarea不能拖动
	$("textarea").css("resize", "none");
	/**---------- 供需信息头部的切换卡改变样式(gongxu-information.html) ----------**/
	$(".gx-title-one span").click(function(){
		$(this).siblings().removeClass("gx-title-oneSpan");
		$(this).addClass("gx-title-oneSpan");
	});
	
	/**---------- 供需信息头部的切换卡改变样式 结束 ----------**/
});
//选择图片，马上预览  上传图片
function uploadImg(obj, ID) {
	var file = obj.files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
		var qq = document.getElementById(ID);
		qq.src = e.target.result;
		//或者 img.src = this.result;  //e.target == this
	}
	reader.readAsDataURL(file);
}

//添加按钮点击事件
/*$('input[type=button]').click(function() {
	var fm = new FormData(document.getElementById("myform"));
	$.ajax({
		type: "post",
		url: "http://172.16.13.113/plantlet/plant/add/seedling.action.do",
		async: false,
		data: fm,
		processData: false,
		contentType: false,
		success: function(da) {
			console.log(da)
		},
		error: function(e) {
			console.log(e.status)
		}
	});
})*/