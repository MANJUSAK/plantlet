/*
 * 描述：数据录入页面的正则
 * */
var needregEXP = true; //判断是否有值
var bnt_submit = false; //判断是否点击了提交按钮

//点击提交按钮
$(".IE_line_submit1").click(function() {
	needregEXP = true;
	bnt_submit = true;
	Exp_1();
});
//input框，键盘按下
$(".IE_line_R_text,.IE_line_R_textarea").keyup(function() {
	if(bnt_submit == true) {
		Exp_1();
	}
});

//遍历输入框，判断是否有值
function Exp_1() {
	$(".IE_line_R_text, .IE_line_R_textarea").each(function() {
		if($.trim($(this).val()) == '') {
			$(this).parent().parent().next().css("visibility", "visible");
			needregEXP = false;
		} else if($.trim($(this).val()) != '') {
			$(this).parent().parent().next().css("visibility", "hidden");
		}
	});
	return needregEXP;
};
//判断手机号码
/*function phone_1() {
	$(".IE_line_R_textPhone").each(function() {
		if(/^1[3|4|5|8][0-9]\d{8}$/.test($(this).val()) || /^0\d{2,3}-?\d{7}$/.test($(this).val())) {
			$(this).parent().parent().next().html('<img src="../images/err.png" />电话不能为空');
			$(this).parent().parent().next().css("visibility", "hidden");
		} else {
			$(this).parent().parent().next().html('<img src="../images/err.png" />请输入正确的电话号码');
			$(this).parent().parent().next().css("visibility", "visible");
			needregEXP = false;
		}
	});
}*/
//判断邮箱
/*function Emall_1() {
	if(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test($(".IE_line_R_textEmall").val())) {
		$(".IE_line_R_textEmall").parent().parent().next().html('<img src="../images/err.png" />邮箱不能为空');
		$(".IE_line_R_textEmall").parent().parent().next().css("visibility", "hidden");
	} else {
		$(".IE_line_R_textEmall").parent().parent().next().html('<img src="../images/err.png" />请输入正确的邮箱');
		$(".IE_line_R_textEmall").parent().parent().next().css("visibility", "visible");
		needregEXP = false;
	}
}*/
//城市三级联动
/*function sheng_shi_xian() {
	if($(".IE_line_R_select:eq(0)").val() == "省份" || $(".IE_line_R_select:eq(1)").val() == "地级市" || $(".IE_line_R_select:eq(2)").val() == "市、县级市") {
		$(".IE_line_R_select:eq(0)").parent().parent().next().css("visibility", "visible");
		needregEXP = false;
	}else{
		$(".IE_line_R_select:eq(0)").parent().parent().next().css("visibility", "hidden");
	}
}*/