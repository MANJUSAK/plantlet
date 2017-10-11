var html = '';
var htmlx = '';
var numberx = 0;
var isexport = true;
$(function() {
	var host = window.sessionStorage.getItem("host");
	var urlx = [host + '/plantlet/plant/find/offer/seedling.action.do', host + '/plantlet/plant/find/offer/statistics/seedling.action.do'];
	var mydata = {
		num: numberx,
		year: 2017
	};
	var show_page = ['.zj-container ul', '#show_all'];
	getdata(urlx[1], mydata, show_page[1], false, true);
	//更改下级菜单样式
	$('#show_default').css('border', '1px solid #1F9952');
	$('#show_default').css('color', '#1F9952');
	//加载更多
	$('.jzm-center').click(function() {
		var y = $('#year').val();
		var m = $('#month').val();
		mydata.num = mydata.num + 1;

		if(y != 0 && m == 0 || y == 0 && m == 0) {
			delete mydata.month;
			if(y == 0 && m == 0) {
				$('#year_all').show();
				getdata(urlx[1], mydata, show_page[1], true, true);
			} else {
				$('#year_all').hide();
				getdata(urlx[1], mydata, show_page[1], true, true);
			}
		} else {
			$('#year_all').hide();
			getdata(urlx[0], mydata, show_page[0], true, false);
		}

	})
	//按年筛选
	$('#year').change(function() {
		var y = $(this).val();
		var m = $('#month').val();
		if(y == 0 && m == 0) {
			mydata = {
				num: numberx,
			};
			$('#year_all span').css('border', '1px solid transparent');
			$('#year_all span').css('color', '#000000');
			$('#show_default').css('border', '1px solid #1F9952');
			$('#show_default').css('color', '#1F9952');
			getdata(urlx[1], mydata, show_page[1], false, true);
			//getdata(urlx[0], mydata, show_page[0], false, false)

			$("#zjFenqi").hide();
			$("#zaojiaAll").show();
			$('#year_all').show();
			//更改下级菜单样式
			$('#year_all span').css({
				'border': '1px solid transparent',
				'color': '#000000'
			});
		} else if(y == 0 && Number(m) != 0) {
			$('#year_all').hide();

			$('#year_all').hide();
			$("#zjFenqi").show();
			$("#zaojiaAll").hide();
			//查看所有
			mydata = {
				num: numberx,
				month: m,
			};
			getdata(urlx[0], mydata, show_page[0], false, false)

		} else if(Number(y) != 0 && Number(m) == 0) {
			$('#year_all').hide();

			$("#zjFenqi").hide();
			$("#zaojiaAll").show();
			//查看所有
			mydata = {
				num: numberx,
				year: y
			};
			getdata(urlx[1], mydata, show_page[1], false, true);

		} else {
			$('#year_all').hide();

			$("#zjFenqi").show();
			$("#zaojiaAll").hide();
			mydata = {
				num: numberx,
				year: y,
				month: m,
			};
			getdata(urlx[0], mydata, show_page[0], false, false);

		}

	})
	//按 月/期筛选
	$('#month').change(function() {
		var m = $(this).val();
		var year = $('#year').val();
		//年月皆为零时，查询所有，此类型数据
		if(m == 0 && year == 0) {
			mydata = {
				num: numberx,
			};
			$('#year_all span').css('border', '1px solid transparent');
			$('#year_all span').css('color', '#000000');
			getdata(urlx[1], mydata, show_page[1], false, true);
			//前端页面控制
			$("#zjFenqi").hide();
			$("#zaojiaAll").show();
			$('#year_all').show();

		} else if(m == 0 && Number(year) != 0) {
			//仅月份为全部时，按年查询此类数据，
			$('#year_all').hide();
			mydata = {
				num: numberx,
				year: year
			};
			getdata(urlx[1], mydata, show_page[1], false, true);
			//前端页面控制
			$("#zjFenqi").hide();
			$("#zaojiaAll").show();

		} else if(m != 0 && year == 0) {
			$('#year_all').hide();
			eval('mydata.month=' + m);
			mydata = {
				num: numberx,
				month: m,
			};
			getdata(urlx[0], mydata, show_page[0], false, false);
			$("#zjFenqi").show();
			$("#zaojiaAll").hide();

		} else {
			$('#year_all').hide();
			eval('mydata.month=' + m);
			mydata = {
				num: numberx,
				year: year,
				month: m
			};
			getdata(urlx[0], mydata, show_page[0], false, false);
			$("#zjFenqi").show();
			$("#zaojiaAll").hide();

		}

	})
	//选择全部时 显示二级菜单（作分页功能
	//此应该动态获取服务器数据来动态生成的
	$('#year_all span').click(function() {
		var index = $(this).index();
		var selects = document.getElementById("year");
		//更改样式
		$('#year_all span').css({
			'border': '1px solid transparent',
			'color': '#000000'
		});
		switch(index) {
			case 0:
				mydata = {
					num: numberx,
					year: 2015
				};
				selects[1].selected = true;
				getdata(urlx[1], mydata, show_page[1], false, true);
				$(this).css({
					'border': '1px solid #1F9952',
					'color': '#1F9952'
				});
				break;
			case 1:
				mydata = {
					num: numberx,
					year: 2016
				};
				selects[2].selected = true;
				getdata(urlx[1], mydata, show_page[1], false, true);
				$(this).css({
					'border': '1px solid #1F9952',
					'color': '#1F9952'
				});
				break;
			case 2:
				mydata = {
					num: numberx,
					year: 2017
				};
				selects[3].selected = true;
				getdata(urlx[1], mydata, show_page[1], false, true);
				$(this).css({
					'border': '1px solid #1F9952',
					'color': '#1F9952'
				});
				break;
		}

	});

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
						var specs = Number(data[i]['specMax']) == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];

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
								'<td>' + specs + '</td>' +
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
								'<div class="zj-item">' + specs + '</div>' +
								'<div class="zj-item">' + data[i]['unit'] + '</div>' +
								'<div class="zj-item">' + data[i]['sdOffer'] + '</div>' +
								'<div class="zj-item">' + data[i]['comment'] + '</div>' +
								'</li>';
						}
					});

					if(isall) {
						if(mydata.year == undefined) {
							$('#show_all_year').html('2017年份');
						} else {
							$('#show_all_year').html(mydata.year + '年份');
						}
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
							$('#show_all_year').html(mydata.year + '年份');
						}
						$(show_page).html('<tr><td colspan="20" style="padding: 8px 5px;box-sizing: border-box;">' + '非常抱歉，暂时没此项数据' + '</td></tr>');
					}
				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});
	}

	//数据导出
	$('#export').click(function() {
		if(!isexport) {
			alert('请求正在执行中')
			return 0;
		}
		isexport = false;
		///////////////////分割线
		$('#export_show').html(' ');
		var datax = {};
		datax.year = mydata.year == undefined ? 2017 : mydata.year;
		mydata.month == undefined ? '' : datax.month = mydata.month;
		var url_ = [host + '/plantlet/plant/output/offer/seedling.action.do', host + '/plantlet/plant/output/offer/statistics/seedling.action.do'];
		if(mydata.month == undefined) {
			exportData(url_[1], null, '#export_show');
		} else {
			exportData(url_[0], datax, '#export_show');
		}
	})

	function exportData(url_, mydatax, htm) {
		$.ajax({
			type: "get",
			url: url_,
			async: true,
			data: mydatax,
			success: function(result) {
				if(result.errorCode == 0) {
					$(htm).html('<span>导出成功，请</span><a href="' + result.data + '">点击下载</a>');
				} else {
					$(htm).html('<span style="color: red;">'+result.msg+'</span>');
				}
				isexport = true;
			},
			error: function(e) {
				console.log(e.status)
				isexport = true;
			}
		});

	}
})