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
	
	$('#selecet_sepc').click(function() {
		var name = $('select[name=sdName]').val();
		var spec = $('select[name=spec]').val();
		var specmin = $('input[name=specMin]').val();
		var specmax = $('input[name=specMax]').val();
		mydata.num = numberx;
		if(name != null) {
			mydata.keyWord = name;
		}
		if(spec != null) {
			mydata.spec = spec;
		}
		if(specmin != null) {
			mydata.specMin = specmin;
		}
		if(specmax != null) {
			mydata.specMax = specmax;
		}
		console.log(mydata)
		getdata(url_, mydata, show_page, false);
	})
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
				console.log(result)
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						data[i]['comment'] = data[i]['comment'] == null ? '' : data[i]['comment'];
						var price = data[i]['minPrice'] == data[i]['maxPrice'] ? data[i]['minPrice'] : data[i]['minPrice'] + '-' + data[i]['maxPrice'];
						var priceOut = data[i]['minPriceOut'] == data[i]['maxPriceOut'] ? data[i]['minPriceOut'] : data[i]['minPriceOut'] + '-' + data[i]['maxPriceOut'];
						var marketPrice = data[i]['marketPrice'] == 0 ? '' : data[i]['marketPrice'] + '（元）';
						html += '<a href="seeding_details.html?sdName='+data[i]['sdName']+'&spec='+data[i]['spec']+'&specMin='+data[i]['specMin']+'&specMax='+data[i]['specMax']+'"><ul class="Seed_detail">' +
							'<li class="sx-mc">' + data[i]['sdName'] + '</li>' +
							'<li class="sx-mc">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</li>' +
							'<li class="sx-jg">' +
							'<div class="jg-li">' + data[i]['offer'] + '（元）</div>' +
							'<div class="jg-li">' + price + '（元）</div>' +
							'<div class="jg-li">' + priceOut + '（元）</div>' +
							'<div class="jg-li">' + marketPrice + '</div>' +
							'</li>' +
							'<li class="sx-sl">' +
							'<div class="sl-li">' + data[i]['num'] + '</div>' +
							'<div class="sl-li">' + data[i]['numOut'] + '</div>' +
							'</li>' +
							'<li class="sx-mc">' + data[i]['comment'] + '</li>' +
							'</ul></a>';
					});
					$(show_page).html(html);
					$('.Seed_detail').click(function() {
						/*var num = $(this).index();
						var index = 0;
						if((num + 1) < 20) {
							index = 0;
						} else {
							index = Math.ceil((num - (num % 20)) / 20);
						}*/
						
						/*var select_val = mydata.num + ',' + index + ',' + data[num]['sdName']+','+data[index]['spec']+','+data[index]['specMin']+','+data[index]['specMax'];
						window.sessionStorage.setItem('seeding_detail', select_val);
						window.open('seeding_details.html');*/
					})
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

	function getsdname(urls) {
		$.ajax({
			type: "get",
			url: urls,
			async: true,
			success: function(result) {
				if(result.errorCode == 0) {
					var data = result.data;
					var htm = '';
					htm += '<select name="sdName">';
					$.each(data, function(i) {

						htm +='<option value="' + data[i] + '">' + data[i] + '</option>';

					});
					htm +='</select>';
					$('#show_sdname').html(htm);
					
				} else {

				}
			},
			error: function(e) {
				console.log(e.status)
			}
		});
	}
})