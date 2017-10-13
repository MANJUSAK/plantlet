var html = '';
var htmlx = '';
var numberx = 0;//初始化请求页码
var isexport = true;//设置导出文件执行状态
$(function() {
	//获取统一请求地址
	var host = window.sessionStorage.getItem("host");
	//拼接请求路径
	var urlx = [host + '/plantlet/plant/find/offer/seedling.action.do', host + '/plantlet/plant/find/offer/statistics/seedling.action.do'];
	//设置请求参数
	var mydata = {
		num: numberx,
		year: 2017
	};
	//设置呈现位置参数
	var show_page = ['.zj-container ul', '#show_all'];
	//请求数据
	getdata(urlx[1], mydata, show_page[1], false, true);
	//更改下级菜单样式
	$('#show_default').css('border', '1px solid #1F9952');
	$('#show_default').css('color', '#1F9952');
	//加载更多
	$('.jzm-center').click(function() {
		var y = $('#year').val();
		var m = $('#month').val();
		mydata.num = mydata.num + 1;
		//根据筛选参数控制请求信息
		if(y != 0 && m == 0 || y == 0 && m == 0) {
		//M 表示月份  移除月份
			delete mydata.month;
			if(y == 0 && m == 0) {
				//0表示所有
				$('#year_all').show();
				//请求所以数据
				getdata(urlx[1], mydata, show_page[1], true, true);
			} else {
				$('#year_all').hide();
				//请求某一年的全部数据
				getdata(urlx[1], mydata, show_page[1], true, true);
			}
		} else {
			$('#year_all').hide();
			//按期请求数据
			getdata(urlx[0], mydata, show_page[0], true, false);
		}

	})
	//按年筛选
	$('#year').change(function() {
		var y = $(this).val();//年份
		var m = $('#month').val();//月份
		if(y == 0 && m == 0) {
			mydata = {
				num: numberx,
			};
			$('#year_all span').css('border', '1px solid transparent');
			$('#year_all span').css('color', '#000000');
			$('#show_default').css('border', '1px solid #1F9952');
			$('#show_default').css('color', '#1F9952');
			getdata(urlx[1], mydata, show_page[1], false, true);

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
		//重置样式
		$('#year_all span').css({
			'border': '1px solid transparent',
			'color': '#000000'
		});
		//根据index参数来控制前端样式
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
	/**
	 * 
	 * @param {Object} urlx		请求数据地址
	 * @param {Object} mydata	请求参数
	 * @param {Object} show_page 呈现位置，格式为：'#id'、'.class'、'#id .class 标签名'等，即为jQuery的选择方式
	 * @param {Object} isMore	是否为加载更多（即换页	true or false
	 * @param {Object} isall	是否为显示所有	true or false
	 */
	function getdata(urlx, mydata, show_page, isMore, isall) {
		//判断是否为加载更多，是则保留上一次的值，反之清空
		html = isMore ? html : '';
		htmlx = isMore ? htmlx : '';
		//ajax请求
		$.ajax({
			type: "get",
			cache: false,
			url: urlx,
			async: true,
			data: mydata,
			success: function(result) {
				//判断是否 为正确数据
				if(result.errorCode == 0) {
					//获取数据集合
					var data = result.data;
					//循环数据
					$.each(data, function(i) {
						//规格拼接
						var specs = Number(data[i]['specMax']) == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];

						if(isall) {
							//生成序号
							var numx = mydata.num == 0 ? i + 1 : (i + 1) + (mydata.num * 20);
							//设定月份  排除显示为0 的 
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
							//修正 显示的值
							data[i]['comment'] = data[i]['comment'] == null ? '无' : data[i]['comment'];
							//生成序号
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
					//根据 isall 参数 判断是否为显示全部 调用不同的 返回结果	html 为 按期 显示	htmlx 为显示所有
					if(isall) {
						//如未设置年份参数 则默认为2017 即当前年份
						if(mydata.year == undefined) {
							$('#show_all_year').html('2017年份');
						} else {
							$('#show_all_year').html(mydata.year + '年份');
						}
						//htmlx 为显示所有
						$(show_page).html(htmlx);
					} else {
						//html 为 按期 显示
						$(show_page).html(html);
					}
				} else {
					//根据 num 值判断是否为第一页
					if(mydata.num > 0) {
						alert('已经没有更多数据了')
					} else {
						//判断是否为显示全部		是则更新年份设置
						if(isall) {
							$('#show_all_year').html(mydata.year + '年份');
						}
						//提示 该年没有数据
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
		//重置下载地址显示
		$('#export_show').html(' ');
		//声明 datax 对象  用作请求参数
		var datax = {};
		//跟据 页码数据的请求参数设置  数据导出参数
		datax.year = mydata.year == undefined ? 2017 : mydata.year;
		mydata.month == undefined ? '' : datax.month = mydata.month;
		//设置 请求url					分期导出 接口															导出全部接口
		var url_ = [host + '/plantlet/plant/output/offer/seedling.action.do', host + '/plantlet/plant/output/offer/statistics/seedling.action.do'];
		//如果月份未设置 则 导出全部
		if(mydata.month == undefined) {
			exportData(url_[1], null, '#export_show');
		} else {
			exportData(url_[0], datax, '#export_show');
		}
	})
	//导出数据方法（文件
	/**
	 * 
	 * @param {Object} url_	请求地址	
	 * @param {Object} mydatax	请求参数
	 * @param {Object} htm	请求数据显示位置
	 */
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