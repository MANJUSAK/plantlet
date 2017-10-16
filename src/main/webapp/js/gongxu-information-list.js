//设置初始化页码内容
var html = '';
//设置初始化页码
var numberx = 0;
//数据导出状态	true 可执行	false不可执行
var isexport = true;
$(function() {
	//设置请求路径
	var url_gx = window.sessionStorage.getItem('host') + '/plantlet/plant/find/supply/seedling.action.do';
	//设置初始请求参数
	var mydata = {
		num: numberx
	}
	//设置请求数据呈现位置
	var show_page = '.gx-content';
	//调用请求方法 请求数据并呈现到页面指定位置
	getdata(url_gx, mydata, show_page, false);

	/*加载更多
	 * 加载更多只需要把请求参数的页码值改变即可
	 */
	$('.jzm-center').click(function() {
		//请求参数 页面值加一
		mydata.num = mydata.num + 1;
		//调用请求方法							告诉方法这是一个加载更多的请求
		getdata(url_gx, mydata, show_page, true);
	})

	$('.gx-title-one span').click(function() {
		var index = $(this).index();
		switch(index) {
			case 0:
				mydata.stp != undefined ? delete mydata.stp : '';
				break;
			case 1:
				mydata.stp = 1;
				break;
			case 2:
				mydata.stp = 2;
				break;
		}
		$('#export_show').html(' ');
		getdata(url_gx, mydata, show_page, false);
	})

	/**
	 *请求数据方法 
	 * @param {Object} url_ 请求地址
	 * @param {Object} mydata	请求参数
	 * @param {Object} showpage	数据呈现位置jQuery选择方式，如：“#id”、'.class 标签名'
	 * @param {Object} ismore	是否为加载更多，true是，false否
	 */
	function getdata(url_, mydata, showpage, ismore) {
		//加载更多为false时 清空原数据
		//???????
		html = ismore ? html : '';
		$.ajax({
			type: "get",
			url: url_,
			data: mydata,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						var img = data[i]['picture'] == null ? '' : data[i]['picture'][0];
						html += '<div class="gxc-text">' +
							'<div class="gxc-left"><img src="' + img + '"/></div>' +
							'<div class="gxc-right">' +
							'<div class="gr-title">' +
							'<a href="#0">' + data[i]['sdName'] + '</a>' +
							'</div>' +
							'<div class="gr-text">' +
							'<p>' + data[i]['seedlingIntro'] + '</p>' +
							'</div>' +
							'</div>' +
							'</div>';
					});
					$(showpage).html(html);
					//
					$('.gxc-text').click(function() {
						var num = $(this).index();
						var id = num < 20 ? num : num / 20;
						var index = 0;
						if((num + 1) < 20) {
							index = 0;
						} else {
							index = Math.ceil((num - (num % 20)) / 20);
						}
						//console.log("这里的下标为："+num);
						var test = index + ',' + id;
						window.sessionStorage.setItem('gx_detail', test);
						//console.log(test)
						window.open('gongxu-details.html');
					})
				} else {
					if(ismore) {
						alert('已经没有更多数据了');
					} else {
						$(showpage).html('非常抱歉，暂时没此项数据');
					}
				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});
	}
	//文件导出
	$('#export').click(function() {
		//显示正在加载
		$(".t_div").show()
		if(!isexport) {
			alert('请求正在执行中');
			return 0;
		}
		isexport = false;
		var mydatax = {};
		mydata.stp == undefined ? '' : mydatax.stp = mydata.stp;
		$('#export_show').html(' ');
		$.ajax({
			type: "get",
			url: window.sessionStorage.getItem("host") + "/plantlet/plant/output/supply/seedling.action.do",
			async: true,
			data: mydatax,
			success: function(result) {
				if(result.errorCode == 0) {
					var time = new Date();
					$('#export_show').html('导出成功，请<a href="' + result.data + '" download>点击下载</a>');
					isexport = true;
				} else {
					$('#export_show').html('<span style="color: red;">' + result.msg + '</span>')
					isexport = true;
				}
				//隐藏正在加载
				$(".t_div").hide()
			},
			error: function(e) {
				console.log(e.status)
				isexport = true;
				//隐藏正在加载
				$(".t_div").hide()
			}
			
		});
	})
})