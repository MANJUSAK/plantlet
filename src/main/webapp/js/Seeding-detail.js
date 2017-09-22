var html = '';
var htmlx = '';
$(function() {
	var para = window.sessionStorage.getItem('seeding_detail');
	var num_arr = para.split(',');
	console.log(num_arr)
	var url_ = window.sessionStorage.getItem('host') + '/plantlet/plant/find/detail/statistics/seedling.action.do';
	var mydata = {
		num: num_arr[0],
		keyWord:num_arr[2],
	};
	var mydatax = {
		num: num_arr[0],
		keyWord:num_arr[2],
	};
	var show_page = ['#show_page_n', '#show_page_w'];

	$('#shi li').click(function() {

		mydata.city = $(this).text();

		getdata(url_, mydata, show_page[0], true, false);
	})
	$('#xianqu li').click(function() {
		mydata.city = "贵阳市";
		mydata.county = $(this).text();

		getdata(url_, mydata, show_page[0], true, false);
	})
	getdata(url_, mydata, show_page[0], true, false);
	$('#MMc_tbL').click(function() {
		getdata(url_, mydata, show_page[0], true, false);
	})
	$('#more1').click(function() {
		mydata.num = mydata.num + 1;
		getdata(url_, mydata, show_page[0], false, true);
	})

	$('#MMc_tbR').click(function() {
		getdata(url_, mydatax, show_page[1], false, false);
	})
	$('#more2').click(function() {
		mydatax.num = mydatax.num + 1;
		getdata(url_, mydatax, show_page[1], true, true);
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
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						if(!isn) { //省外
							htmlx += '<tr class="MMcPc_table1_tr2">' +
								'<td class="MMcPc_teb1_td1">' + data[i]['compOut'] + '</td>' +
								'<td class="MMcPc_teb1_td2">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</td>' +
								'<td class="MMcPc_teb1_td3">' + data[i]['numOut'] + '</td>' +
								'<td class="MMcPc_teb1_td4">' + data[i]['marketPrice'] + '</td>' +
								'</tr>';
						} else { //省内
							html += '<tr class="MMcPc_table1_tr2">' +
								'<td class="MMcPc_teb1_td1">' + data[i]['comp'] + '</td>' +
								'<td class="MMcPc_teb1_td2">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '（' + data[i]['unit'] + '）</td>' +
								'<td class="MMcPc_teb1_td3">' + data[i]['num'] + '</td>' +
								'<td class="MMcPc_teb1_td4">' + data[i]['marketPrice'] + '</td>' +
								'</tr>';
						}
					});
					$('#seeding_detail').html(data[0]['sdName']);
					if(!isn) {
						$(showpage).html(htmlx);
					} else {
						$(showpage).html(html);
					}
				} else {
					if(mydata.num > 0) {
						alert('已经没有更多数据了')
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