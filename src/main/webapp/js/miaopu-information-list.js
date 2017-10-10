var html = '';
var htmlx = '';
var numberx = 0;
var abroadx = false;
var within = '';
var arr_provinces_li = null;
var isexport = true;
$(function() {
	var host = window.sessionStorage.getItem('host');
	//省内
	var url_ = host + '/plantlet/nursery/province/find/index/seedling.action.do';
	//省外
	var url_x = host + '/plantlet/nursery/outside/find/index/seedling.action.do';
	var show_page = ['#show_province', '#show_outside'];
	var iswithinx = ['贵阳市', '遵义市', '六盘水市', '安顺市', '毕节市', '铜仁市', '黔西南自治州', '黔东南自治州', '黔南自治州'];
	var isguiyang = ['清镇市', '修文县', '息烽县', '开阳县', '花溪区', '观山湖区', '云岩区', '乌当区', '小河区', '白云区', '南明区'];
	//var isabroadx = ['河南省', '河北省', '湖南省', '湖北省', '山东省', '江苏省'];

	var mydata = {
		num: numberx,
		/*city:'贵阳市',
		county:'南明区'*/
	};
	getdata(url_, mydata, show_page[0], false, false);
	//点击省外
	$('#isabroad,#quanguo').click(function() {
		if(!abroadx) {
			abroadx = true;
		}
		mydata = {
			num: numberx,
		};
		//mydata.province = isabroadx[0];
		getdata(url_x, mydata, show_page[1], true, false);
		//清空数据
		arr_provinces_li = null;
		reset_download_();
	})
	//点击省内
	$('#iswithin,#all_withinx').click(function() {
		if(abroadx) {
			abroadx = false;
		}

		mydata = {
			num: numberx
		};
		getdata(url_, mydata, show_page[0], false, false);
		//清空数据
		arr_provinces_li = null;
		reset_download_();
	})
	//省内
	$('#iswithinx a').click(function() {
		mydata.num = numberx;
		delete mydata.county;
		mydata.city = iswithinx[$(this).index() - 1];
		if(abroadx) {
			abroadx = false;
		}
		getdata(url_, mydata, show_page[0], false, false);
		reset_download_();
	})
	//贵阳
	$('#isguiyang a').click(function() {
		var idnex = $(this).index();
		mydata.num = numberx;
		mydata.county = isguiyang[idnex];
		if(abroadx) {
			abroadx = false;
		}
		getdata(url_, mydata, show_page[0], false, false);
		reset_download_();

	})
	//省外

	//点击确认按钮再把选中省份放到展示栏；
	$("#anConfirm").click(function() {
		var cc = '';
		if(arr_provinces_li != null) {
			$.each(arr_provinces_li, function(i) {
				cc += '<li class="provinces_li" style="cursor: pointer;">' + arr_provinces_li[i] + '</li>';
			});
			$(".clooseA2").show();
		}
		//前端显示控制
		$(".allCity-li ul").html(cc);
		$(".anAll ul li").html("");
		$(".clooseA").hide();
		$("#cityLi").slideUp();
		//点击单省时查询单省数据
		$('#provinces .provinces_li').click(function() {
			mydata.num = numberx;
			delete mydata.county;
			delete mydata.city;
			mydata.province = $(this).text();
			getdata(url_x, mydata, show_page[1], true, false);
			reset_download_();

		})
		//点击确定时，查询多省数据
		if(arr_provinces_li != null) {
			var len = arr_provinces_li.length;
			var provinces = '';
			for(var i = 0; i < len; i++) {
				if(i == 0) {
					provinces += arr_provinces_li[i];
				} else {
					provinces += ',' + arr_provinces_li[i];
				}
			}
			mydata = {
				num: numberx,
				province: provinces,
			}
			getdata(url_x, mydata, show_page[1], true, false);
			reset_download_();
		}

	});
	//点击取消回复input原始状态
	$(".clooseA2").click(function() {
		$(".allCity-li ul li").html("");
		$(".An-item span input").attr({
			"disabled": false,
			"checked": false
		});
		$(this).hide();
		arr_provinces_li = null;
	});
	//加载更多
	$('.jzm-center').click(function() {
		mydata.num = mydata.num + 1;
		if(abroadx) {
			abroadx = true;
			getdata(url_x, mydata, show_page[1], abroadx, true);
		} else {
			getdata(url_, mydata, show_page[0], abroadx, true);
		}

	})
	//省内导出
	$('#Province_get button').click(function() {
		if(!isexport) {
			alert('请求正在执行中')
			return 0;
		}
		isexport = false;
		////////////////分割线
		$('#Province_get_').html(' ');
		var download_url = host + '/plantlet/nursery/province/export/excel/seedling.action.do';
		var mydatax = {};
		//设置下载省份
		mydata.province == undefined ? '' : mydatax.province = mydata.province;
		//设置下载城市
		mydata.city == undefined ? '' : mydatax.city = mydata.city;
		//设置下载区县
		mydata.county == undefined ? '' : mydatax.county = mydata.county;
		$.ajax({
			type: "get",
			url: download_url,
			data: mydatax,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var down_html = '<span style="line-height: 40px;font-size: 14px;">导出成功，请<a href="' + result.data + '">点击下载</a></span>';
					$('#Province_get_').html(down_html);
				} else {

					$('#Province_get_').html(result.msg);
				}
				isexport = true;
			},
			error: function(e) {
				console.log(e.status);
				isexport = true;
			}
		});
		
	})
	//省外导出
	$('#outside_get button').click(function() {
		if(!isexport) {
			alert('请求正在执行中')
			return 0;
		}
		isexport = false;
		///////////分割线
		$('#outside_get_').html(' ');
		var download_url = host + '/plantlet/nursery/outside/export/excel/seedling.action.do';
		var mydatax = {};
		//设置下载省份
		mydata.province == undefined ? '' : mydatax.province = mydata.province;
		//设置下载城市
		mydata.city == undefined ? '' : mydatax.city = mydata.city;
		//设置下载区县
		mydata.county == undefined ? '' : mydatax.county = mydata.county;
		$.ajax({
			type: "get",
			url: download_url,
			data: mydatax,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var down_html = '<span style="line-height: 40px;font-size: 14px;">导出成功，请<a href="' + result.data + '">点击下载</a></span>';
					$('#outside_get_').html(down_html);
				} else {

					$('#outside_get_').html('<span style="line-height: 40px;font-size: 14px;">' + result.msg + '</span>');
				}
				isexport = true;
			},
			error: function(e) {
				console.log(e.status);
				isexport = true;
			}
		});
		
	})
	//重置下载地址
	function reset_download_() {
		$('#outside_get_').html('');
		$('#Province_get_').html('');
		isexport = true;
	}
	//			请求地址		请求参数	展示位置 	是否省外	是否加载更多
	function getdata(url_, mydata, showpage, isabroad, ismore) {
		//如果ismore 为真则 HTML=HTML  反之 HTML 重置
		html = ismore ? html : '';
		htmlx = ismore ? htmlx : '';
		htmlx = isabroad ? htmlx : '';
		$.ajax({
			type: "get",
			url: url_,
			data: mydata,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						//序号
						var numx = mydata.num == 0 ? i + 1 : (i + 1) + (mydata.num * 20);
						//是否省外
						if(isabroad) {
							var province = data[i]['province'] == null ? '无' : data[i]['province'];
							var company = data[i]['company'] == null ? '无' : data[i]['company'];

							htmlx += '<tr>' +
								'<td>' + numx + '</td>' +
								'<td>' + province + '</td>' +
								'<td></td>' +
								'<td></td>' +
								'<td><a href="miaopu_details.html?key=1&nums=' + data[i]['company'] + '" target="_blank">' + company + '</a></td>' +
								'</tr>';
						} else {
							var province = data[i]['province'] == null ? '无' : data[i]['province'];
							var districts = data[i]['districts'] == null ? '无' : data[i]['districts'];
							var county = data[i]['county'] == null ? '无' : data[i]['county'];
							var nurseryName = data[i]['nurseryName'] == null ? '无' : data[i]['nurseryName'];

							html += '<tr>' +
								'<td class="mtc-td1">' + numx + '</td>' +
								'<td class="mtc-td2">' + districts + '</td>' +
								'<td class="mtc-td3">' + county + '</td>' +
								'<td class="mtc-td4"><a href="miaopu_details.html?key=0&nums=' + data[i]['nurseryName'] + '" target="_blank">' + nurseryName + '</a></td>' +
								'</tr>';
						}
					});
					if(isabroad) {
						$(showpage).html(htmlx);
					} else {
						$(showpage).html(html);
					}

				} else {
					if(mydata.num > 0) {
						alert('已经没有更多数据了');
					} else {
						var address = '';
						if(mydata.city !== undefined) {
							address += mydata.city;
						}
						if(mydata.county !== undefined) {
							address += mydata.county;
						}
						if(mydata.province !== undefined) {
							address += mydata.province;
						}
						$(showpage).html('<tr><td colspan="5">' + '非常抱歉，' + address + '暂时没此项数据' + '</td></tr>');
					}
				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});

	}
	/***************动态省份**********************/
	//苗圃省份弹出与收起
	$("#clooseCity").click(function() {
		$("#cityLi").slideDown();
		$("#quanguo").css("border-color", "#DEDEDE").children("span").css("color", "#555");
	});
	$("#anCloose").click(function() {
		$("#cityLi").slideUp();
		$(".anAll ul li").html("");
		$(".An-item span input").attr({
			"disabled": false,
			"checked": false
		});
		$(".clooseA").hide();
	});
	//点击全国，清除选中的省
	$("#quanguo").click(function() {
		$(".An-item span input:checked").attr("checked", false);
		$("#cityLi").slideUp();
		$(".anAll ul li").html("");
		$(".allCity-li ul li").html("");
		$(".clooseA2").hide();
		$(".clooseA").hide();
		$(this).css({
			"border-color": "#399d34"
		}).children("span").css("color", "#399d34");

	});
	//苗圃省份选择

	$(".An-item span input").click(function() {
		var aa = $(".An-item span input");
		var ss = "";
		var cc = '';
		arr_provinces_li = null;
		for(i = 0; i < aa.length; i++) {
			if(aa[i].checked) {
				if(ss == '') {
					ss += aa[i].value;
					cc += '<li class="provinces_li">' + aa[i].value + '</li>';
				} else {
					if(ss.split(',').length < 5) {
						ss += ',' + aa[i].value;
						cc += '<li class="provinces_li">' + aa[i].value + '</li>';
					} else {
						alert("最多只能选择5个省份");
						for(i = 0; i < aa.length; i++) {
							if(aa[i].checked) {
								this.checked = false;
								aa.attr("disabled", true);
							}
						}
					}
				}
			}
		}
		arr_provinces_li = ss.split(',');
		//选中的省份放到.anAll ul中
		$(".anAll ul").html(cc);

		if($(".anAll ul").html(cc)) {
			$(".clooseA").show();
			//$(".allCity-li ul li").html("");
		}
		//点击取消回复input原始状态
		$(".clooseA").click(function() {
			$(".anAll ul li").html("");
			$(this).hide();
			aa.attr({
				"disabled": false,
				"checked": false
			});
		});
	});

})