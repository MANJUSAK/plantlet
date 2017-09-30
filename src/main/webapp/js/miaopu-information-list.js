var html = '';
var htmlx = '';
var numberx = 0;
var abroadx = false;
var within = '';
var arr_provinces_li = null;
$(function() {
	//省内
	var url_ = window.sessionStorage.getItem('host') + '/plantlet/nursery/province/find/index/seedling.action.do';
	//省外
	var url_x = window.sessionStorage.getItem('host') + '/plantlet/nursery/outside/find/index/seedling.action.do';
	var show_page = ['#within', '#abroad'];
	var iswithinx = ['贵阳市', '遵义市', '六盘水市', '安顺市', '毕节市', '铜仁市', '黔西南布依族苗族自治州', '黔东南苗族侗族自治州', '黔南布依族苗族自治州'];
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
	})
	//点击省内
	$('#iswithin').click(function() {
		if(abroadx) {
			abroadx = false;
		}
		delete mydata.province;
		mydata.num = numberx;
		getdata(url_, mydata, show_page[0], false, false);
	})
	//省内
	$('#iswithinx a').click(function() {
		var frist = $(this).index() / 2;
		mydata.num = numberx;
		delete mydata.county;
		mydata.city = iswithinx[frist];
		if(abroadx) {
			abroadx = false;
		}
		getdata(url_, mydata, show_page[0], false, false);
	})
	//贵阳
	$('#isguiyang a').click(function() {
		var idnex = $(this).index() / 2;
		mydata.num = numberx;
		mydata.county = isguiyang[idnex];
		if(abroadx) {
			abroadx = false;
		}
		getdata(url_, mydata, show_page[0], false, false);
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
		
		$(".allCity-li ul").html(cc);
		$(".anAll ul li").html("");

		$(".clooseA").hide();
		$("#cityLi").slideUp();
		$('.allCity-li ul .provinces_li').click(function() {
			//var index = Math.ceil($(this).index() / 2);
			mydata.num = numberx;
			delete mydata.county;
			delete mydata.city;
			mydata.province = $(this).text();
			/*
			if(!abroadx) {
				abroadx = true;
			}*/
			getdata(url_x, mydata, show_page[1], true, false);
		})
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
					console.log(result)
					var data = result.data;
					$.each(data, function(i) {
						//是否省外
						if(isabroad) {
							var img = data[i]['picture'] == null ? '' : data[i]['picture'][0];
							var seedlingName = data[i]['seedlingName']==null?'无':data[i]['seedlingName'];
							htmlx += '<ul class="isabroads"><li>' +
								'<div class="mt-imgs">' +
								'<div class="mti-content">' +
								'<img src="' + img + '" />' +
								'</div>' +
								'</div>' +
								'<div class="mt-right">' +
								'<div class="mtr-title">' +
								'<a href="miaopu_details.html?key=1&nums=' + data[i]['company'] + '">' + data[i]['company'] + '</a>' +
								'</div>' +
								'<div class="mtr-jianjie">' +
								'<p>' + seedlingName + '</p>' +
								'</div>' +
								'</div>' +
								'</li></ul>';
						} else {
							var nurseryIntro = data[i]['nurseryIntro']==null?'无':data[i]['nurseryIntro'];
							html += '<ul class="iswithins"><li>' +
								'<div class="mt-imgs">' +
								'<div class="mti-content">' +
								'</div>' +
								'</div>' +
								'<div class="mt-right">' +
								'<div class="mtr-title">' +
								'<a href="miaopu_details.html?key=0&nums=' + data[i]['nurseryName'] + '">' + data[i]['nurseryName'] + '</a>' +
								'</div>' +
								'<div class="mtr-jianjie">' +
								'<p>' +  nurseryIntro + '</p>' +
								'</div>' +
								'</div>' +
								'</li></ul>';
						}
					});
					if(isabroad) {
						$(showpage).html(htmlx);
						/*$('.isabroads').click(function() {
							
							var num = $(this).index();
							var id = num < 20 ? num : num / 20;
							var index = 0;
							if((num + 1) < 20) {
								index = 0;
							} else {
								index = Math.ceil((num - (num % 20)) / 20);
							}

							var test = index + ',' + id+',';

							window.sessionStorage.setItem('mp_detail', test);
							window.open('miaopu_details.html')
						})*/
					} else {
						$(showpage).html(html);
						/*$('.iswithins').click(function() {
							var num = $(this).index();
							var id = num < 20 ? num : num / 20;
							var index = 0;
							if((num + 1) < 20) {
								index = 0;
							} else {
								index = Math.ceil((num - (num % 20)) / 20);
							}

							var test = index + ',' + id
							window.sessionStorage.setItem('mp_detail', test);
							window.open('miaopu_details.html')
						})*/
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
						$(showpage).html('非常抱歉，' + address + '暂时没此项数据');
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