var html = '';
var htmlx = '';
var numberx = 0;
$(function() {
	//var para = window.sessionStorage.getItem('seeding_detail');
	var url_host = window.sessionStorage.getItem('host');
	//var urls = [url_host + '/plantlet/nursery/province/find/seedling.action.do', url_host + '/plantlet/nursery/outside/find/seedling.action.do'];
	var urls = [url_host + '/plantlet/nursery/province/find/seedling.action.do', url_host + '/plantlet/nursery/outside/find/index/seedling.action.do'];
	var isall = GetQueryString('isall');
	var mydata = {
		num: numberx,
		keyWord: GetQueryString('sdName'),
		spec: GetQueryString('spec'),
		specMin: GetQueryString('specMin'),
		specMax: GetQueryString('specMax')
	};
	var mydatax = {
		num: numberx,
		keyWord: GetQueryString('sdName'),
		spec: GetQueryString('spec'),
		specMin: GetQueryString('specMin'),
		specMax: GetQueryString('specMax')
	};

	var show_page = ['#show_page_n', '#show_page_w'];
	//第一次进入，默认
	if(isall == 2) {//省内
		getdata(urls[0], mydata, show_page[0], false, false);
	} else if(isall == 3) {//省外
		getdata(urls[1], mydata, show_page[1], true, false);
	} else if(isall == 0) {//全部
		getdata(urls[0], mydata, show_page[0], false, false);
	}
	//省内	市
	$('#shi li').click(function() {
		mydata.city = $(this).text();
		mydata.num = numberx;
		getdata(urls[0], mydata, show_page[0], false, false);
	})
	//贵阳市内	区县
	$('#xianqu li').click(function() {
		mydata.city = "贵阳市";
		mydata.county = $(this).text();
		mydata.num = numberx;
		getdata(urls[0], mydata, show_page[0], false, false);
	})

	$('#MMc_tbL').click(function() {
		mydata.num = numberx;
		getdata(urls[0], mydata, show_page[0], false, false);
	})
	$('#more1').click(function() {
		mydata.num = mydata.num + 1;
		getdata(urls[0], mydata, show_page[0], false, true);
	})
	//省外
	$('#MMc_tbR').click(function() {
		mydata.num = numberx;
		getdata(urls[1], mydatax, show_page[1], true, false);
	})
	$('#more2').click(function() {
		mydatax.num = mydatax.num + 1;
		getdata(urls[1], mydatax, show_page[1], true, true);
	})

	function getdata(url_, mydata, showpage, isn, ismore) {
		html = ismore ? html : '';
		htmlx = ismore ? htmlx : '';
		$.ajax({
			type: "get",
			url: url_,
			data: mydata,
			async: true,
			success: function(result) {
				console.log(result);
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						if(isn) { //省外
							htmlx += '<tr class="MMcPc_table1_tr2">' +
								'<td class="MMcPc_teb1_td1">' + data[i]['company'] + '</td>' +
								'<td class="MMcPc_teb1_td2">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</td>' +
								'<td class="MMcPc_teb1_td3">' + data[i]['num'] + '</td>' +
								'<td class="MMcPc_teb1_td4">' + data[i]['price'] + '(元)/' + data[i]['unit'] + '</td>' +
								'</tr>';
						} else { //省内
							html += '<tr class="MMcPc_table1_tr2">' +
								'<td class="MMcPc_teb1_td1">' + data[i]['nurseryName'] + '</td>' +
								'<td class="MMcPc_teb1_td2">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</td>' +
								'<td class="MMcPc_teb1_td3">' + data[i]['num'] + '</td>' +
								'<td class="MMcPc_teb1_td4">' + data[i]['price']  + '</td>' +
								'</tr>';
						}
					});
					$('#seeding_detail').html(data[0]['plantName']);
					if(isn) {
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
						$(showpage).html('非常抱歉，' + address + '暂时没此项数据');
					}
				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});
	}
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return decodeURI(r[2]);
		return null;
	}
})