var html = '';
var htmlx = '';
var numberx = 0;
$(function() {
	var urlx = [window.sessionStorage.getItem('host') + '/plantlet/plant/find/offer/seedling.action.do', window.sessionStorage.getItem('host') + '/plantlet/plant/find/offer/statistics/seedling.action.do'];
	var mydata = {
		num: numberx,
		year: 2017
	};
	var show_page = ['.zj-container ul', '#show_all'];
	getdata(urlx[0], mydata, show_page[0], false, false)
	//加载更多
	$('.jzm-center').click(function() {
		var y = $('#year').val();
		var m = $('#month').val();
		mydata.num = mydata.num + 1;
		console.log(mydata)
		if(y != 0 && m == 0) {
			delete mydata.month;
			getdata(urlx[1], mydata, show_page[1], true, true);
		} else {
			getdata(urlx[0], mydata, show_page[0], true, false);
		}

	})
	//按年筛选
	$('#year').change(function() {
		var y = $(this).val();
		var m = $('#month').val();
		if(y == 0) {
			mydata.num = numberx;
			delete mydata.year;
			getdata(urlx[0], mydata, show_page[0], false, false)
			$("#zjFenqi").show();
			$("#zaojiaAll").hide();
		} else {
			if(m == 0) {
				$("#zjFenqi").hide();
				$("#zaojiaAll").show();
				//查看所有
				delete mydata.month;
				mydata.num = numberx;
				mydata.year = y;
				getdata(urlx[1], mydata, show_page[1], false, true);
			} else {
				$("#zjFenqi").show();
				$("#zaojiaAll").hide();
				eval('mydata.year=' + y);
				mydata.num = numberx;
				getdata(urlx[0], mydata, show_page[0], false, false);
			}

		}

	})
	//按年月/期筛选
	$('#month').change(function() {
		var m = $(this).val();
		var year = $('#year').val();
		if(m == 0) {
			mydata.num = numberx;
			delete mydata.month;
			mydata.year = year;
			//查看所有
			$("#zjFenqi").hide();
			$("#zaojiaAll").show();
			getdata(urlx[1], mydata, show_page[1], false, true)

		} else {
			eval('mydata.month=' + m);
			mydata.num = numberx;
			$("#zjFenqi").show();
			$("#zaojiaAll").hide();
			getdata(urlx[0], mydata, show_page[0], false, false);
		}

	})

	function getdata(urlx, mydata, show_page, isMore, isall) {
		html = isMore ? html : '';
		htmlx = isMore ? htmlx : '';
		$.ajax({
			type: "get",
			cache: false,
			url: urlx,
			async: true,
			data: mydata,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						if(isall) {
							var numx = mydata.num == 0 ? i + 1 : (i + 1) + (mydata.num * 20);
							data[i]['jan'] = data[i]['jan'] == 0 ? ' ' : data[i]['jan'];
							data[i]['feb'] = data[i]['feb'] == 0 ? ' ' : data[i]['feb'];
							data[i]['mar'] = data[i]['mar'] == 0 ? ' ' : data[i]['mar'];
							data[i]['apr'] = data[i]['apr'] == 0 ? ' ' : data[i]['apr'];
							data[i]['may'] = data[i]['may'] == 0 ? ' ' : data[i]['may'];
							data[i]['jun'] = data[i]['jun'] == 0 ? ' ' : data[i]['jun'];
							data[i]['jul'] = data[i]['jul'] == 0 ? ' ' : data[i]['jul'];
							data[i]['aug'] = data[i]['aug'] == 0 ? ' ' : data[i]['aug'];
							data[i]['sep'] = data[i]['sep'] == 0 ? ' ' : data[i]['sep'];
							data[i]['oct'] = data[i]['oct'] == 0 ? ' ' : data[i]['oct'];
							data[i]['nov'] = data[i]['nov'] == 0 ? ' ' : data[i]['nov'];
							data[i]['dec'] = data[i]['dec'] == 0 ? ' ' : data[i]['dec'];

							htmlx += '<tr class="NS_tr_content">' +
								'<td>' + numx + '</td>' +
								'<td>' + data[i]['sdName'] + '</td>' +
								'<td>' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</td>' +
								'<td>' + data[i]['unit'] + '</td>' +
								'<td>' + data[i]['jan'] + '</td>' +
								'<td>' + data[i]['feb'] + '</td>' +
								'<td>' + data[i]['mar'] + '</td>' +
								'<td>' + data[i]['apr'] + '</td>' +
								'<td>' + data[i]['may'] + '</td>' +
								'<td>' + data[i]['jun'] + '</td>' +
								'<td>' + data[i]['jul'] + '</td>' +
								'<td>' + data[i]['aug'] + '</td>' +
								'<td>' + data[i]['sep'] + '</td>' +
								'<td>' + data[i]['oct'] + '</td>' +
								'<td>' + data[i]['nov'] + '</td>' +
								'<td>' + data[i]['dec'] + '</td>' +
								'</tr>';
						} else {
							data[i]['comment'] = data[i]['comment'] == null ? '无' : data[i]['comment'];
							var numx = mydata.num == 0 ? i + 1 : (i + 1) + (mydata.num * 20);
							html += '<li class="zj_detailx">' +
								'<div class="zj-item">' + numx + '</div>' +
								'<div class="zj-item">' + data[i]['sdName'] + '</div>' +
								'<div class="zj-item">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</div>' +
								'<div class="zj-item">' + data[i]['unit'] + '</div>' +
								'<div class="zj-item">' + data[i]['sdOffer'] + '</div>' +
								'<div class="zj-item">' + data[i]['comment'] + '</div>' +
								'</li>';
						}
					});

					if(isall) {
						$('#show_all_year').html(data[0]['year']);
						$(show_page).html(htmlx);
					} else {
						$(show_page).html(html);
					}
					//详情
					/*$('.zj_detailx').click(function() {
						window.sessionStorage.setItem('zj_detail',JSON.stringify(data[$(this).index() - (mydata.num * 20)]));
					})*/
				} else {
					if(mydata.num > 0) {
						alert('已经没有更多数据了')
					} else {
						if(isall) {
							$('#show_all_year').html('----');
						}
						$(show_page).html('非常抱歉，暂时没此项数据');
					}
				}
			},
			error: function() {

			}
		});
	}

})