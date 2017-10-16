var html = ''; //全局定义呈现字符串
var numberx = 0; //初始化请求页码
var isexport = true; //设置导出执行状态
$(function() {
	//数据列表请求地址
	var url_ = window.sessionStorage.getItem('host') + '/plantlet/plant/find/statistics/seedling.action.do';
	//苗木名称请求地址
	var sdname_url = window.sessionStorage.getItem('host') + '/plantlet/plant/find/all/sd_name/seedling.action.do';
	var show_page = '.sx-fors';
	var mydata = {
		num: numberx
	};
	getsdname(sdname_url);
	getdata(url_, mydata, show_page, false);

	//名称条件筛选
	function getsdname(urls) {
		var htm = '';
		$.ajax({
			type: "get",
			url: urls,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					htm += '<select id="sdnamex" name="sdName">';
					htm += '<option value="0">全部</option>';
					$.each(data, function(i) {
						htm += '<option value="' + data[i] + '">' + data[i] + '</option>';
					});
					htm += '</select>';
					$('#show_sdname').html(htm);
					/*
					 * 选择苗木名称时，根据筛选参数请求数据
					 * 当获取的筛选参数为空时，将不使用该参数
					 */
					$('#sdnamex').change(function() {
						var name = $('select[name=sdName]').val(); //苗木名称
						mydata.num = numberx;
						if(Number(name) == 0) {
							delete mydata.keyWord;
						} else {
							mydata.keyWord = name;
						}
						var spec = $('select[name=spec]').val(); //苗木规格
						if(Number(spec) == 0) {
							delete mydata.spec;
						} else {
							mydata.spec = spec;
						}
						getdata(url_, mydata, show_page, false);
						$('#export_show').html(' ');
					})
				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});

	}
	//规格条件筛选
	$('select[name="spec"]').change(function() {
		if(Number($(this).val()) == 0) {
			mydata = {
				num: numberx
			};
			$('input[name=specMin]').val(null);
			$('input[name=specMax]').val(null);
			var name = $('select[name=sdName]').val();
			if(Number(name) == 0) {
				delete mydata.keyWord;
			} else {
				mydata.keyWord = name;
			}
			getdata(url_, mydata, show_page, false);
		} else {

			var spec = $('select[name=spec]').val();
			mydata.spec = spec;
			mydata.num = numberx;
			getdata(url_, mydata, show_page, false);

		}
		$('#export_show').html(' ');
	})
	//苗木规格	最小规格
	$('input[name="specMin"]').keyup(function() {
		var spec = $('select[name=spec]').val();
		var specmin = $('input[name=specMin]').val();
		var specmax = $('input[name=specMax]').val();
		var name = $('select[name=sdName]').val();
		//当获取到的值不符合规则时，抛弃该属性
		name == 0 ? delete mydata.keyWord : mydata.keyWord = name;
		specmax == '' || specmax == null ? delete mydata.specMax : '';
		specmin == 0 || specmin == null ? delete mydata.specMin : '';
		mydata.num = numberx;

		if(specmin != null && specmin != '') {
			mydata.spec = spec;
			mydata.specMin = specmin;
		}
		if(specmax != null && specmax != '') {
			mydata.specMax = specmax;
		}

		if(mydata.specMax == undefined) {
			//最大值为空时
			if(Number(spec) == 0) {
				alert('请选择正确的规格！')
			} else {
				getdata(url_, mydata, show_page, false);
			}

		} else {
			//最大值不为空,则最大值大于最小值时才请求数据
			if(Number(mydata.specMax) > Number(mydata.specMin)) {
				getdata(url_, mydata, show_page, false);
			}
		}
		$('#export_show').html(' ');
	})
	//最大规格
	$('input[name="specMax"]').keyup(function() {
		var spec = $('select[name=spec]').val();
		var specmin = $('input[name=specMin]').val();
		var specmax = $('input[name=specMax]').val();
		var name = $('select[name=sdName]').val();
		//当获取到的值不符合规则时，抛弃该属性
		name == 0 ? delete mydata.keyWord : mydata.keyWord = name;
		specmax == '' || specmax == null ? delete mydata.specMax : '';
		specmin == 0 || specmin == null ? delete mydata.specMin : '';
		mydata.num = numberx;
		//设置筛选条件
		if(specmin != null && specmin != '') {
			mydata.specMin = specmin;
		}
		if(specmax != null && specmax != '') {
			mydata.spec = spec;
			mydata.specMax = specmax;
		}
		if(mydata.specMin == undefined) {
			//最小值为空时
			if(spec == 0) {
				alert('请选择正确的规格！')
			} else {
				getdata(url_, mydata, show_page, false);
			}
		} else {
			//最小值不为空,则最小值小于最大值时才请求数据
			if(Number(mydata.specMax) > Number(mydata.specMin)) {
				if(Number(spec) == 0) {
					alert('请选择正确的规格！')
				} else {
					getdata(url_, mydata, show_page, false);
				}
			}
		}
	})
	//加载更多
	$('.jzm-center').click(function() {
		mydata.num = mydata.num + 1;
		getdata(url_, mydata, show_page, true);
	})
	/**
	 * @param {Object} url_	请求地址
	 * @param {Object} mydata	请求参数
	 * @param {Object} show_page	呈现位置
	 * @param {Object} isMore	是否为加载更多
	 */
	function getdata(url_, mydata, show_page, isMore) {
		html = isMore ? html : '';
		$.ajax({
			type: "get",
			url: url_,
			data: mydata,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						data[i]['comment'] = data[i]['comment'] == null ? '' : data[i]['comment'];
						var price = data[i]['maxPrice'] == 0 ? '' : data[i]['maxPrice'] == data[i]['maxPrice'] ? data[i]['maxPrice'] : data[i]['maxPrice'] + '-' + data[i]['maxPrice'];
						var priceOut = data[i]['maxPrice'] == 0 ? '' : data[i]['maxPrice'] == data[i]['maxPrice'] ? data[i]['maxPrice'] : data[i]['maxPrice'] + '-' + data[i]['maxPrice'];
						//省内最小价格
						var minPrice = data[i]['minPrice'] == 0 ? '' : data[i]['minPrice'] == data[i]['minPrice'] ? data[i]['minPrice'] : data[i]['minPrice'] + '-' + data[i]['minPrice'];
						//省外最小价格
						var minPriceOut = data[i]['minPrice'] == 0 ? '' : data[i]['minPrice'] == data[i]['minPrice'] ? data[i]['minPrice'] : data[i]['minPrice'] + '-' + data[i]['minPrice'];

						var marketPrice = data[i]['marketPrice'] == 0 ? '' : data[i]['marketPrice'] + '';
						var specs = Number(data[i]['specMax']) == 0 ? data[i]['spec'] + data[i]['specMin'] : data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'];
						var numOut = data[i]['numOut'] == 0 ? '' : data[i]['numOut'];
						var num = data[i]['num'] == 0 ? '' : data[i]['num'];
						var offer = data[i]['offer'] == 0 ? '' : data[i]['offer'];
						html += '<ul class="Seed_detail">' +
							'<li class="sx-mc"><a target="_blank" href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=0">' +
							'' + data[i]['sdName'] + '</a></li>' +
							'<li class="sx-mc">' + specs + '</li>' +
							'<li class="sx-jg">' +
							'<div class="jg-li">' + offer + '</div>' +
							'<div class="jg-li"><a target="_blank" href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=2">' +
							'' + minPrice + '-' + price + '</a></div>' +
							'<div class="jg-li"><a target="_blank" href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=3">' +
							'' + minPriceOut + '-' + priceOut + '</a></div>' +
							'<div class="jg-li">' + marketPrice + '</div>' +
							'</li>' +
							'<li class="sx-sl">' +
							'<div class="sl-li">' + num + '</div>' +
							'<div class="sl-li">' + numOut + '</div>' +
							'</li>' +
							'<li class="sx-mc">' + data[i]['comment'] + '</li>' +
							'</ul>';
					});
					$(show_page).html(html);

				} else {
					if(mydata.num > 0) {
						alert('已经没有更多数据了');
					} else {
						$(show_page).html('非常抱歉，暂时没此项数据');
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
		//判断请求是否已经在执行中
		if(!isexport) {
			alert('请求正在执行中')
			return 0;
		}
		//显示正在加载
		$(".t_div").show()
		
		var mydatax = {};
		//条件筛选
		mydata.keyWord == undefined ? '' : mydatax.keyWord = mydata.keyWord;
		mydata.spec == undefined ? '' : mydatax.spec = mydata.spec;
		mydata.specMin == undefined ? '' : mydatax.specMin = mydata.specMin;
		mydata.specMax == undefined ? '' : mydatax.specMax = mydata.specMax;
		//此参数为更新请求状态	false 为执行中即方法不可执行
		isexport = false;

		$('#export_show').html(' ');
		$.ajax({
			type: "get",
			url: window.sessionStorage.getItem("host") + "/plantlet/plant/output/seedling/statistics/seedling.action.do",
			data: mydatax,
			success: function(result) {
				if(result.errorCode == 0) {
					$('#export_show').html('导出成功，请<a href="' + result.data + '">点击下载</a>');
				} else {
					$('#export_show').html('<span style="color: red;">' + result.msg + '</span>');
				}
				//更新状态 表可以重新执行
				isexport = true;
				//隐藏正在加载
				$(".t_div").hide()
			},
			error: function(e) {
				console.log(e.status)
				//更新状态 表可以重新执行
				isexport = true;
				//隐藏正在加载
				$(".t_div").hide()
			}
		});

	})

})