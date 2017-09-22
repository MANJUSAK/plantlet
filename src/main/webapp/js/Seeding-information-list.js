var html = '';
var numberx = 0;
$(function() {
	var url_ = window.sessionStorage.getItem('host') + '/plantlet/plant/find/statistics/seedling.action.do';

	var show_page = '.sx-fors';
	var mydata = {
		num: numberx
	};
	getdata(url_, mydata, show_page, false);
	$('#selecet_sepc').click(function() {
		var name = $('input[name=sdName]').val();
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
		//console.log(mydata)
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
				if(result.errorCode == 0) {
					var data = result.data;
					$.each(data, function(i) {
						data[i]['comment'] = data[i]['comment'] == null ? '无' : data[i]['comment'];
						html += '<ul class="Seed_detail">' +
							'<li class="sx-mc">' + data[i]['sdName'] + '</li>' +
							'<li class="sx-mc">' + data[i]['spec'] + data[i]['specMin'] + '-' + data[i]['specMax'] + '</li>' +
							'<li class="sx-jg">' +
							'<div class="jg-li">' + data[i]['offer'] + '</div>' +
							'<div class="jg-li">' + data[i]['price'] + '</div>' +
							'<div class="jg-li">' + data[i]['priceOut'] + '</div>' +
							'<div class="jg-li">' + data[i]['marketPrice'] + '</div>' +
							'</li>' +
							'<li class="sx-sl">' +
							'<div class="sl-li">' + data[i]['num'] + '</div>' +
							'<div class="sl-li">' + data[i]['numOut'] + '</div>' +
							'</li>' +
							'<li class="sx-mc">' + data[i]['comment'] + '</li>' +
							'</ul>';
					});
					$(show_page).html(html);
					$('.Seed_detail').click(function() {
						var num = $(this).index();
						var index = 0;
						if((num + 1) < 20) {
							index = 0;
						} else {
							index = Math.ceil((num - (num % 20)) / 20);
						}
						window.sessionStorage.setItem('seeding_detail', mydata.num + ',' + index + ',' + data[num]['sdName']);
						window.open('seeding_details.html');
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

})