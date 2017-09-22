var html = '';
var htmlx = '';
var numberx = 0;
var abroadx = false;
var within = '';
$(function() {
	//省内
	var url_ = window.sessionStorage.getItem('host') + '/plantlet/nursery/province/find/seedling.action.do';
	//省外
	var url_x = window.sessionStorage.getItem('host') + '/plantlet/nursery/outside/find/seedling.action.do';
	var show_page = ['#within', '#abroad'];
	var iswithinx = ['贵阳市', '遵义市', '六盘水市', '安顺市', '毕节市', '铜仁市', '黔西南布依族苗族自治州', '黔东南苗族侗族自治州', '黔南布依族苗族自治州'];
	var isguiyang = ['清镇市', '修文县', '息烽县', '开阳县', '花溪区', '观山湖区', '云岩区', '乌当区', '小河区', '白云区', '南明区'];
	var isabroadx = ['河南省', '河北省', '湖南省', '湖北省', '山东省', '江苏省'];

	var mydata = {
		num: numberx,
		/*city:'贵阳市',
		county:'南明区'*/
	};
	getdata(url_, mydata, show_page[0], false, false);
	$('#isabroad').click(function() {
		if(!abroadx) {
			abroadx = true;
		}
		getdata(url_x, mydata, show_page[1], true, false);
	})
	$('#iswithin').click(function() {
		if(abroadx) {
			abroadx = false;
		}
		getdata(url_, mydata, show_page[0], false, false);
	})
	//省内
	$('#iswithinx a').click(function() {
		var frist = $(this).index() / 2;
		mydata.num = numberx;
		delete mydata.county;
		mydata.city = iswithinx[frist]
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
	$('#isabroadx a').click(function() {
		var index = $(this).index() / 2;
		mydata.num = numberx;
		delete mydata.county;
		delete mydata.city;
		mydata.province = isabroadx[index];
		if(!abroadx) {
			abroadx = true;
		}
		getdata(url_x, mydata, show_page[1], true, false);
	})
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
		htmlx = isabroad?'':'';
		$.ajax({
			type: "get",
			url: url_,
			data: mydata,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						//是否省外
						if(isabroad) {
							var img  = data[i]['picture']==null?'':data[i]['picture'][0];
							htmlx += '<ul class="isabroads"><li>' +
								'<div class="mt-imgs">' +
								'<div class="mti-content">' +
								'<img src="'+img+'" />' +
								'</div>' +
								'</div>' +
								'<div class="mt-right">' +
								'<div class="mtr-title">' +
								'<a href="">' + data[i]['company'] + '</a>' +
								'</div>' +
								'<div class="mtr-jianjie">' +
								'<p>' + data[i]['seedlingName'] + '</p>' +
								'</div>' +
								'</div>' +
								'</li></ul>';
						} else {
							html += '<ul class="iswithins"><li>' +
								'<div class="mt-imgs">' +
								'<div class="mti-content">' +
								'</div>' +
								'</div>' +
								'<div class="mt-right">' +
								'<div class="mtr-title">' +
								'<a href="#0">' + data[i]['nurseryName'] + '</a>' +
								'</div>' +
								'<div class="mtr-jianjie">' +
								'<p>' + data[i]['nurseryIntro'] +'范德萨范德萨发生'+ '</p>' +
								'</div>' +
								'</div>' +
								'</li></ul>';
						}

					});
					if(isabroad) {
						$(showpage).html(htmlx);
						$('.isabroads').click(function() {
							alert(0)
							var num = $(this).index();
							var id = num < 20 ? num : num / 20;
							var index = 0;
							if((num + 1) < 20) {
								index = 0;
							} else {
								index = Math.ceil((num - (num % 20)) / 20);
							}

							var test = index + ',' + id;

							window.sessionStorage.setItem('mp_detail', test);
							window.open('miaopu_details.html')
						})
					} else {
						$(showpage).html(html);
						$('.iswithins').click(function() {
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
						})
					}

				} else {
					if(mydata.num > 0) {
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
})