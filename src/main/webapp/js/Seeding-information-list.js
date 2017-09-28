var html = '';
var numberx = 0;
$(function() {
	var url_ = window.sessionStorage.getItem('host') + '/plantlet/plant/find/statistics/seedling.action.do';
	var sdname_url = window.sessionStorage.getItem('host') + '/plantlet/plant/find/all/sd_name/seedling.action.do';
	var show_page = '.sx-fors';
	var mydata = {
		num: numberx
	};
	getsdname(sdname_url);
	getdata(url_, mydata, show_page, false);
	//条件筛选
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
					//苗木名称
					$('#sdnamex').change(function() {
						var name = $('select[name=sdName]').val();
						mydata.num = numberx;
						if(Number(name) == 0) {
							delete mydata.keyWord;
						} else {
							mydata.keyWord = name;
						}
						var spec = $('select[name=spec]').val();
						if(Number(spec) == 0) {
							delete mydata.spec;
						} else {
							mydata.spec = spec;
						}
						getdata(url_, mydata, show_page, false);
					})
				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});

	}
	/*$('#sdnamex').change(function() {
		var name = $('select[name=sdName]').val();
		mydata.num = numberx;
		if(Number(name) == 0) {
			delete mydata.keyWord;
		} else {
			mydata.keyWord = name;
		}
		var spec = $('select[name=spec]').val();
		if(Number(spec) == 0) {
			delete mydata.spec;
		} else {
			mydata.spec = spec;
		}
		console.log(mydata)
		getdata(url_, mydata, show_page, false);
	})*/
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
			getdata(url_, mydata, show_page, false);

		}

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

			console.log(mydata)
		} else {
			//最大值不为空,则最大值大于最小值时才请求数据
			if(Number(mydata.specMax) > Number(mydata.specMin)) {
				getdata(url_, mydata, show_page, false);
				console.log(mydata)
			}
		}

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
			console.log(mydata)
		} else {
			//最小值不为空,则最小值小于最大值时才请求数据
			if(Number(mydata.specMax) > Number(mydata.specMin)) {
				if(Number(spec) == 0) {
					alert('请选择正确的规格！')
				} else {
					getdata(url_, mydata, show_page, false);
				}
				console.log(mydata)
			}
		}

	})
	//加载更多
	$('.jzm-center').click(function() {
		mydata.num = mydata.num + 1;
		getdata(url_, mydata, show_page, true);
	})

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
						var price = data[i]['minPrice'] == data[i]['maxPrice'] ? data[i]['minPrice'] : data[i]['minPrice'] + '-' + data[i]['maxPrice'];
						var priceOut = data[i]['minPriceOut'] == data[i]['maxPriceOut'] ? data[i]['minPriceOut'] : data[i]['minPriceOut'] + '-' + data[i]['maxPriceOut'];
						var marketPrice = data[i]['marketPrice'] == 0 ? '' : data[i]['marketPrice'] + '';
						var specs =Number(data[i]['specMax'])==0? data[i]['spec'] + data[i]['specMin']:data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] ;
						html += '<ul class="Seed_detail">' +
							'<li class="sx-mc"><a href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=0">' +
							'' + data[i]['sdName'] + '</a></li>' +
							'<li class="sx-mc">' + specs+ '</li>' +
							'<li class="sx-jg">' +
							//'<a href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=1">' +
							'<div class="jg-li">' + data[i]['offer'] + '</div>' +
							'<div class="jg-li"><a href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=2">' +
							'' + price + '</a></div>' +
							'<div class="jg-li"><a href="seeding_details.html?sdName=' + data[i]['sdName'] + '&spec=' + data[i]['spec'] + '&specMin=' + data[i]['specMin'] + '&specMax=' + data[i]['specMax'] + '&isall=3">' +
							'' + priceOut + '</a></div>' +
							'<div class="jg-li">' + marketPrice + '</div>' +
							'</li>' +
							'<li class="sx-sl">' +
							'<div class="sl-li">' + data[i]['num'] + '</div>' +
							'<div class="sl-li">' + data[i]['numOut'] + '</div>' +
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

})