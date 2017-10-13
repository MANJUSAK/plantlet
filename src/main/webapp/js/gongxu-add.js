$(function() {
	//http://172.16.13.113/plantlet/plant/find/supply/seedling.action.do

	/*
	 * 描述：该js为正则js。
	 * */

	var result = true; //用于判断是否输入相关的值
	var btn_judge = false; //判断是否为第一次点击按钮
	//input输入
	$(".fi-right input").keyup(function() {
		if(btn_judge == true) {
			validate();
		}
	});

	function validate() {
		result = true;
		btn_judge = true;
		//苗木名称
		var mmName = $('#mmName').val();
		//苗木企业
		var mmQiye = $('#mmQiye').val();
		//规格
		var guiGe = $('#guiGe').val();
		//价格
		var money = $("#money").val();
		//数量
		var shuLiang = $('#shuLiang').val();

		//联系方式
		var lxFangshi = $('#lxFangshi').val();

		//苗木简介
		var mmJianjie = $('#mmJianjie').val();
		//图片
		var img = $('#img').val();
		//联系人
		var lxPerson = $('#lxPerson').val();
		//供货地址
		var ghDizhi = $('#ghDizhi').val();

		//苗木名称
		mmName = $.trim(mmName);
		if(mmName == '') {
			document.getElementById('mmnameTip').innerHTML = '<img src="../images/err.png"/><font color="red">苗木名称不能为空！</font>'
			result = false;
		} else {
			document.getElementById('mmnameTip').innerHTML = '';
			//result = true;
		}

		//苗木企业
		mmQiye = $.trim(mmQiye);
		if(mmQiye == '') {
			document.getElementById('mmqiyeTip').innerHTML = '<img src="../images/err.png"/><font color="red">苗木企业不能为空！</font>'
			result = false;
		} else {
			document.getElementById('mmqiyeTip').innerHTML = '';
			//result = true;
		}
		//规格
		guiGe = $.trim(guiGe);
		if(guiGe == '') {
			document.getElementById('guigeTip').innerHTML = '<img src="../images/err.png"/><font color="red">规格不能为空！</font>'
			result = false;
		} else {
			document.getElementById('guigeTip').innerHTML = '';
			//result = true;
		}
		//规格
		money = $.trim(money);
		if(money == '') {
			document.getElementById('moneyTip').innerHTML = '<img src="../images/err.png"/><font color="red">请填写苗木价格！</font>'
			result = false;
		} else {
			document.getElementById('moneyTip').innerHTML = '';
			//result = true;
		}
		//数量
		shuLiang = $.trim(shuLiang);
		if(shuLiang == '') {
			document.getElementById('shuliangTip').innerHTML = '<img src="../images/err.png"/><font color="red">数量不能为空！</font>'
			result = false;
		} else {
			document.getElementById('shuliangTip').innerHTML = '';
			//result = true;
		}
		//联系人
		lxPerson = $.trim(lxPerson);
		if(lxPerson == '') {
			document.getElementById('lxpersonTip').innerHTML = '<img src="../images/err.png"/><font color="red">联系人不能为空！</font>'
			result = false;
		} else {
			document.getElementById('lxpersonTip').innerHTML = '';
			//result = true;
		}
		//供货地址
		ghDizhi = $.trim(ghDizhi);
		if(ghDizhi == '') {
			document.getElementById('ghdizhiTip').innerHTML = '<img src="../images/err.png"/><font color="red">供货地址不能为空！</font>'
			result = false;
		} else {
			document.getElementById('ghdizhiTip').innerHTML = '';
			//result = true;
		}
		//	}
		//联系方式
		lxFangshi = $.trim(lxFangshi);
		if(/^1[3|4|5|8][0-9]\d{8}$/.test(lxFangshi) || /^0\d{2,3}-?\d{7}$/.test(lxFangshi)) {
			//错误的提示
			document.getElementById('lxfangshiTip').innerHTML = '';
		} else {
			document.getElementById('lxfangshiTip').innerHTML = '<img src="../images/err.png"/><font color="red">请输入正确的联系电话！</font>';
			if($("#lxFangshi").val() != '') {
				$("#lxfangshiTip").html('<img src="../images/err.png"/><font color="red">请输入正确的联系电话！</font>');
			} else {
				$("#lxfangshiTip").html('<img src="../images/err.png"/><font color="red">联系方式不能为空！</font>');
			}
			result = false;
		}
		//alert("这是测试状态"+result);
		return result;
	}

	$('#sbumit').click(function() {
		validate();
		if(result == true) {
			var formx = new FormData(document.getElementById("myform"));
			$.ajax({
				type: "post",
				url: window.sessionStorage.getItem("host") + '/plantlet/plant/add/supply/seedling.action.do',
				data: formx,
				//async:true,
				processData: false,
				contentType: false,
				success: function(result) {
					if(result.errorCode == 0) {
						location = location;
					} else {
						console.log(result)
					}
				},
				error: function(e) {
					console.log(e.status)
				}
			});
		}
	})
})